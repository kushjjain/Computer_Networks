import java.util.Random;

class Token {
    private int capacity;
    private int tokenRate; 
    private int currentTokens;
    private long lastTime;

    public Token(int capacity, int tokenRate) {
        this.capacity = capacity;
        this.tokenRate = tokenRate;
        this.currentTokens = 0;
        this.lastTime = System.currentTimeMillis();
    }

    public void addTokens() {
        long currentTime = System.currentTimeMillis();
        long elapsedTimeInSeconds = (currentTime - lastTime) / 1000; 
        int newTokens = (int) (elapsedTimeInSeconds * tokenRate);
        if (newTokens > 0) {
            currentTokens = Math.min(capacity, currentTokens + newTokens);
            lastTime = currentTime;
        }
        System.out.println("Token Bucket: Current tokens: " + currentTokens);
    }

    public boolean consumeToken(int packetSize) {
        if (packetSize > currentTokens) {
            System.out.println("Token Bucket: Packet of size " + packetSize + " dropped (not enough tokens).");
            return false;
        }
        currentTokens -= packetSize;
        System.out.println("Token Bucket: Packet of size " + packetSize + " sent. Remaining tokens: " + currentTokens);
        return true;
    }
}

public class TokenBucket {
    public static void main(String[] args) {
        Token bucket = new Token(10, 2); 
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            bucket.addTokens();

            int packetSize = random.nextInt(4) + 1; 
            bucket.consumeToken(packetSize);
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
    }
}

import java.util.*;
public class SelectiveRepeat{
    public static final int WINDOW_SIZE = 4;
    public static final int TOTAL_FRAMES = 10;
    public static void sendFrame(int frame){
        System.out.println("Sending Frame " + frame + "...");
    }
    public static void receiveFrame(int frame) {
        System.out.println("Frame " + frame + " received successfully!");
    }
    public static boolean isFrameLost(Random random){
        return random.nextBoolean();
    }
    public static void main(String args[]){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        boolean[] ack = new boolean[TOTAL_FRAMES];
        int base =0;
        int nextFrame =0;
        while(base < TOTAL_FRAMES){
            while(nextFrame < TOTAL_FRAMES && nextFrame < base + WINDOW_SIZE){
                if(!ack[nextFrame]){
                    sendFrame(nextFrame);
                }
                nextFrame++;
            }
            for(int i = base; i< TOTAL_FRAMES && i < nextFrame ;i++){
                if(!ack[i]){
                    if(isFrameLost(random)){
                        System.out.println("Frame " + i + " lost in transit.");
                    }
                    else{
                        receiveFrame(i);
                        ack[i] = true;
                        System.out.println("Acknowledgment for Frame " + i + " received.");
                    }
                }
            }
            while(base< TOTAL_FRAMES && ack[base]){
                base++;
            }
            if(base<nextFrame){
                System.out.println("Retransmitting window starting from Frame " + base + "...");
                nextFrame = base;
            }
        }
        System.out.println("All frames sent and acknowledged successfully!");
    }
}

// import java.util.Random;

// class Leaky {
//     private int capacity;
//     private int leakRate;
//     private int currentLevel;

//     public Leaky(int capacity, int leakRate) {
//         this.capacity = capacity;
//         this.leakRate = leakRate;
//         this.currentLevel = 0;
//     }

//     public boolean addPacket(int packetSize) {
//         if (currentLevel + packetSize > capacity) {
//             System.out.println("Leaky Bucket: Packet of size " + packetSize + " dropped (bucket full).");
//             return false;
//         }
//         currentLevel += packetSize;
//         System.out.println("Leaky Bucket: Added packet of size " + packetSize + ". Current level: " + currentLevel);
//         return true;
//     }

//     public void leak() {
//         if (currentLevel > 0) {
//             int leakedSize = Math.min(leakRate, currentLevel);
//             currentLevel -= leakedSize;
//             System.out.println("Leaky Bucket: Leaked " + leakedSize + ". Current level: " + currentLevel);
//         }
//     }
// }

// public class LeakyBucket {
//     public static void main(String[] args) {
//         Leaky bucket = new Leaky(10, 1);
//         Random random = new Random();

//         for (int i = 0; i < 15; i++) {
//             int packetSize = random.nextInt(3) + 1; // Generates random number between 1 and 3
//             bucket.addPacket(packetSize);
//             bucket.leak();
//             try {
//                 Thread.sleep(1000); // 1-second delay
//             } catch (InterruptedException e) {
//                 Thread.currentThread().interrupt();
//                 System.out.println("Thread interrupted");
//             }
//         }
//     }
// }
import java.util.*;

public class LeakyBucket{
    public  int capacity;
    public  int leakRate;
    public  int currentLevel;
    public LeakyBucket(int capacity,int leakRate){
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.currentLevel = 0;
    }
    public  boolean addPacket(int packetSize){
        if(currentLevel + packetSize > capacity){
            System.out.println("Leaky Bucket: Packet of size " + packetSize + " dropped (bucket full).");
            return false;
        }
        currentLevel+=packetSize;
        System.out.println("Leaky Bucket: Added packet of size " + packetSize + ". Current level: " + currentLevel);
        return true;
    }
    public  void leak(){
        if(currentLevel > 0){
            int leakedSize = Math.min(leakRate,currentLevel);
            currentLevel -= leakedSize;
            System.out.println("Leaky Bucket: Leaked " + leakedSize + ". Current level: " + currentLevel);
        }
    }
    public static void main(String args[]){
        LeakyBucket leaky = new LeakyBucket(10, 1);
        Random random = new Random();
        for(int i=0;i<15;i++){
            int packetSize = random.nextInt(3)+1;
            leaky.addPacket(packetSize);
            leaky.leak();
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
    }
}
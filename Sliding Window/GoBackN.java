import java.util.*;
public class GoBackN{
    public static final int WINDOW_SIZE = 3;
    public static final int TOTAL_FRAMES = 10;
    public static void sendFrame(int frame){
        System.out.println("Sending Frame " + frame + " .............. ");
    } 
    public static void receiveFrame(int frame){
        System.out.println("Received Frame "+ frame + " !!!!!!!!!!!!!! ");
    }
    public static boolean isFrameLost(Random random){
        return random.nextBoolean();
    }
    public static void main(String args[]){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int base = 0;
        int nextFrame = 0;
        while(base < TOTAL_FRAMES){
            while(nextFrame < TOTAL_FRAMES && nextFrame < base + WINDOW_SIZE){
                sendFrame(nextFrame);
                nextFrame++;
            }
            for(int i= base; i< nextFrame ;i++){
                if(isFrameLost(random)){
                    System.out.println("Frame "+ i + "lost in transit..... Resending Window!!!!");
                    nextFrame = base;
                    break;
                }
                else{
                    receiveFrame(i);
                    System.out.println("Acknowledgement for frame "+ i + " received.....");
                    base++;
                }
            }
        }
        System.out.println("All frames are Acknowledged Successfully!!!!!!!!!!!!");
    }
}

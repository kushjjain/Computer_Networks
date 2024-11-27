import java.util.Scanner; 
 
public class Framing { 
    public static String characterCountEncode(String[] frames){
        StringBuilder encoded = new StringBuilder();
        for(String frame : frames){
            int length = frame.length() + 1;
            encoded.append(length).append(frame);
        }
        return encoded.toString();
    }

    public static String characterCountDecode(String encodedData){
        StringBuilder decode = new StringBuilder();
        int i=0;
        while(i < encodedData.length()){
            int length = Character.getNumericValue(encodedData.charAt(i)) - 1;
            i++;
            if(i+length <= encodedData.length()){
                decode.append(encodedData,i,i+length).append(" ");
                i+= length;
            }
            else{
                throw new StringIndexOutOfBoundsException("Frame limit exceed");
            }
        }
        return decode.toString().trim();
    }

    public static final String flag = "$";
    public static final String esc = "#";

    public static String byteStuffingEncode(String[] frames){
        StringBuilder encode = new StringBuilder();
        for(String frame: frames){
            frame = frame.replace(esc, esc + esc);
            frame = frame.replace(flag, esc + flag);
            encode.append(flag).append(frame).append(flag);
        }
        return encode.toString();
    }
    
    public static String byteStuffingDecode(String encodedData){
        StringBuilder decode = new StringBuilder();
        String[] frames = encodedData.split(flag);
        for(String frame: frames){
            if(!frame.isEmpty()){
                frame = frame.replace(esc + esc , esc);
                frame = frame.replace(esc + flag, flag);
                decode.append(frame).append(" ");
            }
        }
        return decode.toString().trim();
    }
 
    // Bit Stuffing Framing 
    public static String bitStuffingEncode(String[] frames){
        StringBuilder encode = new StringBuilder();
        for(String frame : frames){
            int count = 0;
            for(char bit : frame.toCharArray()){
                if(bit == '1'){
                    count++;
                }
                else{
                    count = 0;
                }
                encode.append(bit);
                if(count == 5){
                    encode.append('0');
                    count =0;
                }
            }
        }
        return encode.toString();
    }

    public static String bitStuffingDecode(String encodedData){
        StringBuilder decode = new StringBuilder();
        int count = 0;
        for(int i=0;i<encodedData.length();i++){
            char bit = encodedData.charAt(i);
            if(bit == '1'){
                count++;
            }
            else{
                count = 0;
            }
            decode.append(bit);
            if(count == 5 && i + 1 < encodedData.length() && encodedData.charAt(i+1)=='0'){
                i++;
                count = 0;
            } 
        }
        return decode.toString();
    }

    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 
 
        while (true) { 
            System.out.println("Select Framing Technique:"); 
            System.out.println("1. Character Count Framing"); 
            System.out.println("2. Byte Stuffing"); 
            System.out.println("3. Bit Stuffing"); 
            System.out.println("4. Exit"); 
            System.out.print("Enter your choice: "); 
            int choice = scanner.nextInt(); 
 
            if (choice == 4) { 
                System.out.println("Exiting program."); 
                System.exit(0); 
            } 
 
            System.out.print("Enter number of frames: "); 
            int numFrames = scanner.nextInt(); 
            scanner.nextLine();
 
            String[] frames = new String[numFrames]; 
            for (int i = 0; i < numFrames; i++) { 
                System.out.print("Enter frame " + (i + 1) + ": "); 
                frames[i] = scanner.nextLine(); 
            } 
 
            switch (choice) { 
                case 1: 
                    String charCountEncoded = characterCountEncode(frames); 
                    System.out.println("Transmitted Frame: " + charCountEncoded); 
                    System.out.println("Decoded Data: " + characterCountDecode(charCountEncoded)); 
                    break; 
 
                case 2: 
                    String byteStuffingEncoded = byteStuffingEncode(frames); 
                    System.out.println("Transmitted Frame: " + byteStuffingEncoded); 
                    System.out.println("Decoded Data: " + byteStuffingDecode(byteStuffingEncoded)); 
                    break; 
 
                case 3: 
                    String bitStuffingEncoded = bitStuffingEncode(frames); 
                    System.out.println("Transmitted Data: " + bitStuffingEncoded); 
                    System.out.println("Decoded Data: " + bitStuffingDecode(bitStuffingEncoded)); 
                    break; 
 
                default: 
                    System.out.println("Invalid choice. Please try again."); 
            } 
 
            System.out.println(); 
        } 
    } 
} 
import java.util.*;
public class CRC{
    public static String XOR(String dividend, String divisor){
        StringBuilder result = new StringBuilder();
        for(int i=0;i<divisor.length();i++){
            result.append(dividend.charAt(i) == divisor.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }
    public static String computeCRC(String data,String genPoly){
        int genLength = genPoly.length();
        String dividend = data.substring(0,genLength);
        for(int i = genLength; i<=data.length();i++){
            if(dividend.charAt(0) == '1'){
                dividend = XOR(dividend, genPoly);
            }
            else{
                dividend = XOR(dividend,"0".repeat(genLength));
            }
            if(i < data.length()){
                dividend = dividend.substring(1) + data.charAt(i);
            }
        }
        return dividend.substring(1);
    }
    public static void receiverData(String receivedData,String genPoly){
        String remainder = computeCRC(receivedData, genPoly);
        if(remainder.contains("1")){
            System.out.println("Error Detected in Received Data.");
        }
        else{
            System.out.println("No Error Detected in Received Data.");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter data to be transmitted: ");
        String data = sc.next();
        System.out.print("Enter the Generating polynomial: ");
        String genPoly = sc.next();
        int genPolyLength = genPoly.length();
        String paddedData = data + "0".repeat(genPolyLength - 1);
        System.out.println("\nPadded data: " + paddedData);
        String crc = computeCRC(paddedData, genPoly);
        System.out.println("CRC or Check value is: " + crc);
        String transmittedData = data + crc;
        System.out.println("Transmitted data: " + transmittedData);
        System.out.print("Enter received data: ");
        String receivedData = sc.next();
        receiverData(receivedData, genPoly);
    }
}
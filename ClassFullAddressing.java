import java.util.*;
public class ClassFullAddressing{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the IP Address: ");
        String ip = sc.nextLine();
        String ipClass,subnetMask,subnetAddress;
        String[] octets = ip.split("\\.");
        if(octets.length !=4 || !isValidIP(octets)){
            System.out.println("Invalid!! IP Address");
            return;
        }
        int firstOctet = Integer.parseInt(octets[0]);
        if(firstOctet <= 127){
            ipClass = "A";
            subnetMask = "255.0.0.0";
        }
        else if(firstOctet<=191){
            ipClass = "B";
            subnetMask = "255.255.0.0";
        }
        else if(firstOctet<=223){
            ipClass = "C";
            subnetMask = "255.255.255.0";
        }
        else if(firstOctet<=239){
            ipClass = "D";
            subnetMask = "N/A";
        }
        else{
            ipClass = "E";
            subnetMask = "N/A";
        }
        subnetAddress = subnetMask.equals("N/A") ? "N/A" : calculateSubnet(octets,subnetMask);
        System.out.println("Class : " +ipClass);
        System.out.println("Subnet Mask : " + subnetMask);
        System.out.println("Subnet Address : " +subnetAddress);
    }

    public static Boolean isValidIP(String[] octets){
        for(String octet : octets){
            int num;
            try{
                num = Integer.parseInt(octet);
            }
            catch(NumberFormatException e){
                return false;
            }
            if(num <0 || num > 255){
                return false;
            }
        }
        return true;
    }
    public static String calculateSubnet(String[] ip,String subnetMask){
        String[] masks = subnetMask.split("\\.");
        StringBuilder subnetAddress = new StringBuilder();
        for(int i=0;i<4;i++){
            int ipPart = Integer.parseInt(ip[i]);
            int ipMask = Integer.parseInt(masks[i]);

            subnetAddress.append(ipPart & ipMask).append(".");
        }
        return subnetAddress.substring(0,subnetAddress.length() - 1);
    }
}
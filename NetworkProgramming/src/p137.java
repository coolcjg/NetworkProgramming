import java.net.NetworkInterface;
import java.util.Enumeration;

public class p137 {
	public static void main(String[] args){
		try{			
			NetworkInterface eth0 = NetworkInterface.getByName("eth0");	
			Enumeration addresses = eth0.getInetAddresses();
			while(addresses.hasMoreElements()){
				System.out.println(addresses.nextElement());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

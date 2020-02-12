import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddress {
	
	public static void main(String[] args){
		try{
			InetAddress me = InetAddress.getLocalHost();
			String dottedQuad = me.getHostAddress();
			AddressTests at = new AddressTests();
			
			System.out.println("My address is " + dottedQuad);
			System.out.println("version :" +  at.getVersion(me));
			
		}catch(UnknownHostException ex){
			System.out.println("Im~~~.");
		}
	}
}

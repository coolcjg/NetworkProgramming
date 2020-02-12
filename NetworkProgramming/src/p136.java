import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class p136 {
	public static void main(String[] args){
		try{
			InetAddress local = InetAddress.getByName("127.0.0.1");
			NetworkInterface ni = NetworkInterface.getByInetAddress(local);
			if(ni == null){
				System.err.println("null");
			}
			
		}catch(SocketException ex){
			System.err.println("Could not list network interfaces.");
		}catch(UnknownHostException ex){
			System.err.println("That's weird. Could not lookup 127.0.0.1.");
		}
	}

}

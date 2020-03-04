import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DatagramExample {
	public static void main(String[] args) {
		String s = "This is a test.";
		
		try {
			byte[] data = s.getBytes("UTF-8");
			InetAddress ia = InetAddress.getByName("www.ibiblio.org");
			int port = 7;
			DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);
			System.out.println("getAddress  : " + dp.getAddress());
			System.out.println("getPort     : " + dp.getPort());
			System.out.println("getLength   : " + dp.getLength());
			System.out.println(new String(dp.getData(), dp.getOffset(), dp.getLength(), "UTF-8"));
			
		}catch(UnknownHostException | UnsupportedEncodingException ex) {
			System.err.println(ex);
		}
	}

}

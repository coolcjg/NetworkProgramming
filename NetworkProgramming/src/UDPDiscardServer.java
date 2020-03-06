import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPDiscardServer {
	
	public final static int PORT = 9;
	public final static int MAX_PACKET_SIZE = 65507;
	
	public static void main(String[] args) {
		byte[] buffer = new byte[MAX_PACKET_SIZE];
		
		try(DatagramSocket server = new DatagramSocket(PORT)) {
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			while(true) {
				try {
					server.receive(packet);
					String s = new String(packet.getData(), 0, packet.getLength(), "8859_1");
					System.out.println(packet.getAddress() + " at port " + packet.getPort() + " says " + s);
					//다음 패킷을 수신하기 위해 길이를 리셋.
					packet.setLength(buffer.length);
				}catch(IOException ex) {
					System.err.println(ex);
				}
			}
		}catch(SocketException ex) {
			System.err.println(ex);
		}
	}

}

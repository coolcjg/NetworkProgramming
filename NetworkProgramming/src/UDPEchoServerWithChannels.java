import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPEchoServerWithChannels {
	
	public final static int PORT = 8;
	public final static int MAX_PACKET_SIZE = 65507;
	
	public static void main(String[] args) {
		try {
			DatagramChannel channel = DatagramChannel.open();
			SocketAddress address = new InetSocketAddress(PORT);
			channel.bind(address);
			ByteBuffer buffer = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
			
			while(true) {
				SocketAddress client = channel.receive(buffer);
				buffer.flip();
				channel.send(buffer, client);
				buffer.clear();
			}
			
		}catch(IOException e) {
			System.err.println(e);
		}
	}

}

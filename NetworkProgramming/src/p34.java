import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class p34 {
	public static void main(String[] args) {
		try{
			
		FileOutputStream fos = new FileOutputStream("D://test.txt");
		generateCharacters(fos);
		}catch(IOException e){
			
			e.printStackTrace();
		}
		
		
	}
	
	public static void generateCharacters(OutputStream out) throws IOException{
		int firstPrintableCharacter = 33;
		int numberOfPrintableCharacters = 94;
		int numberOfCharactersPerLine = 72;
		
		int start = firstPrintableCharacter;
		
		byte[] line = new byte[numberOfCharactersPerLine +2];
		
		while(true){
			for(int i=start; i<start + numberOfCharactersPerLine; i++){
				line[i-start] = (byte)((i-firstPrintableCharacter)%numberOfPrintableCharacters + firstPrintableCharacter);
			}
			line[72] = (byte) '\r';
			line[73] = (byte) '\n';
			out.write(line);
			start = ((start+1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
		}
	}

}

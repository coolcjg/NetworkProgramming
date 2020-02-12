import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class DigestRunnable implements Runnable{
	
	private String filename;
	
	public DigestRunnable(String filename){
		this.filename = filename;
	}
	
	@Override
	public void run(){
		try{
			FileInputStream in = new FileInputStream(filename);
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(in, sha);
			while(din.read() != -1);
			din.close();
			byte[] digest = sha.digest();
			for(int i=0; i<digest.length; i++){
				System.out.print("digest[] : ");
				System.out.print(digest[i]+128);
				System.out.println();
				
			}
			
			
			StringBuilder result = new StringBuilder(filename);
			result.append(": ");
			result.append(DatatypeConverter.printHexBinary(digest));
			System.out.println(result);
		}catch(IOException ex){
			System.err.println(ex);
		}catch(NoSuchAlgorithmException ex){
			System.err.println(ex);
		}
	}
	
	public static void main(String[] args){
		for(String filename: args){
			DigestRunnable dr = new DigestRunnable(filename);
			Thread t = new Thread(dr);
			t.start();
		}
	}

}

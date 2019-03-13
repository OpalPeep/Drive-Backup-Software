package googleDrive;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileOp {


	public static void writeToFile(File writeTo, boolean text, StandardOpenOption option, String  ... strings) {
		System.out.println("Begining to write to " + writeTo.getAbsolutePath());
		Charset charset = Charset.forName("US-ASCII");
		int count = 0;
		try (BufferedWriter writer = Files.newBufferedWriter(writeTo.toPath(), charset, option,StandardOpenOption.WRITE)) {
			if(text) 
			for(int I = 0; I < strings.length; I++) {
				if(strings[I] != null) {
					count++;
					writer.write(strings[I]);
					if(I != strings.length) {
						writer.newLine();
					}
				}
				if(I == strings.length) {
					writer.close();
				}
			}
			else 
				for(int I = 0; I < strings.length; I++) {
					if(strings[I] != null) {
						count++;
						writer.write(strings[I]);
						if(I != strings.length) {
							writer.newLine();
						}
					}
					if(I == strings.length) {
						writer.close();
					}
				}
		} catch (IOException e) {
			e.printStackTrace();
    	}
		System.out.println("The file is " + count + " lines long");
	}
	
	public static String[] readFile(File f) {
		if(f.exists() && f.canRead() && f.length() != 0) {
			String[] cont = null;
			try {
				BufferedReader reader = Files.newBufferedReader(f.toPath());
			    int I = 0;
			    String[] s = new String[(int) f.length()];
			    while ((s[I] = reader.readLine()) != null) {
			    	I++;
			    }
				cont = new String[I]; 
			    for(int II = 0; II < I; II++) cont[II] = s[II];
			    reader.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			return cont;
		}
		return null;
	}
	
	public static String[] readStream(InputStream stream) {
		ArrayList<String> list = new ArrayList<>();
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			    int I = 0;
			    String s = "";
			    while ((s = reader.readLine()) != null) {
			    	list.add(s);
			    	I++;
			    }
			    reader.close();
			}
			catch(IOException e) { 
				e.printStackTrace();
				return null;
			}
			return list.toArray(new String[0]);
	}
}

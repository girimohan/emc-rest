package emc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UploadImage {
	
	public static boolean copyImage(String fileName, InputStream in) {
		
		String UPLOAD_PATH = "./img/candidates";
		try {


		          try ( // write the inputStream to a FileOutputStream
		                  OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + "/"+fileName))) {
		              int read = 0;
		              byte[] bytes = new byte[1024];

		              while ((read = in.read(bytes)) != -1) {
		                  out.write(bytes, 0, read);
		              }
		             
		              in.close();
		              out.flush();

		          }
		          return true;


		    } catch (IOException e) {
		        System.out.println(e);
		        return false;
		    }
		}
	
}

		



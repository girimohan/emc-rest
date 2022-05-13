package emc.utils;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



import com.google.appengine.repackaged.com.google.common.io.Files;

/**
 * 
 * @author DGautam
 * Class to handle image upload
 *
 */

public class UploadImage {
	
	
	/**
	 * @author DGautam
	 * @param fileName
	 * @param fileType
	 * @param inputStream
	 * Copy Files to the image folder.
	 */
	
	  public static void copyFile(String fileName,String fileType, InputStream inputStream) {

	       try {


	       String relativeUploadPath = "img/candidates";
				//relativeWebPath is the path to the folder you created in your web directory
	            File file = getUniqueFilename(new File(relativeUploadPath+"/"+fileName));




	          try ( // write the inputStream to a FileOutputStream
	                  OutputStream out = new FileOutputStream(new File(relativeUploadPath + "/"+file.getName()))) {
	              int read = 0;
	              byte[] bytes = new byte[1024];

	              while ((read = inputStream.read(bytes)) != -1) {
	                  out.write(bytes, 0, read);
	              }

	              inputStream.close();
	              out.flush();

	          }


	    } catch (IOException e) {
	        System.out.println(e);
	    }
	}
   
	  /**
	   * @author DGautam
	   * @param file
	   * @return file
	   * returns a file with a unique name in case an image with the same name 
         already exist in the folder
	   */
	

	private static File getUniqueFilename( File file )
	    {
	        String baseName = Files.getNameWithoutExtension( file.getName());
	        String extension = Files.getFileExtension( file.getName() );
	        int counter = 1;
	        while(file.exists())
	        {
	            file = new File( file.getParent(), baseName + "-" + (counter++) + "." + extension );
	        }
	        return file;
	    }
	
}

		



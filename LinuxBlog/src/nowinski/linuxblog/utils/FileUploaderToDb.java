package nowinski.linuxblog.utils;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;


public class FileUploaderToDb {
	public static byte[] uploadPhoto(Part filePart, int width, int height) throws IOException {
		InputStream inputStream = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] image = new byte[51322];
		int read;

		if (filePart != null) {
			inputStream = filePart.getInputStream();
			while ((read = inputStream.read(image, 0, image.length)) != -1)
				output.write(image, 0, read);
			output.flush();
			output.close();
			inputStream.close();
		}
		return scale(image, width, height);
	}

	private static byte[] scale(byte[] fileData, int width, int height) {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        try {
            BufferedImage img = ImageIO.read(in);
            if(height == 0) {
                height = img.getHeight();
            }
            if(width == 0) {
                width = img.getWidth();
            }
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ImageIO.write(imageBuff, "jpg", buffer);
            //close open streams and return result byte array
            in.close();
            buffer.close();
            return buffer.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	//do poprawy
	public static boolean checkFileType(String fileName) {
		int errorFileType = 0;
		if (!fileName.endsWith(".png"))
			errorFileType++;
		if (!fileName.endsWith(".jpg"))
			errorFileType++;
		if (!fileName.endsWith(".jpeg"))
			errorFileType++;
		if (errorFileType == 3)
			return false;
		return true;
	}
}

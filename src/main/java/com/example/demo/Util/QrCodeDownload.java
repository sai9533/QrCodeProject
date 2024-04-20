package com.example.demo.Util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;


@Service
public class QrCodeDownload {

    
	public byte[] addContentToImage(byte[] imageData, String additionalContent) throws IOException {
	    // Convert additional content to byte array
	    byte[] additionalContentBytes = additionalContent.getBytes();

	    // Read the resized image
	    ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
	    BufferedImage resizedImage = ImageIO.read(bais);

	    // Calculate the height of the new image
	    int resizedImageWidth = resizedImage.getWidth();
	    int resizedImageHeight = resizedImage.getHeight();
	    int additionalContentHeight = 20; // Adjust this value based on your content size
	    int newImageHeight = resizedImageHeight + additionalContentHeight;

	    // Create a new image with the combined height
	    BufferedImage combinedImage = new BufferedImage(resizedImageWidth, newImageHeight, BufferedImage.TYPE_INT_RGB);

	    // Draw the resized image onto the new image
	    Graphics2D graphics = combinedImage.createGraphics();
	    graphics.drawImage(resizedImage, 0, 0, null);

	    // Draw the additional content below the resized image
	    graphics.setColor(Color.BLACK); // Set color of the additional content
	    graphics.setFont(new Font("Arial", Font.PLAIN, 12)); // Set font for the additional content
	    graphics.drawString(additionalContent, 10, resizedImageHeight + 15); // Adjust the position as needed

	    // Dispose the Graphics object
	    graphics.dispose();

	    // Convert the combined BufferedImage back to byte array
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    ImageIO.write(combinedImage, "jpg", outputStream);
	    return outputStream.toByteArray();
	}

}

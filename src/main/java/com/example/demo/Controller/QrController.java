package com.example.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Product;
import com.example.demo.Repo.ProductRepo;
import com.example.demo.Util.QrCodeDownload;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
//import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;


@RestController
public class QrController {

    @Autowired
    private ProductRepo imageRepository; // Assuming you have a repository to interact with the database
    @Autowired
    QrCodeDownload load;
   /* @GetMapping("/images/{id}")
    
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable Integer id) throws IOException {
        Optional<Product> optionalImage = imageRepository.findById(id);
        if (optionalImage.isPresent()) {
            Product imageEntity = optionalImage.get();
            byte[] imageDataBytes = imageEntity.getImageData();
            byte[] resizedImageData = resizeImage(imageDataBytes, 50, 50);
            String additionalContent = "Additional content related to the image."; // Add your additional content here
            //byte[] imageData = addContentToImage(resizedImageData, additionalContent); // Add content below the image
            byte[] imageData = load.addContentToImage(imageDataBytess, additionalContent);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Adjust content type based on the actual image format
            headers.setContentLength(imageData.length);
            headers.setContentDispositionFormData("attachment", "image.png"); 
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new ByteArrayResource(imageData));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	private byte[] resizeImage(byte[] imageDataBytes, int targetWidth , int targetHeight) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayInputStream bais = new ByteArrayInputStream(imageDataBytes);
        BufferedImage originalImage = ImageIO.read(bais);
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", baos);
        return baos.toByteArray();
		
	}
	
	  private byte[] addContentToImage(byte[] imageData, String additionalContent) throws IOException {
	        // Convert additional content to byte array
	        byte[] additionalContentBytes = additionalContent.getBytes();

	        // Combine image data and additional content
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        outputStream.write(imageData);
	        outputStream.write(additionalContentBytes);
	        return outputStream.toByteArray();
	    }
	    */
}

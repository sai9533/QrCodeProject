package com.example.demo.Controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Product;
import com.example.demo.Util.QRCodeResponse;
import com.example.demo.Util.ZXingHelper;

import jakarta.servlet.http.HttpServletResponse;




import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;

import javax.imageio.ImageIO;
//import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
@Controller
public class BarCodeController {
	
	
	
	
	@RequestMapping(value = "/barcode/{id}", method = RequestMethod.GET)
	public void barcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception
	{
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(ZXingHelper.getBarCodeImage(id, 200, 50));
		outputStream.flush();
		outputStream.close();
	}
	
	/*@RequestMapping(value = "/barcode1/{id}", method = RequestMethod.GET)
	public void barcode1(@PathVariable("id") String id, HttpServletResponse response) throws Exception {
	    // Set content type to indicate that it's an image
		Product p=new Product("chain","1500","50","24");
		
	    response.setContentType("image/png");
	    
	    // Set content disposition header to indicate download
	    response.setHeader("Content-Disposition", "attachment; filename=\"barcode.png\"");
	    
	    // Generate barcode image
	    byte[] barcodeImage = ZXingHelper.getBarCodeImage(p.toString(), 400, 80);
	    
	    // Write barcode image to response output stream
	    try (OutputStream outputStream = response.getOutputStream()) {
	        outputStream.write(barcodeImage);
	        outputStream.flush();
	    }
	    
	}*/
	   @RequestMapping(value = "/barcode/", method = RequestMethod.POST)
		public void barcode4(@RequestBody Product p,HttpServletResponse response) throws Exception
		{
	    	System.out.println(p.toString());
		   //Product p=new Product("chain","1500","50","24");
	    	StringBuffer sb=new StringBuffer();
	    	sb.append(p.getProductName()+"\n");
	    	sb.append(p.getProductPrice()+"\n");
	    	sb.append(p.getProductWeight()+"\n");
			response.setContentType("image/png");
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(ZXingHelper.getBarCodeImage(sb.toString(),0, 50));
			outputStream.flush();
			outputStream.close();
		}
	   
	  /*  @GetMapping("/barcode")
	    public void generateBarcode(@RequestParam String data, HttpServletResponse response) throws IOException, WriterException {
	        response.setContentType("image/png");

	        // Set barcode dimensions
	        int width = 400;
	        int height = 200;

	        // Create BitMatrix
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.CODE_128, width, height);

	        // Create BufferedImage
	        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
	        graphics.setColor(Color.WHITE);
	        graphics.fillRect(0, 0, width, height);
	        graphics.setColor(Color.BLACK);

	        // Write BitMatrix to BufferedImage
	        for (int x = 0; x < width; x++) {
	            for (int y = 0; y < height; y++) {
	                if (bitMatrix.get(x, y)) {
	                    graphics.fillRect(x, y, 1, 1);
	                }
	            }
	        }

	        // Write BufferedImage to HttpServletResponse
	        ImageIO.write(bufferedImage, "png", response.getOutputStream());
	    }*/
	    
	    @PostMapping("/qrcode")
	    public void generateBarcode(@RequestBody Product p, HttpServletResponse response) throws IOException, WriterException {
	        response.setContentType("image/png");

	        // Encode URL
	        //String encodedUrl = URLEncoder.encode(url, "UTF-8");

	        // Set barcode dimensions
	        int width = 100;
	        int height = 100;
	        StringBuffer sb=new StringBuffer();
	    	sb.append(p.getProductName()+"\n");
	    	sb.append(p.getProductPrice()+"\n");
	    	sb.append(p.getProductWeight()+"\n");
	    	String str="<html><body>welcome</body></html>";
	        // Create BitMatrix
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, width, height);

	        // Create BufferedImage
	        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
	        graphics.setColor(Color.WHITE);
	        graphics.fillRect(0, 0, width, height);
	        graphics.setColor(Color.BLACK);

	        // Write BitMatrix to BufferedImage
	        for (int x = 0; x < width; x++) {
	            for (int y = 0; y < height; y++) {
	                if (bitMatrix.get(x, y)) {
	                    graphics.fillRect(x, y, 1, 1);
	                }
	            }
	        }

	        // Write BufferedImage to HttpServletResponse
	        ImageIO.write(bufferedImage, "png", response.getOutputStream());
	    }
        
	    @GetMapping("/generateQRCode")
	    public QRCodeResponse generateQRCode(@RequestParam String data, @RequestParam String qrCodeNumber) throws WriterException, IOException {
	        // Combine data and QR code number
	        String combinedData = data + " | QR Code Number: " + qrCodeNumber;

	        // Set QR code parameters
	        int size = 400;

	        // Encode data into QR code
	        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<>();
	        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(combinedData, BarcodeFormat.QR_CODE, size, size, hintMap);

	        // Create buffered image to draw QR code
	        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
	        Graphics2D graphics = (Graphics2D) image.getGraphics();
	        graphics.setColor(Color.WHITE);
	        graphics.fillRect(0, 0, size, size);
	        graphics.setColor(Color.BLACK);

	        // Write BitMatrix to buffered image
	        for (int i = 0; i < size; i++) {
	            for (int j = 0; j < size; j++) {
	                if (bitMatrix.get(i, j)) {
	                    graphics.fillRect(i, j, 1, 1);
	                }
	            }
	        }

	        // Convert BufferedImage to byte array
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(image, "png", baos);
	        byte[] imageData = baos.toByteArray();

	        // Encode byte array to Base64 string
	        String base64Image = Base64.getEncoder().encodeToString(imageData);

	        // Create QRCodeResponse object
	        return new QRCodeResponse(base64Image, combinedData);
	    }
	    @GetMapping(value = "/generateQRCode1", produces = MediaType.IMAGE_PNG_VALUE)
	    public ResponseEntity<byte[]> generateQRCode1(@RequestParam String data) throws WriterException, IOException {
	        // Combine data and QR code number
	        String combinedData = data + " | QR Code Number: " ;

	        // Set QR code parameters
	        int size = 400;

	        // Encode data into QR code
	        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<>();
	        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(combinedData, BarcodeFormat.QR_CODE, size, size, hintMap);

	        // Create buffered image to draw QR code
	        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
	        Graphics2D graphics = (Graphics2D) image.getGraphics();
	        graphics.setColor(Color.WHITE);
	        graphics.fillRect(0, 0, size, size);
	        graphics.setColor(Color.BLACK);

	        // Write BitMatrix to buffered image
	        for (int i = 0; i < size; i++) {
	            for (int j = 0; j < size; j++) {
	                if (bitMatrix.get(i, j)) {
	                    graphics.fillRect(i, j, 1, 1);
	                }
	            }
	        }

	        // Convert BufferedImage to byte array
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(image, "png", baos);
	        byte[] imageData = baos.toByteArray();

	        // Set headers for file download
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        headers.setContentDispositionFormData("attachment", "qrcode.png");

	        // Return the byte array representing the QR code image with appropriate headers
	        return ResponseEntity.ok().headers(headers).body(imageData);
	    }

	 
}

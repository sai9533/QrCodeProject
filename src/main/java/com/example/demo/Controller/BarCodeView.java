package com.example.demo.Controller;


	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.servlet.ModelAndView;
	import com.google.zxing.WriterException;
	import com.google.zxing.EncodeHintType;
	import com.google.zxing.BarcodeFormat;
	import com.google.zxing.MultiFormatWriter;
	import com.google.zxing.common.BitMatrix;
	import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
	import java.awt.*;
	import java.awt.image.BufferedImage;
	import java.io.ByteArrayOutputStream;
	import java.io.IOException;
	import java.util.Base64;
	import java.util.HashMap;
	import java.util.Map;

import javax.imageio.ImageIO;

	@Controller
	public class BarCodeView {

	    @GetMapping("/der")
	    public ModelAndView generateQRCode() throws WriterException, IOException {
	    	String data="welcome";
	    	String qrCodeNumber="get";
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

	        // Convert BufferedImage to Base64 string
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(image, "png", baos);
	        byte[] imageData = baos.toByteArray();
	        String base64Image = Base64.getEncoder().encodeToString(imageData);

	        // Add QR code image and other content to ModelAndView
	        ModelAndView modelAndView = new ModelAndView("index");
	        modelAndView.addObject("base64Image", base64Image);
	        modelAndView.addObject("data", combinedData);
	        return modelAndView;
	    }
	}




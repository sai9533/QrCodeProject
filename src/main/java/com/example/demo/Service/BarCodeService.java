package com.example.demo.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
public class BarCodeService {

	
	public byte[] generateQRCodeImage( String data) throws WriterException, IOException {
        // Combine data and QR code number
        String combinedData = data + " | QR Code Number: " ;

        // Set QR code parameters
        int size = 400;

        // Generate QR code
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        BitMatrix bitMatrix = qrCodeWriter.encode(combinedData, BarcodeFormat.QR_CODE, size, size, hints);

        // Convert BitMatrix to BufferedImage
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

        // Convert BufferedImage to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageData = baos.toByteArray();

        // Return the byte array representing the QR code image with appropriate headers
        return imageData;
    }
	/* public String generate( String data,  String qrCodeNumber) throws WriterException, IOException {
	        // Generate QR code image
	       // BufferedImage image =generateQRCodeImage(data, qrCodeNumber);

	        // Convert BufferedImage to byte array
	        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
	       // ImageIO.write(image, "png", baos);
	       // byte[] imageData = baos.toByteArray();

	        // Store QR code image in database
	       // QRCodeEntity qrCodeEntity = new QRCodeEntity();
	       // qrCodeEntity.setImageData(imageData);
	       // qrCodeRepository.save(qrCodeEntity);

	        // Return a success message or redirect to another page
	        return "QR code stored successfully!";
	    }*/
}

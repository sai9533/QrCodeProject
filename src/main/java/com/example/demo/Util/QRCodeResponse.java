package com.example.demo.Util;

public class QRCodeResponse {
	
	 private String qrCodeImage;
     private String qrCodeData;

     public QRCodeResponse(String qrCodeImage, String qrCodeData) {
         this.qrCodeImage = qrCodeImage;
         this.qrCodeData = qrCodeData;
     }

	public String getQrCodeImage() {
		return qrCodeImage;
	}

	public void setQrCodeImage(String qrCodeImage) {
		this.qrCodeImage = qrCodeImage;
	}

	public String getQrCodeData() {
		return qrCodeData;
	}

	public void setQrCodeData(String qrCodeData) {
		this.qrCodeData = qrCodeData;
	}


}

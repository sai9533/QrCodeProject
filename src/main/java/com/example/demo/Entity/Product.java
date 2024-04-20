package com.example.demo.Entity;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Product {
	
	
	@Id
	@GeneratedValue
	private Integer productId;
	private String productName;
	private String productPrice;
	private String productWeight;
	private String productType;
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	private String productStatus;
	 @Lob
	 @Column(columnDefinition = "LONGBLOB")
	 private byte[] imageData;
	 
	 public byte[] getProductImg() {
		return productImg;
	}
	public void setProductImg(byte[] productImg) {
		this.productImg = productImg;
	}
	@Lob
	 @Column(columnDefinition = "LONGBLOB")
	 private byte[] productImg;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	/*public Product(String productName, String productPrice, String productWeight, String productType) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.productWeight = productWeight;
		this.productType = productType;
	}*/
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductWeight() {
		return productWeight;
	}
	public void setProductWeight(String productWeight) {
		this.productWeight = productWeight;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productPrice=" + productPrice + ", productWeight="
				+ productWeight + ", productType=" + productType + "]";
	}
	
	

}

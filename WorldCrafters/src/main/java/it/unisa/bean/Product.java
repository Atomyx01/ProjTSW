package it.unisa.bean;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private double price;
	private String seller;
	private String imgSrc;
	private Category category;
	private int quantity;
	private int selectedQuantity;
	private int favorites;
	private Date listingDate;
	private String description;
	private boolean status;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getSeller() {
		return seller;
	}


	public void setSeller(String seller) {
		this.seller = seller;
	}


	public String getImgSrc() {
		return imgSrc;
	}


	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getSelectedQuantity() {
		return selectedQuantity;
	}


	public void setSelectedQuantity(int selectedQuantity) {
		this.selectedQuantity = selectedQuantity;
	}

	
	public int getFavorites() {
		return favorites;
	}


	public void setFavorites(int favorites) {
		this.favorites = favorites;
	}
	
	public Date getListingDate() {
		return listingDate;
	}


	public void setListingDate(Date listingDate) {
		this.listingDate = listingDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

}
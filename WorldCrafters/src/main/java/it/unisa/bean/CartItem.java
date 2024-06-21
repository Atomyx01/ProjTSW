package it.unisa.bean;

public class CartItem extends Product {
	
	private static final long serialVersionUID = 1L;

	private int selectedQuantity;
	private int cartId;
	private int productId;
	
	
	@Override
	public int getSelectedQuantity() {
		return selectedQuantity;
	}

	@Override
	public void setSelectedQuantity(int selectedQuantity) {
		this.selectedQuantity = selectedQuantity;
	}

	public int getCartId() {
		return cartId;
	}
	
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

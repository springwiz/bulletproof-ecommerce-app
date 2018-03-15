/**
 * 
 */
package com.bulletproof.ecommerce.core.model;

/**
 * The Class InventorySupply.
 *
 * @author sumit
 */
public class InventorySupply extends Entity {
	
	/** The store id. */
	private String storeId = "";
	
	/** The product id. */
	private String productId = "";
	
	/** The quantity. */
	private double quantity = 0.0;
	
	/**
	 * Gets the store id.
	 *
	 * @return the store id
	 */
	public String getStoreId() {
		return storeId;
	}

	/**
	 * Sets the store id.
	 *
	 * @param storeId the new store id
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * Sets the product id.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
}

/**
 * 
 */
package com.bulletproof.ecommerce.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class InventorySupply.
 *
 * @author sumit
 */
public class InventorySupplyDTO extends EntityDTO {
	
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
	@JsonProperty
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
	@JsonProperty
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
	@JsonProperty
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

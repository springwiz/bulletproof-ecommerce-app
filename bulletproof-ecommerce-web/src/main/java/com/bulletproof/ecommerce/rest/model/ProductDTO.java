/**
 * 
 */
package com.bulletproof.ecommerce.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class Product.
 *
 * @author sumit
 */
public class ProductDTO extends EntityDTO {
	
	/** The category id. */
	private String categoryId;
	
	/** The color. */
	private String color;
	
	/** The make. */
	private String make;
	
	/** The size. */
	private String size;

	/**
	 * Gets the category id.
	 *
	 * @return the category id
	 */
	@JsonProperty
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * Sets the category id.
	 *
	 * @param categoryId the new category id
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	@JsonProperty
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Gets the make.
	 *
	 * @return the make
	 */
	@JsonProperty
	public String getMake() {
		return make;
	}

	/**
	 * Sets the make.
	 *
	 * @param make the new make
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	@JsonProperty
	public String getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(String size) {
		this.size = size;
	}
}

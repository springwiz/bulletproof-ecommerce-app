/**
 * 
 */
package com.bulletproof.ecommerce.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class Entity.
 *
 * @author sumit
 */
public class EntityDTO {
	
	/** The id. */
	private String id = "";
	
	/** The name. */
	private String name = "";
	
	/** The description. */
	private String description = "";

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@JsonProperty
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@JsonProperty
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	@JsonProperty
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}

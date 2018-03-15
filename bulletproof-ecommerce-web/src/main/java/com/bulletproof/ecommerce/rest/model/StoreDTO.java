/**
 * 
 */
package com.bulletproof.ecommerce.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class Store.
 *
 * @author sumit
 */
public class StoreDTO extends EntityDTO {
	
	/** The address. */
	private String address;
	
	/** The city. */
	private String city;
	
	/** The country. */
	private String country; 
	
	/** The zipcode. */
	private String zipcode; 
	
	/** The longitude. */
	private double longitude; 
	
	/** The latitude. */
	private double latitude;

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	@JsonProperty
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	@JsonProperty
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	@JsonProperty
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the zipcode.
	 *
	 * @return the zipcode
	 */
	@JsonProperty
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * Sets the zipcode.
	 *
	 * @param zipcode the new zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	@JsonProperty
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Sets the longitude.
	 *
	 * @param longitude the new longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	@JsonProperty
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Sets the latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}

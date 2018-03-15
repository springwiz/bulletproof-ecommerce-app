/**
 * 
 */
package com.bulletproof.ecommerce.core.dao;

import com.bulletproof.ecommerce.core.dao.exception.PersistentException;
import com.bulletproof.ecommerce.core.model.InventorySupply;
import com.bulletproof.ecommerce.core.model.Product;
import com.bulletproof.ecommerce.core.model.Store;


/**
 * The Interface StoreItemRetailRepository.
 */
public interface StoreItemRetailRepository {
	
	/**
	 * Find inventory.
	 *
	 * @param storeId the store id
	 * @param productId the product id
	 * @return the inventory supply
	 * @throws PersistentException the persistent exception
	 */
	InventorySupply findInventory(String storeId, String productId) throws PersistentException;

	/**
	 * Gets the product.
	 *
	 * @param productId the product id
	 * @return the product
	 * @throws PersistentException the persistent exception
	 */
	Product getProduct(String productId) throws PersistentException;

	/**
	 * Gets the store details.
	 *
	 * @param storeId the store id
	 * @return the store details
	 * @throws PersistentException the persistent exception
	 */
	Store getStoreDetails(String storeId) throws PersistentException;

	/**
	 * Change product.
	 *
	 * @param product the product
	 * @throws PersistentException the persistent exception
	 */
	void changeProduct(Product product) throws PersistentException;

	/**
	 * Adjust inventory.
	 *
	 * @param invSupply the inv supply
	 * @throws PersistentException the persistent exception
	 */
	void adjustInventory(InventorySupply invSupply) throws PersistentException;
}

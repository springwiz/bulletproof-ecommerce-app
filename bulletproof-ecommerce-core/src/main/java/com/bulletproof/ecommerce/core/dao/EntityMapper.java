/**
 * 
 */
package com.bulletproof.ecommerce.core.dao;

import org.springframework.stereotype.Component;

import com.bulletproof.ecommerce.core.model.Entity;
import com.bulletproof.ecommerce.core.model.InventorySupply;
import com.bulletproof.ecommerce.core.model.Product;
import com.bulletproof.ecommerce.core.model.Store;
import com.datastax.driver.core.Row;


/**
 * The Class EntityMapper.
 */
@Component
public final class EntityMapper {

	/**
	 * Instantiates a new entity mapper.
	 */
	public EntityMapper() {
	
	}

	/**
	 * Persistent to model.
	 *
	 * @param row the row
	 * @param object the object
	 * @return the entity
	 */
	public Entity persistentToModel(Row row, Entity object) {
		object.setId(row.getString("id"));
		object.setName(row.getString("name"));
		object.setDescription(row.getString("description"));
		
		if(InventorySupply.class.isInstance(object)) {
			InventorySupply invSupply = (InventorySupply) object;
			invSupply.setProductId(row.getString("store_id"));
			invSupply.setQuantity(row.getDouble("quantity"));
			invSupply.setStoreId(row.getString("product_id"));
		}
		else if(Product.class.isInstance(object)) {
			Product product = (Product) object;
			product.setCategoryId(row.getString("category_id"));
			product.setColor(row.getString("color"));
			product.setMake(row.getString("make"));
			product.setSize(row.getString("size"));
			product.setPrice(row.getDouble("price"));
		}
		else if(Store.class.isInstance(object)) {
			Store store = (Store) object;
			store.setAddress(row.getString("address"));
			store.setCity(row.getString("city"));
			store.setCountry(row.getString("country"));
			store.setZipcode(row.getString("zipcode"));
			store.setLongitude(row.getDouble("longitute"));
			store.setLatitude(row.getDouble("latitute"));
		}
		else {
			// do nothing for now
			// its a category
		}
		return object;
	}
}

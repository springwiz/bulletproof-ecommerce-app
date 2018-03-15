package com.bulletproof.ecommerce.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulletproof.ecommerce.core.CassandraConfig;
import com.bulletproof.ecommerce.core.dao.exception.PersistentException;
import com.bulletproof.ecommerce.core.model.InventorySupply;
import com.bulletproof.ecommerce.core.model.Product;
import com.bulletproof.ecommerce.core.model.Store;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

/**
 * The Class CurrentStoreItemRetailRepositoryImpl.
 *
 * @author sumit
 */
public class StoreItemRetailRepositoryImpl implements StoreItemRetailRepository {

	/** The entity mapper. */
	@Autowired
	private EntityMapper entityMapper;

	/** The session. */
	private Session session;

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(StoreItemRetailRepositoryImpl.class);

	/** The insert product statement. */
	private PreparedStatement insertProductStatement;

	/** The get product statement. */
	private PreparedStatement getProductStatement;

	/** The find inventory statement. */
	private PreparedStatement findInventoryStatement;

	/** The adjust inventory statement. */
	private PreparedStatement adjustInventoryStatement;

	/**
	 * Instantiates a new current store item retail repository impl.
	 *
	 * @param cassandraQueries the cassandra queries
	 * @param session the session
	 */
	public StoreItemRetailRepositoryImpl(CassandraConfig cassandraQueries, Session session) {
		this.session = session;
		insertProductStatement = session.prepare(cassandraQueries.getChangeProductQ());
		getProductStatement = session.prepare(cassandraQueries.getGetProductQ());
		findInventoryStatement = session.prepare(cassandraQueries.getFindInventoryQ());
		adjustInventoryStatement = session.prepare(cassandraQueries.getAdjustInventoryQ());
	}

	/* (non-Javadoc)
	 * @see com.bulletproof.ecommerce.core.dao.CurrentStoreItemRetailRepository#findInventory(java.lang.String, java.lang.String)
	 */
	@Override
	public InventorySupply findInventory(String storeId, String productId) throws PersistentException {
		logger.debug("Entering findInventory method with storeId {} and productId {}", storeId, productId);
		List<InventorySupply> rowList = new ArrayList<InventorySupply>();
		try {
			ResultSet rs = session.execute(findInventoryStatement.bind(storeId, productId));
			rs.forEach((t) -> rowList.add((InventorySupply) entityMapper.persistentToModel(t, new InventorySupply())));
		} catch (Exception exc) {
			throw new PersistentException("Getting Inventory Supply Records failed", exc);
		}
		return (rowList.isEmpty()) ? null : rowList.get(0);
	}

	/* (non-Javadoc)
	 * @see com.bulletproof.ecommerce.core.dao.CurrentStoreItemRetailRepository#getProduct(java.lang.String)
	 */
	@Override
	public Product getProduct(String productId) throws PersistentException {
		logger.debug("Entering getProduct method with productId {}", productId);
		List<Product> rowList = new ArrayList<Product>();
		try {
			ResultSet rs = session.execute(getProductStatement.bind(productId));
			rs.forEach((t) -> rowList.add((Product) entityMapper.persistentToModel(t, new Product())));
		} catch (Exception exc) {
			throw new PersistentException("Getting Product Records failed", exc);
		}
		return (rowList.isEmpty()) ? null : rowList.get(0);
	}

	/* (non-Javadoc)
	 * @see com.bulletproof.ecommerce.core.dao.CurrentStoreItemRetailRepository#getStoreDetails(java.lang.String)
	 */
	@Override
	public Store getStoreDetails(String storeId) throws PersistentException {
		logger.debug("Entering findInventory method with storeId {}", storeId);
		List<Store> rowList = new ArrayList<Store>();
		try {
			ResultSet rs = session.execute(findInventoryStatement.bind(storeId));
			rs.forEach((t) -> rowList.add((Store) entityMapper.persistentToModel(t, new Store())));
		} 
		catch (Exception exc) {
			throw new PersistentException("Getting Store Records failed", exc);
		}
		return (rowList.isEmpty()) ? null : rowList.get(0);
	}

	/* (non-Javadoc)
	 * @see com.bulletproof.ecommerce.core.dao.CurrentStoreItemRetailRepository#changeProduct(com.bulletproof.ecommerce.core.model.Product)
	 */
	@Override
	public void changeProduct(Product product) throws PersistentException {
		logger.debug("Entering changeProduct method:: Product Id: {}", product.getId());
		try {
			session.execute(insertProductStatement.bind(product.getId(), product.getName(), product.getCategoryId(),
					product.getColor(), product.getMake(), product.getSize(), product.getPrice()));
		} catch (Exception exc) {
			throw new PersistentException("Modifying Product failed", exc);
		}
	}

	/* (non-Javadoc)
	 * @see com.bulletproof.ecommerce.core.dao.CurrentStoreItemRetailRepository#adjustInventory(com.bulletproof.ecommerce.core.model.InventorySupply)
	 */
	@Override
	public void adjustInventory(InventorySupply invSupply) throws PersistentException {
		logger.debug("Entering adjustInventoryStatement method:: Inventory Id: {}", invSupply.getId());
		try {
			session.execute(adjustInventoryStatement.bind(invSupply.getQuantity(), invSupply.getStoreId(),
					invSupply.getProductId()));
		} 
		catch (Exception exc) {
			throw new PersistentException("Adjusting Inventory failed", exc);
		}
	}

	/**
	 * Gets the entity mapper.
	 *
	 * @return the entity mapper
	 */
	public EntityMapper getEntityMapper() {
		return entityMapper;
	}

	/**
	 * Sets the entity mapper.
	 *
	 * @param entityMapper the new entity mapper
	 */
	public void setEntityMapper(EntityMapper entityMapper) {
		this.entityMapper = entityMapper;
	}
}

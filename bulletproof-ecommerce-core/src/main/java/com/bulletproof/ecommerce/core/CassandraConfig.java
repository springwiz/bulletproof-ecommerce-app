package com.bulletproof.ecommerce.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.bulletproof.ecommerce.core.dao.CassandraConnection;
import com.bulletproof.ecommerce.core.dao.CassandraSession;
import com.bulletproof.ecommerce.core.dao.StoreItemRetailRepository;
import com.bulletproof.ecommerce.core.dao.StoreItemRetailRepositoryImpl;

/**
 * The Class CassandraConfig.
 */
@Configuration
@ComponentScan("com.bulletproof.ecommerce.core")
@PropertySource("classpath:application.properties")
public class CassandraConfig {
	
	/** The host. */
	@Value("${Host}")
	private String host;
	
	/** The port. */
	@Value("${Port}")
	private int port;
	
	/** The user. */
	@Value("${User}")
	private String user;
	
	/** The password. */
	@Value("${Password}")
	private String password;
	
	/** The get product Q. */
	@Value("${GetProduct}")
	private String getProductQ;
	
	/** The change product Q. */
	@Value("${ChangeProduct}")
	private String changeProductQ;
	 
	/** The find inventory Q. */
	@Value("${FindInventory}")
	private String findInventoryQ;
	 
	/** The adjust inventory Q. */
	@Value("${AdjustInventory}")
	private String adjustInventoryQ; 
	
	/**
	 * Domain property source placeholder config.
	 *
	 * @return the property sources placeholder configurer
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer domainPropertySourcePlaceholderConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	/**
	 * Creates the repository.
	 *
	 * @return the store item retail repository
	 */
	@Bean
	public StoreItemRetailRepository createRepository() {
		CassandraSession session = new CassandraSession(
				new CassandraConnection(this.getHost(), 2, this.getPort(), this.getUser(), this.getPassword()),
				"ecommerce");
		return new StoreItemRetailRepositoryImpl(this, session.getSession());
	}
	
	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Sets the host.
	 *
	 * @param host the new host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Gets the gets the product Q.
	 *
	 * @return the gets the product Q
	 */
	public String getGetProductQ() {
		return getProductQ;
	}

	/**
	 * Sets the gets the product Q.
	 *
	 * @param getProductQ the new gets the product Q
	 */
	public void setGetProductQ(String getProductQ) {
		this.getProductQ = getProductQ;
	}

	/**
	 * Gets the change product Q.
	 *
	 * @return the change product Q
	 */
	public String getChangeProductQ() {
		return changeProductQ;
	}

	/**
	 * Sets the change product Q.
	 *
	 * @param changeProductQ the new change product Q
	 */
	public void setChangeProductQ(String changeProductQ) {
		this.changeProductQ = changeProductQ;
	}

	/**
	 * Gets the find inventory Q.
	 *
	 * @return the find inventory Q
	 */
	public String getFindInventoryQ() {
		return findInventoryQ;
	}

	/**
	 * Sets the find inventory Q.
	 *
	 * @param findInventoryQ the new find inventory Q
	 */
	public void setFindInventoryQ(String findInventoryQ) {
		this.findInventoryQ = findInventoryQ;
	}

	/**
	 * Gets the adjust inventory Q.
	 *
	 * @return the adjust inventory Q
	 */
	public String getAdjustInventoryQ() {
		return adjustInventoryQ;
	}

	/**
	 * Sets the adjust inventory Q.
	 *
	 * @param adjustInventoryQ the new adjust inventory Q
	 */
	public void setAdjustInventoryQ(String adjustInventoryQ) {
		this.adjustInventoryQ = adjustInventoryQ;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}

/**
 * 
 */
package com.bulletproof.ecommerce.core.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulletproof.ecommerce.core.dao.exception.CassandraInitializationException;
import com.datastax.driver.core.Session;

/**
 * Class to perform session related operations
 *
 */
public class CassandraSession {

	private Session session = null;
	private String keyspace = null;

	private static Logger logger = LoggerFactory.getLogger(CassandraSession.class);

	/**
	 * Start Cassandra session
	 * @param con
	 * @param keyspace
	 */
	public CassandraSession(CassandraConnection con, String keyspace) {
		this.keyspace = keyspace;
		try {
			session = con.getCluster().connect(this.keyspace);
		} catch (Exception ex) {
			throw new CassandraInitializationException(
					"ERROR: Unable to connect to Cassandra with keyspace: " + keyspace, ex);
		}
	}

	/**
	 * Get session from the connection
	 * @return
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * Get Keyspace from the session
	 * @return
	 */
	public String getKeyspace() {
		return keyspace;
	}

	/**
	 * Close the session after use
	 */
	public void closeSession() {
		try {
			if (null != session && null != session.getCluster() && !session.getCluster().isClosed()) {
				session.close();
				logger.info("Closed Cassandra Session succesfully for keyspace: {}", keyspace);
			}
		} catch (Exception e) {
			logger.info("Unable to close Cassandra Session. Error: ", e);
		}
	}

}

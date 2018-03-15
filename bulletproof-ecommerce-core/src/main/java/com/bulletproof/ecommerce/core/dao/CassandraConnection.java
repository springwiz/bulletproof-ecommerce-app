package com.bulletproof.ecommerce.core.dao;

import org.apache.commons.lang3.StringUtils;
import org.junit.runners.model.InitializationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulletproof.ecommerce.core.dao.exception.CassandraInitializationException;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.SocketOptions;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.DowngradingConsistencyRetryPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;

/**
 * Cassandra connection creator 
 *
 */
public class CassandraConnection {

	private static final long SLEEP_MILISECONDS = 2000;
	
	private static final int DEFAULT_NUMBER_OF_RETRIES = 3;

	private static final int READ_TIMEOUT = 10000;

	final Logger logger = LoggerFactory.getLogger(CassandraConnection.class);

	private Cluster cluster = null;

	/**
	 * CassandraConnection constructor
	 * 
	 * @param serverIP
	 * @param retries
	 * @param port
	 * @param userName
	 * @param passward
	 * @throws InitializationError
	 */
	public CassandraConnection(final String serverIP, final Integer retries, final Integer port,
			final String userName, final String password) {
		
		Integer noOfRetries = retries;

		if (null == noOfRetries || noOfRetries == 0) {
			noOfRetries = DEFAULT_NUMBER_OF_RETRIES;
		}

		connectToCassandra(serverIP, userName, password, noOfRetries, port);
	}

	private void connectToCassandra(final String serverIP, final String userName, final String password,
			Integer noOfRetries, Integer port) {
		String serverIPs[] = serverIP.split(",");
		for (int ipIdx = 0; ipIdx < serverIPs.length; ipIdx++) {
			serverIPs[ipIdx] = StringUtils.trim(serverIPs[ipIdx]);
		}

		Exception e = null;
		Integer retried = -1;

		while (retried < noOfRetries && (cluster == null || e != null)) {
			try {
				e = null;
				if (retried > -1) {
					logger.info("Exception during connection, will attempt retry : {} more times",
							noOfRetries - retried);
					logger.info("sleep for 2 secs befor attempting retry..");
					Thread.sleep(SLEEP_MILISECONDS);
					logger.info("Retry attempt number : {}", retried + 1);
				}
				cluster = Cluster.builder().addContactPoints(serverIPs).withPort(port)
						.withCredentials(userName, password)
						.withQueryOptions(new QueryOptions().setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM))
						.withLoadBalancingPolicy(new TokenAwarePolicy(DCAwareRoundRobinPolicy.builder().build()))
						.withSocketOptions(new SocketOptions().setReadTimeoutMillis(READ_TIMEOUT))
						.withProtocolVersion(ProtocolVersion.V3)
						.withRetryPolicy(DowngradingConsistencyRetryPolicy.INSTANCE).build();
			} catch (final Exception ex) {
				e = ex;
				retried++;
			}
		}

		if (cluster == null || e != null) {
			throw new CassandraInitializationException("ERROR: Unable to connect to Cassandra.", e);
		}
	}

	public Cluster getCluster() {
		return this.cluster;
	}

	public void closeCluster() {
		try {
			if (null != cluster) {
				cluster.close();
				logger.info("Closed Cassandra Cluster succesfully");
			}
		} catch (Exception e) {
			logger.info("Unable to close Cassandra Cluster for . Error: ", e);
		}
	}
}

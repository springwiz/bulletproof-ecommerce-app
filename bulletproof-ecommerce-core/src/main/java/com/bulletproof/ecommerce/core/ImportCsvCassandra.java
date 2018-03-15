/**
 * 
 */
package com.bulletproof.ecommerce.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulletproof.ecommerce.core.dao.CassandraConnection;
import com.bulletproof.ecommerce.core.dao.CassandraSession;
import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSetFuture;

/**
 * @author sumit
 *
 */
public class ImportCsvCassandra {
	private static PreparedStatement insertStatement;
	
	private static Logger logger = LoggerFactory.getLogger(ImportCsvCassandra.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String table = args[0];
		String csvFilePath = args[1];
		
		try(BufferedReader fileReader = new BufferedReader(new FileReader(csvFilePath))) {
			StringBuffer insertQuery = new StringBuffer("INSERT INTO " + "ecommerce" + "." + table + " (");
			StringBuffer valuesString = new StringBuffer("VALUES(");
			String[] columns = fileReader.readLine().split(",");
			for(String col: columns) {
				insertQuery.append(col + ",");
				valuesString.append("?,");
			}
			insertQuery.deleteCharAt(insertQuery.lastIndexOf(","));
			valuesString.deleteCharAt(valuesString.lastIndexOf(","));
			insertQuery.append(")");
			valuesString.append(")");
			insertQuery.append(" " + valuesString.toString());
			
			CassandraSession session = new CassandraSession(
					new CassandraConnection("192.168.1.10", 2, 9042, "cassandra", "N0RY0e5MXKPV"),
					"ecommerce");
			insertStatement = session.getSession().prepare(insertQuery.toString());
			List<ResultSetFuture> currentResults = new ArrayList<>();
			List<BoundStatement> boundStatements = new ArrayList<>();
			BatchStatement batch = new BatchStatement(BatchStatement.Type.UNLOGGED);
			
			String line = "";
			while ((line = fileReader.readLine()) != null) {
				BoundStatement bs = insertStatement.bind(line.split(","));
				boundStatements.add(bs);
				if(boundStatements.size() >= 50) {				
					batch.addAll(boundStatements);
					currentResults.add(session.getSession().executeAsync(batch));
					batch.clear();
					boundStatements.clear();
				}
			}
			if(!boundStatements.isEmpty()) {
				batch.addAll(boundStatements);
				currentResults.add(session.getSession().executeAsync(batch));
			}
	
			for (ResultSetFuture currentResult : currentResults) {
				currentResult.getUninterruptibly();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
}
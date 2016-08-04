/**
 * <p> Web Service Application Programming Interface</p>
 * 
 *

 * 
 * @author Bernardo Tabuenca Archilla
 * @version 1.0
 */
package org.btb.analyticsws.jaxb.resources;

import org.btb.analyticsws.db.DbConnectException;
import org.btb.analyticsws.db.Transaction;

public class Resource {

	protected Transaction openTransaction() {

		Transaction tr = null;

		try {
			tr = new Transaction();
		} catch (DbConnectException e) {

		}
		return tr;
	}

	protected void closeTransaction(Transaction tr) {
		try {
			tr.commitTransaction();
		} catch (DbConnectException e) {
		}
	}

	protected void rollBackTransaction(Transaction tr) {
		try {
			tr.rollbackTransaction();
		} catch (DbConnectException e) {

		}
	}

}

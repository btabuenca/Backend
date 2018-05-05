/**
 * <p> Web Service Application Programming Interface</p>
 * 
 *

 * 
 * @author Bernardo Tabuenca Archilla
 * @version 1.0
 */
package org.btb.analyticsws.jaxb.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.btb.analyticsws.db.Transaction;
import org.btb.analyticsws.db.dao.DAOException;
import org.btb.analyticsws.db.dao.RecepcionDAO;
import org.btb.analyticsws.jaxb.model.Recepcion;

public class RecepcionResource extends Resource {
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	String id;

	public RecepcionResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id; // idregistro
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Recepcion getJSON() {

		Recepcion item = null;
		Transaction tr = openTransaction();

		try {
			if (tr == null) {
				System.out.println("getJSON could not be accomplished. Reason: transaction is null");
			} else {
				item = new RecepcionDAO().getItem(id + "", tr.getConnection());
				if (item != null) {
					System.out.println(
							"getJSON returns item  |" + item.getIdregistro() + "|" + item.getMatricula() + "|");
				}

				closeTransaction(tr);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			rollBackTransaction(tr);
		}

		return item;
	}

}
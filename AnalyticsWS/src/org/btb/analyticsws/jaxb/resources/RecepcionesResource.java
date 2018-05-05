/**
 * <p> Web Service Application Programming Interface</p>
 * 
 *

 * 
 * @author Bernardo Tabuenca Archilla
 * @version 1.0
 */
package org.btb.analyticsws.jaxb.resources;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.btb.analyticsws.db.Transaction;
import org.btb.analyticsws.db.dao.DAOException;
import org.btb.analyticsws.db.dao.RecepcionDAO;
import org.btb.analyticsws.jaxb.model.Recepcion;

@Path("/recepciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class RecepcionesResource extends Resource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	@Context
	ServletContext servletContext;

	// Return the list of voys for applications
	@GET
	public List<Recepcion> getAllItems() {
		List<Recepcion> lItems = null;
		Transaction tr = openTransaction();

		try {
			lItems = new RecepcionDAO().getAllItems(tr.getConnection());
			closeTransaction(tr);
		} catch (DAOException e) {
			System.out.println("Error in getAllItems. " + e.getMessage());
			e.printStackTrace();
			rollBackTransaction(tr);
		}

		return lItems;
	}

	@GET
	@Path("count")
	public String getCount() {

		int count = 0;

		Transaction tr = openTransaction();

		try {
			if (tr == null) {
				System.out.println("getCount could not be accomplished. Reason: transaction is null");
				servletContext.log("btb. getCount could not be accomplished. Reason: transaction is null");
				count = -1;
			} else {
				// TO IMPROVE / do select count ...
				ArrayList lItems = new RecepcionDAO().getAllItems(tr.getConnection());
				count = lItems.size();
				closeTransaction(tr);
			}
		} catch (DAOException e) {
			rollBackTransaction(tr);
			e.printStackTrace();
			count = -1;
		}

		return String.valueOf(count);
	}

	// Defines that the next path parameter after recepciones is
	// treated as a parameter and passed to the RecepcionResources
	// Allows to type http://localhost:8080/MtrackWS/rest/recepciones/1
	// 1 will be treaded as parameter voy and passed to RecepcionResource
	@Path("{recepcion}")
	public RecepcionResource getItem(@PathParam("recepcion") String id) {
		return new RecepcionResource(uriInfo, request, id);
	}

}
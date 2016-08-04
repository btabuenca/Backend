/**
 * <p> Web Service Application Programming Interface</p>
 * 
 *

 * 
 * @author Bernardo Tabuenca Archilla
 * @version 1.0
 */
package org.btb.analyticsws.jaxb.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.btb.analyticsws.Constants;
import org.btb.analyticsws.db.Transaction;
import org.btb.analyticsws.db.dao.DAOException;
import org.btb.analyticsws.db.dao.ExpedicionDAO;
import org.btb.analyticsws.db.vo.ExpedicionVO;
import org.btb.analyticsws.jaxb.model.Expedicion;

@Path("/expediciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ExpedicionesResource extends Resource {

	
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
  public List<Expedicion> getAllItems() {
	    List<Expedicion> lItems = null;
	    Transaction tr = openTransaction();
	    
	    try {
			lItems = new ExpedicionDAO().getAllItems(tr.getConnection());
			closeTransaction(tr);
		} catch (DAOException e) {
			System.out.println("Error in getAllItems. "+e.getMessage());
			e.printStackTrace();
			rollBackTransaction(tr);
		}

	    return lItems;
  }
  


  @GET
  @Path("count")
  public String getCount() {

    int count = 0;
    servletContext.log("btb. Testing log 1");
    
	Transaction tr = openTransaction();

	
	try {					
		if (tr == null){
			System.out.println("getCount could not be accomplished. Reason: transaction is null");
			servletContext.log("btb. getCount could not be accomplished. Reason: transaction is null");
			count = -1;
		}else{
			// TO IMPROVE / do select count ...
			ArrayList lItems = new ExpedicionDAO().getAllItems(tr.getConnection());
			count = lItems.size();
			closeTransaction(tr);
		}
	} catch (DAOException e) {
		rollBackTransaction(tr);
		e.printStackTrace();
		servletContext.log("btb. Testing log PETE");
		count = -1;
	}    
    
    return String.valueOf(count);
  }
  
  

  // Defines that the next path parameter after voys is
  // treated as a parameter and passed to the VoyResources
  // Allows to type http://localhost:8080/MtrackWS/rest/voys/1
  // 1 will be treaded as parameter voy and passed to ExpedicionResource
  @Path("{expedicion}")
  public ExpedicionResource getItem(@PathParam("expedicion") String id) {
    return new ExpedicionResource(uriInfo, request, id);
  }

} 
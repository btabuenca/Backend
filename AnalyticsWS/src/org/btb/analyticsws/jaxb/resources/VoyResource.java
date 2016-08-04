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

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import org.btb.analyticsws.Constants;
import org.btb.analyticsws.db.Transaction;
import org.btb.analyticsws.db.dao.DAOException;
import org.btb.analyticsws.db.dao.VoyDAO;
import org.btb.analyticsws.jaxb.model.Voy;

public class VoyResource extends Resource {
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	String id;

	public VoyResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id; // SEQ_VOYAGE
	}
	
	  @GET
	  @Produces({MediaType.APPLICATION_JSON})
	  public Voy getVoyJSON() {
		  
			Voy item = null;
			Transaction tr = openTransaction();

			try {
				if (tr == null) {
					System.out.println("getVoyJSON could not be accomplished. Reason: transaction is null");
				} else {
					item = new VoyDAO().getItem(id + "", tr.getConnection());
					if (item!=null){
						System.out.println("getVoyJSON returns item  |"+item.getSeq_voyage()+"|"+item.getLib_voyage()+"|");	
					}
					
					closeTransaction(tr);
				}
			} catch (DAOException e) {
				e.printStackTrace();
				rollBackTransaction(tr);
			}

			return item;
	  }  	  
	  
	  @PUT
	  @Consumes(MediaType.APPLICATION_JSON)	  
	  public void updateOrInsertVoy(Voy voy) throws IOException {
		  
			Voy item = null;
			Transaction tr = openTransaction();
			
			try {
				item = new VoyDAO().getItem(id, tr.getConnection());
				
				if (item != null){
					// The item already exists in db. Perform update
					new VoyDAO().updateItem(voy, tr.getConnection());
				}else{
					// The item does not exist in db. Perform insert
					new VoyDAO().insertItem(voy, tr.getConnection());					
				}
				closeTransaction(tr);
				
			} catch (DAOException e) {
				e.printStackTrace();
				rollBackTransaction(tr);
			}
	  }
	  
	  
	  
	  
//	  public Response putVoy(JAXBElement<Voy> item) {		  
//
//		  // ESTE AUN NO LO HAS PROBADO O NO FUNCIONA
//		  
//		// Update or insert voy whenever it does not exist
//		Voy c = item.getValue();
//		
//		new VoyDAO().getItem(sSeqVoyage, conn)
//		
//	    return putAndGetResponse(c);
//	  }
	  
	  @DELETE
	  public void deleteVoy() {
		 
		  
		Transaction tr = openTransaction();		 
	    try {
			new VoyDAO().deleteItem(id, tr.getConnection());
			closeTransaction(tr);
		} catch (DAOException e) {
			rollBackTransaction(tr);
			e.printStackTrace();
			throw new RuntimeException("Delete: Voy with " + id +  " not found");
		}

	  }
	  
	  private Response putAndGetResponse(Voy item) {
	    Response res;
	    
	    Transaction tr = openTransaction();
	    int insert;
		try {
			insert = new VoyDAO().insertItem(item.getSeq_voyage(), item.getAge(), item.getCvoy(), item.getSeq_section(), item.getLib_voyage(), tr.getConnection());
			closeTransaction(tr);
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			rollBackTransaction(tr);
			e.printStackTrace();
			insert = Constants.OPERATION_FAILED;
		}
	    
	    if(insert == Constants.OPERATION_SUCCEED){
	    	res = Response.noContent().build();
	    }else{
	    	res = Response.created(uriInfo.getAbsolutePath()).build();	    	
	    }
	    
	    return res;
	  }
	  	
	

}
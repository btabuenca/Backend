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
import org.btb.analyticsws.db.dao.VoyDAO;
import org.btb.analyticsws.db.vo.VoyVO;
import org.btb.analyticsws.jaxb.model.Voy;

@Path("/voys")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class VoysResource extends Resource {

	
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
  public List<Voy> getVoys() {
	    List<Voy> voys = null;
	    Transaction tr = openTransaction();
	    
	    try {
			voys = new VoyDAO().getAllItems(tr.getConnection());
			closeTransaction(tr);
		} catch (DAOException e) {
			System.out.println("Error in getVoys. "+e.getMessage());
			e.printStackTrace();
			rollBackTransaction(tr);
		}

	    return voys;
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
			ArrayList lItems = new VoyDAO().getAllItems(tr.getConnection());
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
  
  
  @POST
  public String insertVoy(Voy voy) throws IOException {
	  
    int iRet = Constants.OPERATION_SUCCEED;
    Transaction tr = openTransaction();
    
    if (voy.getSeq_voyage() != null){

    	try {
			iRet = new VoyDAO().insertItem(voy, tr.getConnection());
			closeTransaction(tr);
		} catch (DAOException e) {

			iRet = Constants.OPERATION_FAILED;
			e.printStackTrace();
			rollBackTransaction(tr);
			System.out.println("btb. Error "+e.getMessage());			
		}
        
    }


    return iRet+"";


  }
  
  
// This works perfectly but you did not need to use it.  
//  @POST
//  @Produces(MediaType.TEXT_HTML)
//  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//  public void newVoy(
//		  @FormParam(VoyVO.SEQ_VOYAGE) String seqvoyage,
//		  @FormParam(VoyVO.AGE) String age,
//		  @FormParam(VoyVO.CVOY) String cvoy,
//		  @FormParam(VoyVO.SEQ_SECTION) String seqsection,
//		  @FormParam(VoyVO.LIB_VOYAGE) String libvoyage,
//		  @Context HttpServletResponse servletResponse) throws IOException {
//	  
//    Voy voy = new Voy();
//    Transaction tr = openTransaction();
//    
//    if (seqvoyage != null){
//
//    	voy.setSeq_voyage(seqvoyage);
//    	voy.setAge(age);
//    	voy.setCvoy(cvoy);
//    	voy.setSeq_section(seqsection);
//    	voy.setLib_voyage(libvoyage);
//
//    	try {
//			new VoyDAO().insertItem(seqvoyage, age, cvoy, seqsection, libvoyage, tr.getConnection());
//		} catch (DAOException e) {
//
//			e.printStackTrace();
//			rollBackTransaction(tr);
//			servletContext.log("btb. Testing log PETE");			
//		}
//        
//    }
//
//
//    servletResponse.sendRedirect("../create_todo.html");
//  }
  

  // Defines that the next path parameter after voys is
  // treated as a parameter and passed to the VoyResources
  // Allows to type http://localhost:8080/MtrackWS/rest/voys/1
  // 1 will be treaded as parameter voy and passed to VoyResource
  @Path("{voy}")
  public VoyResource getVoy(@PathParam("voy") String id) {
    return new VoyResource(uriInfo, request, id);
  }

} 
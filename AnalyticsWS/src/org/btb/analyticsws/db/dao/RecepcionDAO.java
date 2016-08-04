/**
 * <p> Web Service Application Programming Interface</p>
 * 
 *

 * 
 * @author Bernardo Tabuenca Archilla
 * @version 1.0
 */
package org.btb.analyticsws.db.dao;

import java.util.*;

import org.btb.analyticsws.db.dao.DAOException;
import org.btb.analyticsws.db.vo.RecepcionVO;
import org.btb.analyticsws.jaxb.model.Recepcion;

import java.sql.*;

public class RecepcionDAO extends GenericDAO {
	
	

	public ArrayList<Recepcion> getAllItems(Connection conn) throws DAOException {

		ArrayList<Recepcion> itemList = new ArrayList<Recepcion>();
		
		String sql = " select r."+RecepcionVO.MATRICULA+", "; 
		sql+="  r."+RecepcionVO.IDREGISTRO+" as "+RecepcionVO.IDREGISTRO+", ";
		sql+="  "+RecepcionVO.MERCANCIA+" as "+RecepcionVO.MERCANCIA+", ";
		sql+="  "+RecepcionVO.EMPRESA+" as "+RecepcionVO.EMPRESA+", ";
		sql+="  to_char(p.fechaprevistaentrada, 'dd/mm/yyyy HH24:mi') as "+RecepcionVO.FECHA_PREVISION+", ";  
		sql+="  to_char(r.fechahoraregistro, 'dd/mm/yyyy HH24:mi') as "+RecepcionVO.FECHA_REGISTRO+", ";
		sql+="  to_char(ae.fechahorainicioae, 'dd/mm/yyyy HH24:mi') as "+RecepcionVO.FECHA_AUTORIZACION_ENTRADA+", ";
		sql+="  to_char(ce.fechahoraapertura, 'dd/mm/yyyy HH24:mi') as "+RecepcionVO.FECHA_CONTROL_ENTRADA+", ";
		sql+="  to_char(asa.fechahorainicioas, 'dd/mm/yyyy HH24:mi') as "+RecepcionVO.FECHA_AUTORIZACION_SALIDA+", ";
		sql+="  to_char(cs.fechahoraapertura, 'dd/mm/yyyy HH24:mi') as "+RecepcionVO.FECHA_CONTROL_SALIDA+", ";		
		sql+="  trunc((ae.fechahorainicioae - r.fechahoraregistro) * 24 * 60) AS mins_fuera, ";
		sql+="  trunc((cs.fechahoraapertura - ae.fechahorainicioae) * 24 * 60) AS mins_dentro, ";
		sql+="  trunc((cs.fechahoraapertura - r.fechahoraregistro) * 24 * 60) AS mins_visita ";
		sql+=" from previsiones p, registros r, autorizacionesentradas ae, autorizacionessalidas asa, controlentrada ce, controlsalida cs, m_tipovehiculos tv ";
		sql+=" where  ";
		sql+="  r.codtipoacceso = 'R' and ";
		sql+="  r.codtipovehiculo = tv.codtipovehiculo and ";
		sql+="  r.idprevision = p.idprevision(+) and ";
		sql+="  r.idregistro = ae.idregistro(+) and ";
		sql+="  r.idregistro = asa.idregistro(+) and ";
		sql+="  r.idregistro = ce.idregistro(+) and ";
		sql+="  r.idregistro = cs.idregistro(+) ";
		sql+=" order by r.fechahoraregistro desc";

		
		setConexion(conn);
		executeQuery(sql);

		try {
			ResultSet rs = getResultSet();
			Recepcion anItem = null;
			while (rs.next()) {
				anItem = new Recepcion();
				
				anItem.setMatricula(rs.getString(RecepcionVO.MATRICULA)); //	VARCHAR2
				anItem.setIdregistro(rs.getString(RecepcionVO.IDREGISTRO)); //	VARCHAR2
				anItem.setMercancia(rs.getString(RecepcionVO.MERCANCIA)); //	VARCHAR2
				anItem.setEmpresa(rs.getString(RecepcionVO.EMPRESA)); //	VARCHAR2
				
				anItem.setdPrev(rs.getString(RecepcionVO.FECHA_PREVISION)); 
				anItem.setdReg(rs.getString(RecepcionVO.FECHA_REGISTRO)); 
				anItem.setdAutEnt(rs.getString(RecepcionVO.FECHA_AUTORIZACION_ENTRADA)); 
				anItem.setdEnt(rs.getString(RecepcionVO.FECHA_CONTROL_ENTRADA)); 
				anItem.setdAutSal(rs.getString(RecepcionVO.FECHA_AUTORIZACION_SALIDA)); 
				anItem.setdSal(rs.getString(RecepcionVO.FECHA_CONTROL_SALIDA));
				
				anItem.setMinsFuera(rs.getString(RecepcionVO.MINS_FUERA));
				anItem.setMinsDentro(rs.getString(RecepcionVO.MINS_DENTRO));
				anItem.setMinsVisita(rs.getString(RecepcionVO.MINS_VISITA));
			
				//System.out.println(rs.getString(RecepcionVO.MATRICULA)+"-------->"+anItem.toString());
				
				itemList.add(anItem);
			}
			System.out.println("Finishes iterating over "+itemList.size()+" items");
			
		} catch (SQLException e) {
			System.out.println("SQLException getting voys "+e.getMessage());
			throw new DAOException(e.getMessage());
		} finally {
			System.out.println("Let us release resource from query");
			releaseResourcesQuery();
		}
		
		System.out.println("Connections should be already release. Returns "+itemList.size()+" items");
		
		return itemList;
	}
	
	
	
	public Recepcion getItem(String theIdRegistro, Connection conn) throws DAOException {

		Recepcion anItem = null;

		String sql = " select r."+RecepcionVO.MATRICULA+", "; 
		sql+="  r."+RecepcionVO.IDREGISTRO+" as "+RecepcionVO.IDREGISTRO+", ";
		sql+="  "+RecepcionVO.MERCANCIA+" as "+RecepcionVO.MERCANCIA+", ";
		sql+="  "+RecepcionVO.EMPRESA+" as "+RecepcionVO.EMPRESA+", ";
		sql+="  to_char(p.fechaprevistaentrada, 'dd/mm/yyyy HH24:mi') as "+RecepcionVO.FECHA_PREVISION+", ";  
		sql+="  to_char(r.fechahoraregistro, 'dd/mm/yyyy HH24:mi') as "+RecepcionVO.FECHA_REGISTRO+", ";
		sql+="  to_char(ae.fechahorainicioae, 'dd/mm/yyyy HH24:mi') as "+RecepcionVO.FECHA_AUTORIZACION_ENTRADA+", ";
		sql+="  to_char(ce.fechahoraapertura, 'dd/mm/yyyy HH24:mi') as "+RecepcionVO.FECHA_CONTROL_ENTRADA+", ";
		sql+="  to_char(asa.fechahorainicioas, 'dd/mm/yyyy HH24:mi') as "+RecepcionVO.FECHA_AUTORIZACION_SALIDA+", ";
		sql+="  to_char(cs.fechahoraapertura, 'dd/mm/yyyy HH24:mi') as "+RecepcionVO.FECHA_CONTROL_SALIDA+", ";
		sql+="  trunc((ae.fechahorainicioae - r.fechahoraregistro) * 24 * 60) AS mins_fuera, ";
		sql+="  trunc((cs.fechahoraapertura - ae.fechahorainicioae) * 24 * 60) AS mins_dentro, ";
		sql+="  trunc((cs.fechahoraapertura - r.fechahoraregistro) * 24 * 60) AS mins_visita ";		
		sql+=" from previsiones p, registros r, autorizacionesentradas ae, autorizacionessalidas asa, controlentrada ce, controlsalida cs, m_tipovehiculos tv ";
		sql+=" where  ";
		sql+="  r.idregistro = "+theIdRegistro+" and ";
		sql+="  r.codtipoacceso = 'R' and ";
		sql+="  r.codtipovehiculo = tv.codtipovehiculo and ";
		sql+="  r.idprevision = p.idprevision(+) and ";
		sql+="  r.idregistro = ae.idregistro(+) and ";
		sql+="  r.idregistro = asa.idregistro(+) and ";
		sql+="  r.idregistro = ce.idregistro(+) and ";
		sql+="  r.idregistro = cs.idregistro(+) ";
		sql+=" order by r.fechahoraregistro ";
		
		
		setConexion(conn);
		executeQuery(sql);

		try {
			
			ResultSet rs = getResultSet();
			if (rs.next()) {
				anItem = new Recepcion();
				
				
				anItem.setMatricula(rs.getString(RecepcionVO.MATRICULA)); //	VARCHAR2
				anItem.setIdregistro(rs.getString(RecepcionVO.IDREGISTRO)); //	VARCHAR2
				anItem.setMercancia(rs.getString(RecepcionVO.MERCANCIA)); //	VARCHAR2
				anItem.setEmpresa(rs.getString(RecepcionVO.EMPRESA)); //	VARCHAR2
				
				anItem.setdPrev(rs.getString(RecepcionVO.FECHA_PREVISION)); 
				anItem.setdReg(rs.getString(RecepcionVO.FECHA_REGISTRO)); 
				anItem.setdAutEnt(rs.getString(RecepcionVO.FECHA_AUTORIZACION_ENTRADA)); 
				anItem.setdEnt(rs.getString(RecepcionVO.FECHA_CONTROL_ENTRADA)); 
				anItem.setdAutSal(rs.getString(RecepcionVO.FECHA_AUTORIZACION_SALIDA)); 
				anItem.setdSal(rs.getString(RecepcionVO.FECHA_CONTROL_SALIDA)); 
				
				anItem.setMinsFuera(rs.getString(RecepcionVO.MINS_FUERA));
				anItem.setMinsDentro(rs.getString(RecepcionVO.MINS_DENTRO));
				anItem.setMinsVisita(rs.getString(RecepcionVO.MINS_VISITA));
				
				//System.out.println(rs.getString(RecepcionVO.MATRICULA)+"-------->"+anItem.toString());

			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			releaseResourcesQuery();
		}
		
		return anItem;
		
		
	}	

	

}

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
import org.btb.analyticsws.db.vo.ExpedicionVO;
import org.btb.analyticsws.jaxb.model.Expedicion;

import java.sql.*;

public class ExpedicionDAO extends GenericDAO {
	
	

	public ArrayList<Expedicion> getAllItems(Connection conn) throws DAOException {

		ArrayList<Expedicion> itemList = new ArrayList<Expedicion>();
		
		String sql = " select r."+ExpedicionVO.MATRICULA+", "; 
		sql+="  r."+ExpedicionVO.IDREGISTRO+" as "+ExpedicionVO.IDREGISTRO+", ";
		sql+="  "+ExpedicionVO.MERCANCIA+" as "+ExpedicionVO.MERCANCIA+", ";
		sql+="  "+ExpedicionVO.PRECINTO+" as "+ExpedicionVO.PRECINTO+", ";
		sql+="  r."+ExpedicionVO.DESTINO+" as "+ExpedicionVO.DESTINO+", ";
		sql+="  to_char(p.fechaprevistaentrada, 'dd/mm/yyyy HH24:mi') as "+ExpedicionVO.FECHA_PREVISION+", ";  
		sql+="  to_char(r.fechahoraregistro, 'dd/mm/yyyy HH24:mi') as "+ExpedicionVO.FECHA_REGISTRO+", ";
		sql+="  to_char(ae.fechahorainicioae, 'dd/mm/yyyy HH24:mi') as "+ExpedicionVO.FECHA_AUTORIZACION_ENTRADA+", ";
		sql+="  to_char(ce.fechahoraapertura, 'dd/mm/yyyy HH24:mi') as "+ExpedicionVO.FECHA_CONTROL_ENTRADA+", ";
		sql+="  to_char(asa.fechahorainicioas, 'dd/mm/yyyy HH24:mi') as "+ExpedicionVO.FECHA_AUTORIZACION_SALIDA+", ";
		sql+="  to_char(cs.fechahoraapertura, 'dd/mm/yyyy HH24:mi') as "+ExpedicionVO.FECHA_CONTROL_SALIDA+", ";		
		sql+="  trunc((ae.fechahorainicioae - r.fechahoraregistro) * 24 * 60) AS mins_fuera, ";
		sql+="  trunc((cs.fechahoraapertura - ae.fechahorainicioae) * 24 * 60) AS mins_dentro, ";
		sql+="  trunc((cs.fechahoraapertura - r.fechahoraregistro) * 24 * 60) AS mins_visita ";
		sql+=" from previsiones p, registros r, autorizacionesentradas ae, autorizacionessalidas asa, controlentrada ce, controlsalida cs, m_tipovehiculos tv ";
		sql+=" where  ";
		sql+="  r.codtipoacceso = 'E' and ";
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
			Expedicion anItem = null;
			while (rs.next()) {
				anItem = new Expedicion();
				
				anItem.setMatricula(rs.getString(ExpedicionVO.MATRICULA)); //	VARCHAR2
				anItem.setIdregistro(rs.getString(ExpedicionVO.IDREGISTRO)); //	VARCHAR2
				anItem.setMercancia(rs.getString(ExpedicionVO.MERCANCIA)); //	VARCHAR2
				anItem.setPrecinto(rs.getString(ExpedicionVO.PRECINTO)); //	VARCHAR2
				anItem.setDestino(rs.getString(ExpedicionVO.DESTINO)); //	VARCHAR2
				
				anItem.setdPrev(rs.getString(ExpedicionVO.FECHA_PREVISION)); 
				anItem.setdReg(rs.getString(ExpedicionVO.FECHA_REGISTRO)); 
				anItem.setdAutEnt(rs.getString(ExpedicionVO.FECHA_AUTORIZACION_ENTRADA)); 
				anItem.setdEnt(rs.getString(ExpedicionVO.FECHA_CONTROL_ENTRADA)); 
				anItem.setdAutSal(rs.getString(ExpedicionVO.FECHA_AUTORIZACION_SALIDA)); 
				anItem.setdSal(rs.getString(ExpedicionVO.FECHA_CONTROL_SALIDA));
				
				anItem.setMinsFuera(rs.getString(ExpedicionVO.MINS_FUERA));
				anItem.setMinsDentro(rs.getString(ExpedicionVO.MINS_DENTRO));
				anItem.setMinsVisita(rs.getString(ExpedicionVO.MINS_VISITA));
			
				//System.out.println(rs.getString(ExpedicionVO.MATRICULA)+"-------->"+anItem.toString());
				
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
	
	
	
	public Expedicion getItem(String theIdRegistro, Connection conn) throws DAOException {

		Expedicion anItem = null;

		String sql = " select r."+ExpedicionVO.MATRICULA+", "; 
		sql+="  r."+ExpedicionVO.IDREGISTRO+" as "+ExpedicionVO.IDREGISTRO+", ";
		sql+="  "+ExpedicionVO.MERCANCIA+" as "+ExpedicionVO.MERCANCIA+", ";
		sql+="  "+ExpedicionVO.PRECINTO+" as "+ExpedicionVO.PRECINTO+", ";
		sql+="  r."+ExpedicionVO.DESTINO+" as "+ExpedicionVO.DESTINO+", ";
		sql+="  to_char(p.fechaprevistaentrada, 'dd/mm/yyyy HH24:mi') as "+ExpedicionVO.FECHA_PREVISION+", ";  
		sql+="  to_char(r.fechahoraregistro, 'dd/mm/yyyy HH24:mi') as "+ExpedicionVO.FECHA_REGISTRO+", ";
		sql+="  to_char(ae.fechahorainicioae, 'dd/mm/yyyy HH24:mi') as "+ExpedicionVO.FECHA_AUTORIZACION_ENTRADA+", ";
		sql+="  to_char(ce.fechahoraapertura, 'dd/mm/yyyy HH24:mi') as "+ExpedicionVO.FECHA_CONTROL_ENTRADA+", ";
		sql+="  to_char(asa.fechahorainicioas, 'dd/mm/yyyy HH24:mi') as "+ExpedicionVO.FECHA_AUTORIZACION_SALIDA+", ";
		sql+="  to_char(cs.fechahoraapertura, 'dd/mm/yyyy HH24:mi') as "+ExpedicionVO.FECHA_CONTROL_SALIDA+", ";
		sql+="  trunc((ae.fechahorainicioae - r.fechahoraregistro) * 24 * 60) AS mins_fuera, ";
		sql+="  trunc((cs.fechahoraapertura - ae.fechahorainicioae) * 24 * 60) AS mins_dentro, ";
		sql+="  trunc((cs.fechahoraapertura - r.fechahoraregistro) * 24 * 60) AS mins_visita ";		
		sql+=" from previsiones p, registros r, autorizacionesentradas ae, autorizacionessalidas asa, controlentrada ce, controlsalida cs, m_tipovehiculos tv ";
		sql+=" where  ";
		sql+="  r.idregistro = "+theIdRegistro+" and ";
		sql+="  r.codtipoacceso = 'E' and ";
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
				anItem = new Expedicion();
				
				
				anItem.setMatricula(rs.getString(ExpedicionVO.MATRICULA)); //	VARCHAR2
				anItem.setIdregistro(rs.getString(ExpedicionVO.IDREGISTRO)); //	VARCHAR2
				anItem.setMercancia(rs.getString(ExpedicionVO.MERCANCIA)); //	VARCHAR2
				anItem.setPrecinto(rs.getString(ExpedicionVO.PRECINTO)); //	VARCHAR2
				anItem.setDestino(rs.getString(ExpedicionVO.DESTINO)); //	VARCHAR2
				
				anItem.setdPrev(rs.getString(ExpedicionVO.FECHA_PREVISION)); 
				anItem.setdReg(rs.getString(ExpedicionVO.FECHA_REGISTRO)); 
				anItem.setdAutEnt(rs.getString(ExpedicionVO.FECHA_AUTORIZACION_ENTRADA)); 
				anItem.setdEnt(rs.getString(ExpedicionVO.FECHA_CONTROL_ENTRADA)); 
				anItem.setdAutSal(rs.getString(ExpedicionVO.FECHA_AUTORIZACION_SALIDA)); 
				anItem.setdSal(rs.getString(ExpedicionVO.FECHA_CONTROL_SALIDA)); 
				
				anItem.setMinsFuera(rs.getString(ExpedicionVO.MINS_FUERA));
				anItem.setMinsDentro(rs.getString(ExpedicionVO.MINS_DENTRO));
				anItem.setMinsVisita(rs.getString(ExpedicionVO.MINS_VISITA));
				
				//System.out.println(rs.getString(ExpedicionVO.MATRICULA)+"-------->"+anItem.toString());

			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			releaseResourcesQuery();
		}
		
		return anItem;
		
		
	}	

	

}

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

import org.btb.analyticsws.Constants;
import org.btb.analyticsws.db.dao.DAOException;
import org.btb.analyticsws.db.vo.VoyVO;
import org.btb.analyticsws.jaxb.model.Voy;

import java.sql.*;

public class VoyDAO extends GenericDAO {
	
	

	public ArrayList<Voy> getAllItems(Connection conn) throws DAOException {

		ArrayList<Voy> itemList = new ArrayList<Voy>();
		String sql = "SELECT * FROM " + VoyVO.TABLENAME + " ORDER BY " + VoyVO.DATP_DEP_PLAN;
		//String sql = "SELECT * FROM " + VoyVO.TABLENAME + " WHERE "+VoyVO.SEQ_VOYAGE+" LIKE '%4444%' ORDER BY " + VoyVO.DATP_DEP_PLAN;
		
		setConexion(conn);
		executeQuery(sql);

		try {
			ResultSet rs = getResultSet();
			Voy anItem = null;
			while (rs.next()) {
				anItem = new Voy();
				
				anItem.setSeq_voyage(rs.getString(VoyVO.SEQ_VOYAGE)); //	VARCHAR2
				anItem.setAge(rs.getString(VoyVO.AGE)); //	VARCHAR2
				anItem.setCvoy(rs.getString(VoyVO.CVOY)); //	VARCHAR2
				anItem.setSeq_section(rs.getString(VoyVO.SEQ_SECTION)); //	VARCHAR2
				anItem.setLib_voyage(rs.getString(VoyVO.LIB_VOYAGE)); //	VARCHAR2
				anItem.setDatp_dep_plan(rs.getDate(VoyVO.DATP_DEP_PLAN)); //	DATE
				anItem.setDuree(rs.getInt(VoyVO.DUREE)); //	NUMBER
				anItem.setDist_tot(rs.getInt(VoyVO.DIST_TOT)); //	NUMBER
				anItem.setDist_vide(rs.getInt(VoyVO.DIST_VIDE)); //	NUMBER
				anItem.setDesc_voy(rs.getString(VoyVO.DESC_VOY)); //	VARCHAR2
				anItem.setGroupe_voy(rs.getString(VoyVO.GROUPE_VOY)); //	VARCHAR2
				anItem.setCc_metier(rs.getString(VoyVO.CC_METIER)); //	VARCHAR2
				anItem.setDist_tot_reel(rs.getInt(VoyVO.DIST_TOT_REEL)); //	NUMBER
				anItem.setEtat_voy_mtrack(rs.getString(VoyVO.ETAT_VOY_MTRACK)); //	VARCHAR2
				anItem.setCod_terminal(rs.getString(VoyVO.COD_TERMINAL)); //	VARCHAR2
				anItem.setHora_carga(rs.getDate(VoyVO.HORA_CARGA)); //	DATE
				anItem.setActualizado(rs.getString(VoyVO.ACTUALIZADO)); //	VARCHAR2
				anItem.setSeq_ress_chauf(rs.getString(VoyVO.SEQ_RESS_CHAUF)); //	VARCHAR2
				anItem.setLib_ress_chauf(rs.getString(VoyVO.LIB_RESS_CHAUF)); //	VARCHAR2
				anItem.setSeq_ress_st(rs.getString(VoyVO.SEQ_RESS_ST)); //	VARCHAR2
				anItem.setLib_ress_st(rs.getString(VoyVO.LIB_RESS_ST)); //	VARCHAR2
				anItem.setFecha_actualizacion(rs.getDate(VoyVO.FECHA_ACTUALIZACION)); //	DATE
				anItem.setFecha_creacion(rs.getDate(VoyVO.FECHA_CREACION)); //	DATE
				anItem.setFecha_modificacion(rs.getDate(VoyVO.FECHA_MODIFICACION)); //	DATE
				anItem.setIncidencias_viaje(rs.getString(VoyVO.INCIDENCIAS_VIAJE)); //	VARCHAR2
				anItem.setTyp_parcours(rs.getString(VoyVO.TYP_PARCOURS)); //	VARCHAR2
				anItem.setCod_agencia(rs.getString(VoyVO.COD_AGENCIA)); //	VARCHAR2
				anItem.setSeq_voy_lie(rs.getString(VoyVO.SEQ_VOY_LIE)); //	VARCHAR2
				
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
	
	
	
	public Voy getItem(String sSeqVoyage, Connection conn) throws DAOException {

		Voy anItem = null;
		String sql = "SELECT * FROM " + VoyVO.TABLENAME + " WHERE "+VoyVO.SEQ_VOYAGE+"='"+sSeqVoyage+"' ORDER BY " + VoyVO.DATP_DEP_PLAN;
		setConexion(conn);
		executeQuery(sql);

		try {
			
			ResultSet rs = getResultSet();
			if (rs.next()) {
				anItem = new Voy();
				
				
				anItem.setSeq_voyage(rs.getString(VoyVO.SEQ_VOYAGE)); //	VARCHAR2
				anItem.setAge(rs.getString(VoyVO.AGE)); //	VARCHAR2
				anItem.setCvoy(rs.getString(VoyVO.CVOY)); //	VARCHAR2
				anItem.setSeq_section(rs.getString(VoyVO.SEQ_SECTION)); //	VARCHAR2
				anItem.setLib_voyage(rs.getString(VoyVO.LIB_VOYAGE)); //	VARCHAR2
				anItem.setDatp_dep_plan(rs.getDate(VoyVO.DATP_DEP_PLAN)); //	DATE
				anItem.setDuree(rs.getInt(VoyVO.DUREE)); //	NUMBER
				anItem.setDist_tot(rs.getInt(VoyVO.DIST_TOT)); //	NUMBER
				anItem.setDist_vide(rs.getInt(VoyVO.DIST_VIDE)); //	NUMBER
				anItem.setDesc_voy(rs.getString(VoyVO.DESC_VOY)); //	VARCHAR2
				anItem.setGroupe_voy(rs.getString(VoyVO.GROUPE_VOY)); //	VARCHAR2
				anItem.setCc_metier(rs.getString(VoyVO.CC_METIER)); //	VARCHAR2
				anItem.setDist_tot_reel(rs.getInt(VoyVO.DIST_TOT_REEL)); //	NUMBER
				anItem.setEtat_voy_mtrack(rs.getString(VoyVO.ETAT_VOY_MTRACK)); //	VARCHAR2
				anItem.setCod_terminal(rs.getString(VoyVO.COD_TERMINAL)); //	VARCHAR2
				anItem.setHora_carga(rs.getDate(VoyVO.HORA_CARGA)); //	DATE
				anItem.setActualizado(rs.getString(VoyVO.ACTUALIZADO)); //	VARCHAR2
				anItem.setSeq_ress_chauf(rs.getString(VoyVO.SEQ_RESS_CHAUF)); //	VARCHAR2
				anItem.setLib_ress_chauf(rs.getString(VoyVO.LIB_RESS_CHAUF)); //	VARCHAR2
				anItem.setSeq_ress_st(rs.getString(VoyVO.SEQ_RESS_ST)); //	VARCHAR2
				anItem.setLib_ress_st(rs.getString(VoyVO.LIB_RESS_ST)); //	VARCHAR2
				anItem.setFecha_actualizacion(rs.getDate(VoyVO.FECHA_ACTUALIZACION)); //	DATE
				anItem.setFecha_creacion(rs.getDate(VoyVO.FECHA_CREACION)); //	DATE
				anItem.setFecha_modificacion(rs.getDate(VoyVO.FECHA_MODIFICACION)); //	DATE
				anItem.setIncidencias_viaje(rs.getString(VoyVO.INCIDENCIAS_VIAJE)); //	VARCHAR2
				anItem.setTyp_parcours(rs.getString(VoyVO.TYP_PARCOURS)); //	VARCHAR2
				anItem.setCod_agencia(rs.getString(VoyVO.COD_AGENCIA)); //	VARCHAR2
				anItem.setSeq_voy_lie(rs.getString(VoyVO.SEQ_VOY_LIE)); //	VARCHAR2
				

			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			releaseResourcesQuery();
		}
		
		return anItem;
		
		
	}	

	
	public int insertItem(Voy v, Connection conn)
			throws DAOException {
		
		int operation = Constants.OPERATION_SUCCEED;		
		StringBuffer sql = new StringBuffer();

		
		sql.append("INSERT INTO "+VoyVO.TABLENAME+" ("+
		
				VoyVO.DUREE+", "+
				VoyVO.DIST_TOT+", "+
				VoyVO.DIST_VIDE+ ", " +
				VoyVO.DIST_TOT_REEL+ " "); // Watchout. There is no comma here on purpose

		if(v.getSeq_voyage()!=null)
			sql.append(", "+VoyVO.SEQ_VOYAGE);		
		
		if(v.getAge()!=null)
			sql.append(", "+VoyVO.AGE);
		
		if(v.getCvoy()!=null)
			sql.append(", "+VoyVO.CVOY);
		
		if (v.getLib_voyage()!=null)
			sql.append(", "+VoyVO.LIB_VOYAGE);
		
		if(v.getDesc_voy()!=null)
			sql.append(", "+VoyVO.DESC_VOY);
		
		if (v.getGroupe_voy()!=null)
			sql.append(", "+VoyVO.GROUPE_VOY);
		
		if(v.getCc_metier()!=null)
			sql.append(", "+VoyVO.CC_METIER);		
		
		try{
			String sDateAux = fechaHoraFormat.format(v.getHora_carga());			
			sql.append(", "+VoyVO.HORA_CARGA);
		}catch(Exception e){}		
		
		if(v.getSeq_section()!=null)		
			sql.append(", "+VoyVO.SEQ_SECTION);
		
		try{
			String sDateAux = fechaHoraFormat.format(v.getDatp_dep_plan());			
			sql.append(", "+VoyVO.DATP_DEP_PLAN);
		}catch(Exception e){}
		
		
		if(v.getEtat_voy_mtrack()!=null)
			sql.append(", "+VoyVO.ETAT_VOY_MTRACK);
		
		if(v.getCod_terminal()!=null)
			sql.append(", "+VoyVO.COD_TERMINAL);
		
		if(v.getActualizado()!=null)
			sql.append(", "+VoyVO.ACTUALIZADO);
		
		if(v.getSeq_ress_chauf()!=null)
			sql.append(", "+VoyVO.SEQ_RESS_CHAUF);
		
		if(v.getLib_ress_chauf()!=null)
			sql.append(", "+VoyVO.LIB_RESS_CHAUF);
		
		if(v.getSeq_ress_st()!=null)
			sql.append(", "+VoyVO.SEQ_RESS_ST);
		
		if(v.getLib_ress_st()!=null)
			sql.append(", "+VoyVO.LIB_RESS_ST);
				
		try{
			String sDateAux = fechaHoraFormat.format(v.getFecha_actualizacion());			
			sql.append(", "+VoyVO.FECHA_ACTUALIZACION);
		}catch(Exception e){}
		
		try{
			String sDateAux = fechaHoraFormat.format(v.getFecha_creacion());			
			sql.append(", "+VoyVO.FECHA_CREACION);
		}catch(Exception e){}

		try{
			String sDateAux = fechaHoraFormat.format(v.getFecha_modificacion());			
			sql.append(", "+VoyVO.FECHA_MODIFICACION);
		}catch(Exception e){}
		
		if(v.getIncidencias_viaje()!=null)
			sql.append(", "+VoyVO.INCIDENCIAS_VIAJE);
		
		if(v.getTyp_parcours()!=null)
			sql.append(", "+VoyVO.TYP_PARCOURS);
		
		if(v.getCod_agencia()!=null)
			sql.append(", "+VoyVO.COD_AGENCIA);
		
		if(v.getSeq_voy_lie()!=null)
			sql.append(", "+VoyVO.SEQ_VOY_LIE);
		
		sql.append(" ) VALUES ( ");


		
		sql.append(" "+v.getDuree()+", ");
		sql.append(" "+v.getDist_tot()+", ");
		sql.append(" "+v.getDist_vide()+", ");
		sql.append(" "+v.getDist_tot_reel()+" "); // Watchout, commma is missing here on purpose
		

		if(v.getSeq_voyage()!=null)
			sql.append(", '"+v.getSeq_voyage()+"' ");		
		
		if(v.getAge()!=null)
			sql.append(", '"+v.getAge()+"' ");
		
		if(v.getCvoy()!=null)
			sql.append(", '"+v.getCvoy()+"' ");
		
		if (v.getLib_voyage()!=null)
			sql.append(", '"+v.getLib_voyage()+"' ");
		
		if(v.getDesc_voy()!=null)
			sql.append(", '"+v.getDesc_voy()+"' ");
		
		if (v.getGroupe_voy()!=null)
			sql.append(", '"+v.getGroupe_voy()+"' ");
		
		if(v.getCc_metier()!=null)
			sql.append(", '"+v.getCc_metier()+"' ");
		
		try{
			String sDateAux = fechaHoraFormat.format(v.getHora_carga());			
			sql.append(", TO_DATE('"+sDateAux+"', 'dd/mm/yyyy hh24:mi') ");
		}catch(Exception e){}		
		
		if(v.getSeq_section()!=null)		
			sql.append(", '"+v.getSeq_section()+"' ");
		
		try{
			String sDateAux = fechaHoraFormat.format(v.getDatp_dep_plan());			
			sql.append(", TO_DATE('"+sDateAux+"', 'dd/mm/yyyy hh24:mi') ");
		}catch(Exception e){}
		
		
		
		if(v.getEtat_voy_mtrack()!=null)
			sql.append(", '"+v.getEtat_voy_mtrack()+"' ");
		
		if(v.getCod_terminal()!=null)
			sql.append(", '"+v.getCod_terminal()+"' ");
		
		if(v.getActualizado()!=null)
			sql.append(", '"+v.getActualizado()+"' ");
		
		if(v.getSeq_ress_chauf()!=null)
			sql.append(", '"+v.getSeq_ress_chauf()+"' ");
		
		if(v.getLib_ress_chauf()!=null)
			sql.append(", '"+v.getLib_ress_chauf()+"' ");
		
		if(v.getSeq_ress_st()!=null)
			sql.append(", '"+v.getSeq_ress_st()+"' ");
		
		if(v.getLib_ress_st()!=null)
			sql.append(", '"+v.getLib_ress_st()+"' ");
				
		try{
			String sDateAux = fechaHoraFormat.format(v.getFecha_actualizacion());			
			sql.append(", TO_DATE('"+sDateAux+"', 'dd/mm/yyyy hh24:mi') ");
		}catch(Exception e){}
		
		try{
			String sDateAux = fechaHoraFormat.format(v.getFecha_creacion());			
			sql.append(", TO_DATE('"+sDateAux+"', 'dd/mm/yyyy hh24:mi') ");
		}catch(Exception e){}

		try{
			String sDateAux = fechaHoraFormat.format(v.getFecha_modificacion());			
			sql.append(", TO_DATE('"+sDateAux+"', 'dd/mm/yyyy hh24:mi') ");
		}catch(Exception e){}
		
		if(v.getIncidencias_viaje()!=null)
			sql.append(", '"+v.getIncidencias_viaje()+"' ");
		
		if(v.getTyp_parcours()!=null)
			sql.append(",'"+v.getTyp_parcours()+"' ");
		
		if(v.getCod_agencia()!=null)
			sql.append(", '"+v.getCod_agencia()+"' ");
		
		if(v.getSeq_voy_lie()!=null)
			sql.append(", '"+v.getSeq_voy_lie()+"' ");		
		
		
		sql.append(" )");
		
		setConexion(conn);
		
		try{
			int resul = executeUpdate(sql.toString());			
			System.out.println("Number of rows affected by the insert "+resul+ ". 0 means no effect");

			if(resul == 0){
				operation = Constants.OPERATION_FAILED;
				System.out.println("Could not create item into table "+VoyVO.TABLENAME+" with id"+v.getSeq_voyage()+".");
				throw new DAOException("Could not create item into table "+VoyVO.TABLENAME+" with id"+v.getSeq_voyage()+".");				
			}		
		}finally{
			releaseResourcesUpdate();
		}		
		return operation;		
		
	}
	
	
	
	public int insertItem(String seqvoyage, String age, String cvoy, String seqsection, String libvoyage, Connection conn)
			throws DAOException {
		
		int operation = Constants.OPERATION_SUCCEED;		
		StringBuffer sql = new StringBuffer();

		sql.append("INSERT INTO "+VoyVO.TABLENAME+" ("+
				VoyVO.SEQ_VOYAGE+", "+
				VoyVO.AGE+", "+
				VoyVO.CVOY+ ", " +
				VoyVO.SEQ_SECTION+ ", " +
				VoyVO.LIB_VOYAGE+ ")");

		sql.append(" VALUES ( ");
		sql.append(" '"+seqvoyage+"', ");
		sql.append(" '"+age+"', ");
		sql.append(" '"+cvoy+"', ");
		sql.append(" '"+seqsection+"', ");
		sql.append(" '"+libvoyage+"' )");
		
		setConexion(conn);
		
		try{
			int resul = executeUpdate(sql.toString());
			
			System.out.println("Number of rows affected by the insert "+resul+ ". 0 means no effect");

			if(resul == 0){
				operation = Constants.OPERATION_FAILED;
				System.out.println("Could not create item into table "+VoyVO.TABLENAME+" with id"+seqvoyage+" en la BBDD");
				throw new DAOException("Could not create item into table "+VoyVO.TABLENAME+" with id"+seqvoyage+" en la BBDD");				
			}		
		}finally{
			releaseResourcesUpdate();
		}		
		return operation;
	}
	
	
	public int updateItem(Voy v, Connection conn)
			throws DAOException {
		
		int operation = Constants.OPERATION_SUCCEED;		
		StringBuffer sql = new StringBuffer();	
		
		sql.append("UPDATE "+VoyVO.TABLENAME+" SET ");
		
		sql.append(" "+VoyVO.DUREE+"="+v.getDuree()+", ");
		sql.append(" "+VoyVO.DIST_TOT+"="+v.getDist_tot()+", ");
		sql.append(" "+VoyVO.DIST_VIDE+"="+v.getDist_vide()+", ");
		sql.append(" "+VoyVO.DIST_TOT_REEL+"="+v.getDist_tot_reel()+" "); // Watch out! The comma might be missing ...

		
		if(v.getAge()!=null)
			sql.append(", "+VoyVO.AGE+"='"+v.getAge()+"' ");
		
		if(v.getCvoy()!=null)
			sql.append(", "+VoyVO.CVOY+"='"+v.getCvoy()+"' ");
		
		if (v.getLib_voyage()!=null)
			sql.append(", "+VoyVO.LIB_VOYAGE+"='"+v.getLib_voyage()+"' ");
		
		if(v.getDesc_voy()!=null)
			sql.append(", "+VoyVO.DESC_VOY+"='"+v.getDesc_voy()+"' ");
		
		if (v.getGroupe_voy()!=null)
			sql.append(", "+VoyVO.GROUPE_VOY+"='"+v.getGroupe_voy()+"' ");
		
		if(v.getCc_metier()!=null)
			sql.append(", "+VoyVO.CC_METIER+"='"+v.getCc_metier()+"' ");
		
		try{
			String sDateAux = fechaHoraFormat.format(v.getHora_carga());			
			sql.append(", "+VoyVO.HORA_CARGA+"=TO_DATE('"+sDateAux+"', 'dd/mm/yyyy hh24:mi') ");
		}catch(Exception e){}		
		
		if(v.getSeq_section()!=null)		
			sql.append(", "+VoyVO.SEQ_SECTION+"='"+v.getSeq_section()+"' ");
		
		try{
			String sDateAux = fechaHoraFormat.format(v.getDatp_dep_plan());			
			sql.append(", "+VoyVO.DATP_DEP_PLAN+"=TO_DATE('"+sDateAux+"', 'dd/mm/yyyy hh24:mi') ");
		}catch(Exception e){}
		
		
		
		if(v.getEtat_voy_mtrack()!=null)
			sql.append(", "+VoyVO.ETAT_VOY_MTRACK+"='"+v.getEtat_voy_mtrack()+"' ");
		
		if(v.getCod_terminal()!=null)
			sql.append(", "+VoyVO.COD_TERMINAL+"='"+v.getCod_terminal()+"' ");
		
		if(v.getActualizado()!=null)
			sql.append(", "+VoyVO.ACTUALIZADO+"='"+v.getActualizado()+"' ");
		
		if(v.getSeq_ress_chauf()!=null)
			sql.append(", "+VoyVO.SEQ_RESS_CHAUF+"='"+v.getSeq_ress_chauf()+"' ");
		
		if(v.getLib_ress_chauf()!=null)
			sql.append(", "+VoyVO.LIB_RESS_CHAUF+"='"+v.getLib_ress_chauf()+"' ");
		
		if(v.getSeq_ress_st()!=null)
			sql.append(", "+VoyVO.SEQ_RESS_ST+"='"+v.getSeq_ress_st()+"' ");
		
		if(v.getLib_ress_st()!=null)
			sql.append(", "+VoyVO.LIB_RESS_ST+"='"+v.getLib_ress_st()+"' ");
				
		try{
			String sDateAux = fechaHoraFormat.format(v.getFecha_actualizacion());			
			sql.append(", "+VoyVO.FECHA_ACTUALIZACION+"=TO_DATE('"+sDateAux+"', 'dd/mm/yyyy hh24:mi') ");
		}catch(Exception e){}
		
		try{
			String sDateAux = fechaHoraFormat.format(v.getFecha_creacion());			
			sql.append(", "+VoyVO.FECHA_CREACION+"=TO_DATE('"+sDateAux+"', 'dd/mm/yyyy hh24:mi') ");
		}catch(Exception e){}

		try{
			String sDateAux = fechaHoraFormat.format(v.getFecha_modificacion());			
			sql.append(", "+VoyVO.FECHA_MODIFICACION+"=TO_DATE('"+sDateAux+"', 'dd/mm/yyyy hh24:mi') ");
		}catch(Exception e){}
		
		if(v.getIncidencias_viaje()!=null)
			sql.append(", "+VoyVO.INCIDENCIAS_VIAJE+"='"+v.getIncidencias_viaje()+"' ");
		
		if(v.getTyp_parcours()!=null)
			sql.append(", "+VoyVO.TYP_PARCOURS+"='"+v.getTyp_parcours()+"' ");
		
		if(v.getCod_agencia()!=null)
			sql.append(", "+VoyVO.COD_AGENCIA+"='"+v.getCod_agencia()+"' ");
		
		if(v.getSeq_voy_lie()!=null)
			sql.append(", "+VoyVO.SEQ_VOY_LIE+"='"+v.getSeq_voy_lie()+"' ");
		
		sql.append(" WHERE "+VoyVO.SEQ_VOYAGE+" = '"+v.getSeq_voyage()+"'");

		
		setConexion(conn);
		
		try{
			int resul = executeUpdate(sql.toString());
			
			System.out.println("Number of rows affected by the insert "+resul+ ". 0 means no effect");

			if(resul == 0){
				operation = Constants.OPERATION_FAILED;
				System.out.println("Could not create item into table "+VoyVO.TABLENAME+" with id"+v.getSeq_voyage()+".");
				throw new DAOException("Could not create item into table "+VoyVO.TABLENAME+" with id"+v.getSeq_voyage()+".");				
			}		
		}finally{
			releaseResourcesUpdate();
		}		
		return operation;
	}
	
	
	
	public int deleteItem(String seqvoyage, Connection conn)
			throws DAOException {
		
		int operation = Constants.OPERATION_SUCCEED;		
		StringBuffer sql = new StringBuffer();

		sql.append("DELETE FROM "+VoyVO.TABLENAME+" WHERE "+VoyVO.SEQ_VOYAGE+"='"+seqvoyage+"'");

		setConexion(conn);
		
		try{
			int resul = executeUpdate(sql.toString());

			System.out.println("Number of rows affected by the delete "+resul+ ". 0 means no effect");
			
			if(resul == 0){
				operation = Constants.OPERATION_FAILED;
				System.out.println("Could not delete item from table "+VoyVO.TABLENAME+" with id"+seqvoyage+".");
				throw new DAOException("Could not create item into table "+VoyVO.TABLENAME+" with id"+seqvoyage+".");
				
			}
		}finally{
			releaseResourcesUpdate();
		}		
		return operation;
	}	
	

}

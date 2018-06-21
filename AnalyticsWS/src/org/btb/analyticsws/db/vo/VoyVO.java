/**
 * <p> Web Service Application Programming Interface</p>
 * 
 *

 * 
 * @author Bernardo Tabuenca Archilla
 * @version 1.0
 */
package org.btb.analyticsws.db.vo;




public class VoyVO {

	
	public VoyVO() {
	}
	
//	public VoyVO(String seq_voyage, String age, String cvoy, String seq_section, String lib_voyage, Date datp_dep_plan,
//			int duree, int dist_tot, int dist_vide, String desc_voy, String groupe_voy, String cc_metier,
//			int dist_tot_reel, String etat_voy_mtrack, String cod_terminal, Date hora_carga, String actualizado,
//			String seq_ress_chauf, String lib_ress_chauf, String seq_ress_st, String lib_ress_st,
//			Date fecha_actualizacion, Date fecha_creacion, Date fecha_modificacion, String incidencias_viaje,
//			String typ_parcours, String cod_agencia, String seq_voy_lie) {
//
//		Seq_voyage = seq_voyage;
//		Age = age;
//		Cvoy = cvoy;
//		Seq_section = seq_section;
//		Lib_voyage = lib_voyage;
//		Datp_dep_plan = datp_dep_plan;
//		Duree = duree;
//		Dist_tot = dist_tot;
//		Dist_vide = dist_vide;
//		Desc_voy = desc_voy;
//		Groupe_voy = groupe_voy;
//		Cc_metier = cc_metier;
//		Dist_tot_reel = dist_tot_reel;
//		Etat_voy_mtrack = etat_voy_mtrack;
//		Cod_terminal = cod_terminal;
//		Hora_carga = hora_carga;
//		Actualizado = actualizado;
//		Seq_ress_chauf = seq_ress_chauf;
//		Lib_ress_chauf = lib_ress_chauf;
//		Seq_ress_st = seq_ress_st;
//		Lib_ress_st = lib_ress_st;
//		Fecha_actualizacion = fecha_actualizacion;
//		Fecha_creacion = fecha_creacion;
//		Fecha_modificacion = fecha_modificacion;
//		Incidencias_viaje = incidencias_viaje;
//		Typ_parcours = typ_parcours;
//		Cod_agencia = cod_agencia;
//		Seq_voy_lie = seq_voy_lie;
//	}
	
	public static final String TABLENAME ="VOY";
	
	public static final String SEQ_VOY_LIE = "SEQ_VOY_LIE";
	public static final String COD_AGENCIA = "COD_AGENCIA";
	public static final String TYP_PARCOURS = "TYP_PARCOURS";
	public static final String INCIDENCIAS_VIAJE = "INCIDENCIAS_VIAJE";
	public static final String FECHA_MODIFICACION = "FECHA_MODIFICACION";
	public static final String FECHA_CREACION = "FECHA_CREACION";
	public static final String FECHA_ACTUALIZACION = "FECHA_ACTUALIZACION";
	public static final String LIB_RESS_ST = "LIB_RESS_ST";
	public static final String SEQ_RESS_ST = "SEQ_RESS_ST";
	public static final String LIB_RESS_CHAUF = "LIB_RESS_CHAUF";
	public static final String SEQ_RESS_CHAUF = "SEQ_RESS_CHAUF";
	public static final String ACTUALIZADO = "ACTUALIZADO";
	public static final String HORA_CARGA = "HORA_CARGA";
	public static final String COD_TERMINAL = "COD_TERMINAL";
	public static final String ETAT_VOY_MTRACK = "ETAT_VOY_MTRACK";
	public static final String DIST_TOT_REEL = "DIST_TOT_REEL";
	public static final String CC_METIER = "CC_METIER";
	public static final String GROUPE_VOY = "GROUPE_VOY";
	public static final String DESC_VOY = "DESC_VOY";
	public static final String DIST_VIDE = "DIST_VIDE";
	public static final String DIST_TOT = "DIST_TOT";
	public static final String DUREE = "DUREE";
	public static final String DATP_DEP_PLAN = "DATP_DEP_PLAN";
	public static final String LIB_VOYAGE = "LIB_VOYAGE";
	public static final String SEQ_SECTION = "SEQ_SECTION";
	public static final String CVOY = "CVOY";
	public static final String AGE = "AGE";
	public static final String SEQ_VOYAGE = "SEQ_VOYAGE";
//	
//
//	
//	private String Seq_voyage = null; //	Varchar2
//	private String Age = null; //	Varchar2
//	private String Cvoy = null; //	Varchar2
//	private String Seq_section = null; //	Varchar2
//	private String Lib_voyage = null; //	Varchar2
//	private Date Datp_dep_plan = null; //	Date
//	private int Duree = -1; //	Number
//	private int Dist_tot = -1; //	Number
//	private int Dist_vide = -1; //	Number
//	private String Desc_voy = null; //	Varchar2
//	private String Groupe_voy = null; //	Varchar2
//	private String Cc_metier = null; //	Varchar2
//	private int Dist_tot_reel = -1; //	Number
//	private String Etat_voy_mtrack = null; //	Varchar2
//	private String Cod_terminal = null; //	Varchar2
//	private Date Hora_carga = null; //	Date
//	private String Actualizado = null; //	Varchar2
//	private String Seq_ress_chauf = null; //	Varchar2
//	private String Lib_ress_chauf = null; //	Varchar2
//	private String Seq_ress_st = null; //	Varchar2
//	private String Lib_ress_st = null; //	Varchar2
//	private Date Fecha_actualizacion = null; //	Date
//	private Date Fecha_creacion = null; //	Date
//	private Date Fecha_modificacion = null; //	Date
//	private String Incidencias_viaje = null; //	Varchar2
//	private String Typ_parcours = null; //	Varchar2
//	private String Cod_agencia = null; //	Varchar2
//	private String Seq_voy_lie = null; //	Varchar2
//	
	
//	// Key getter added for jaxb parsing
//	public String getId() {
//		return Seq_voyage;
//	}	
//	
//	
//	
//	public String getSeq_voyage() {
//		return Seq_voyage;
//	}
//	public void setSeq_voyage(String seq_voyage) {
//		Seq_voyage = seq_voyage;
//	}
//	public String getAge() {
//		return Age;
//	}
//	public void setAge(String age) {
//		Age = age;
//	}
//	public String getCvoy() {
//		return Cvoy;
//	}
//	public void setCvoy(String cvoy) {
//		Cvoy = cvoy;
//	}
//	public String getSeq_section() {
//		return Seq_section;
//	}
//	public void setSeq_section(String seq_section) {
//		Seq_section = seq_section;
//	}
//	public String getLib_voyage() {
//		return Lib_voyage;
//	}
//	public void setLib_voyage(String lib_voyage) {
//		Lib_voyage = lib_voyage;
//	}
//	public Date getDatp_dep_plan() {
//		return Datp_dep_plan;
//	}
//	public void setDatp_dep_plan(Date datp_dep_plan) {
//		Datp_dep_plan = datp_dep_plan;
//	}
//	public int getDuree() {
//		return Duree;
//	}
//	public void setDuree(int duree) {
//		Duree = duree;
//	}
//	public int getDist_tot() {
//		return Dist_tot;
//	}
//	public void setDist_tot(int dist_tot) {
//		Dist_tot = dist_tot;
//	}
//	public int getDist_vide() {
//		return Dist_vide;
//	}
//	public void setDist_vide(int dist_vide) {
//		Dist_vide = dist_vide;
//	}
//	public String getDesc_voy() {
//		return Desc_voy;
//	}
//	public void setDesc_voy(String desc_voy) {
//		Desc_voy = desc_voy;
//	}
//	public String getGroupe_voy() {
//		return Groupe_voy;
//	}
//	public void setGroupe_voy(String groupe_voy) {
//		Groupe_voy = groupe_voy;
//	}
//	public String getCc_metier() {
//		return Cc_metier;
//	}
//	public void setCc_metier(String cc_metier) {
//		Cc_metier = cc_metier;
//	}
//	public int getDist_tot_reel() {
//		return Dist_tot_reel;
//	}
//	public void setDist_tot_reel(int dist_tot_reel) {
//		Dist_tot_reel = dist_tot_reel;
//	}
//	public String getEtat_voy_mtrack() {
//		return Etat_voy_mtrack;
//	}
//	public void setEtat_voy_mtrack(String etat_voy_mtrack) {
//		Etat_voy_mtrack = etat_voy_mtrack;
//	}
//	public String getCod_terminal() {
//		return Cod_terminal;
//	}
//	public void setCod_terminal(String cod_terminal) {
//		Cod_terminal = cod_terminal;
//	}
//	public Date getHora_carga() {
//		return Hora_carga;
//	}
//	public void setHora_carga(Date hora_carga) {
//		Hora_carga = hora_carga;
//	}
//	public String getActualizado() {
//		return Actualizado;
//	}
//	public void setActualizado(String actualizado) {
//		Actualizado = actualizado;
//	}
//	public String getSeq_ress_chauf() {
//		return Seq_ress_chauf;
//	}
//	public void setSeq_ress_chauf(String seq_ress_chauf) {
//		Seq_ress_chauf = seq_ress_chauf;
//	}
//	public String getLib_ress_chauf() {
//		return Lib_ress_chauf;
//	}
//	public void setLib_ress_chauf(String lib_ress_chauf) {
//		Lib_ress_chauf = lib_ress_chauf;
//	}
//	public String getSeq_ress_st() {
//		return Seq_ress_st;
//	}
//	public void setSeq_ress_st(String seq_ress_st) {
//		Seq_ress_st = seq_ress_st;
//	}
//	public String getLib_ress_st() {
//		return Lib_ress_st;
//	}
//	public void setLib_ress_st(String lib_ress_st) {
//		Lib_ress_st = lib_ress_st;
//	}
//	public Date getFecha_actualizacion() {
//		return Fecha_actualizacion;
//	}
//	public void setFecha_actualizacion(Date fecha_actualizacion) {
//		Fecha_actualizacion = fecha_actualizacion;
//	}
//	public Date getFecha_creacion() {
//		return Fecha_creacion;
//	}
//	public void setFecha_creacion(Date fecha_creacion) {
//		Fecha_creacion = fecha_creacion;
//	}
//	public Date getFecha_modificacion() {
//		return Fecha_modificacion;
//	}
//	public void setFecha_modificacion(Date fecha_modificacion) {
//		Fecha_modificacion = fecha_modificacion;
//	}
//	public String getIncidencias_viaje() {
//		return Incidencias_viaje;
//	}
//	public void setIncidencias_viaje(String incidencias_viaje) {
//		Incidencias_viaje = incidencias_viaje;
//	}
//	public String getTyp_parcours() {
//		return Typ_parcours;
//	}
//	public void setTyp_parcours(String typ_parcours) {
//		Typ_parcours = typ_parcours;
//	}
//	public String getCod_agencia() {
//		return Cod_agencia;
//	}
//	public void setCod_agencia(String cod_agencia) {
//		Cod_agencia = cod_agencia;
//	}
//	public String getSeq_voy_lie() {
//		return Seq_voy_lie;
//	}
//	public void setSeq_voy_lie(String seq_voy_lie) {
//		Seq_voy_lie = seq_voy_lie;
//	}
//	

	
}

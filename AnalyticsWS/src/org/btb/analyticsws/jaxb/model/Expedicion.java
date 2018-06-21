/**
 * <p> Web Service Application Programming Interface</p>
 * 
 *

 * 
 * @author Bernardo Tabuenca Archilla
 * @version 1.0
 */
package org.btb.analyticsws.jaxb.model;


import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
// JAX-RS supports automatic mapping from JAXB annotated class to XML and JSON

public class Expedicion {
	
	 

	//
	// 8795GLF	265	Congelado	7777	05/05/2016 00:00	05/05/2016 09:22	05/05/2016 09:22	05/05/2016 09:26	05/05/2016 09:44	05/05/2016 09:44
	//
	//	select r.matricula, 
	//	  r.idregistro,
	//	  descripciontipovehiculo,
	//	  codigoprecinto,
	//	  p.fechaprevistaentrada as f_prev,  
	//	  r.fechahoraregistro as f_registro,
	//	  ae.fechahorainicioae as f_auten,
	//	  ce.fechahoraapertura as f_conten,
	//	  asa.fechahorainicioas as f_ausal,
	//	  cs.fechahoraapertura as f_contsal
	//	from previsiones p, registros r, autorizacionesentradas ae, autorizacionessalidas asa, controlentrada ce, controlsalida cs, m_tipovehiculos tv
	//	where 
	//	  r.codtipoacceso = 'E' and
	//	  r.codtipovehiculo = tv.codtipovehiculo and
	//	  r.idprevision = p.idprevision(+) and
	//	  r.idregistro = ae.idregistro(+) and
	//	  r.idregistro = asa.idregistro(+) and
	//	  r.idregistro = ce.idregistro(+) and
	//	  r.idregistro = cs.idregistro(+)
	//	order by r.fechahoraregistro;
	
	
	private String matricula;
	private String idregistro;
	private String mercancia;
	private String precinto;
	private String destino;

	private String dPrev;
	private String dReg;
	private String dAutEnt;
	private String dEnt;
	private String dAutSal;
	private String dSal;
	
	private String minsDentro;
	private String minsFuera;
	private String minsVisita;
	

	
	public Expedicion() {
	}


	  public Expedicion(String matricula,
			String idregistro, String mercancia, String precinto, String destino, String dPrev, String dReg,
			String dAutEnt, String dEnt, String dAutSal, String dSal, String minsDentro, String minsFuera,
			String minsVisita) {

		this.matricula = matricula;
		this.idregistro = idregistro;
		this.mercancia = mercancia;
		this.precinto = precinto;
		this.destino = destino;
		this.dPrev = dPrev;
		this.dReg = dReg;
		this.dAutEnt = dAutEnt;
		this.dEnt = dEnt;
		this.dAutSal = dAutSal;
		this.dSal = dSal;
		this.minsDentro = minsDentro;
		this.minsFuera = minsFuera;
		this.minsVisita = minsVisita;
	}



	public String getMatricula() {
		return matricula;
	}



	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}



	public String getIdregistro() {
		return idregistro;
	}



	public void setIdregistro(String idregistro) {
		this.idregistro = idregistro;
	}



	public String getMercancia() {
		return mercancia;
	}



	public void setMercancia(String mercancia) {
		this.mercancia = mercancia;
	}



	public String getPrecinto() {
		return precinto;
	}



	public void setPrecinto(String precinto) {
		this.precinto = precinto;
	}



	public String getDestino() {
		return destino;
	}



	public void setDestino(String destino) {
		this.destino = destino;
	}



	public String getdPrev() {
		return dPrev;
	}



	public void setdPrev(String dPrev) {
		this.dPrev = dPrev;
	}



	public String getdReg() {
		return dReg;
	}



	public void setdReg(String dReg) {
		this.dReg = dReg;
	}



	public String getdAutEnt() {
		return dAutEnt;
	}



	public void setdAutEnt(String dAutEnt) {
		this.dAutEnt = dAutEnt;
	}



	public String getdEnt() {
		return dEnt;
	}



	public void setdEnt(String dEnt) {
		this.dEnt = dEnt;
	}



	public String getdAutSal() {
		return dAutSal;
	}



	public void setdAutSal(String dAutSal) {
		this.dAutSal = dAutSal;
	}



	public String getdSal() {
		return dSal;
	}



	public void setdSal(String dSal) {
		this.dSal = dSal;
	}



	public String getMinsDentro() {
		return minsDentro;
	}



	public void setMinsDentro(String minsDentro) {
		this.minsDentro = minsDentro;
	}



	public String getMinsFuera() {
		return minsFuera;
	}



	public void setMinsFuera(String minsFuera) {
		this.minsFuera = minsFuera;
	}



	public String getMinsVisita() {
		return minsVisita;
	}



	public void setMinsVisita(String minsVisita) {
		this.minsVisita = minsVisita;
	}

	

}

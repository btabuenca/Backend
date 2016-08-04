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

public class Recepcion {
	
	 

	private String matricula;
	private String idregistro;
	private String mercancia;
	private String empresa;

	private String dPrev;
	private String dReg;
	private String dAutEnt;
	private String dEnt;
	private String dAutSal;
	private String dSal;
	
	private String minsDentro;
	private String minsFuera;
	private String minsVisita;
	

	
	public Recepcion() {
	}


	  public Recepcion(String matricula,
			String idregistro, String mercancia, String empresa, String dPrev, String dReg,
			String dAutEnt, String dEnt, String dAutSal, String dSal, String minsDentro, String minsFuera,
			String minsVisita) {

		this.matricula = matricula;
		this.idregistro = idregistro;
		this.mercancia = mercancia;
		this.empresa = empresa;
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



	public String getEmpresa() {
		return empresa;
	}



	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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

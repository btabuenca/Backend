/*******************************************************************************
 * Copyright (C) 2018 Universidad Politecnica de Madrid
 * Author: Bernardo Tabuenca Archilla
 * Lifelong Learning Hub project 
 * 
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.ounl.lifelonglearninghub.db;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Student {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	private String student_fullname;//	'Bernardo Tabuenca Archilla'
	private String student_name;//	'btabuenca'
	private String student_password;// 'btabu3nca',
	private String student_birth;//	'19770830',
	private String student_email;//	'bernardo.tabuenca@upm.es'
	private String student_gender;//	'M'
	private String student_type;//	'1'
	private String student_country;//	'ES'
	private int student_active;// 0, 1...
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStudent_fullname() {
		return student_fullname;
	}
	public void setStudent_fullname(String student_fullname) {
		this.student_fullname = student_fullname;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getStudent_password() {
		return student_password;
	}
	public void setStudent_password(String student_password) {
		this.student_password = student_password;
	}
	public String getStudent_birth() {
		return student_birth;
	}
	public void setStudent_birth(String student_birth) {
		this.student_birth = student_birth;
	}
	public String getStudent_email() {
		return student_email;
	}
	public void setStudent_email(String student_email) {
		this.student_email = student_email;
	}
	public String getStudent_gender() {
		return student_gender;
	}
	public void setStudent_gender(String student_gender) {
		this.student_gender = student_gender;
	}
	public String getStudent_type() {
		return student_type;
	}
	public void setStudent_type(String student_type) {
		this.student_type = student_type;
	}
	public String getStudent_country() {
		return student_country;
	}
	public void setStudent_country(String student_country) {
		this.student_country = student_country;
	}
	public int getStudent_active() {
		return student_active;
	}
	public void setStudent_active(int student_active) {
		this.student_active = student_active;
	}
	
	



}
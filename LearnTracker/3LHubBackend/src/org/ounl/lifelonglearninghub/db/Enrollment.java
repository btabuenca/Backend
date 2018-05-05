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
public class Enrollment {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	private String subject_id;//	'LT2R1'
	private String enrollment_birth;//	'19770830',
	private String enrollment_email;//	'bernardo.tabuenca@upm.es'
	private String enrollment_gender;//	'M'
	private String enrollment_user;//	'btabuenca'
	private String enrollment_password;// 'btabu3nca',
	private String enrollment_type;//	'1'
	private String enrollment_country;//	'ES'
	private int enrollment_active;// 0, 1...
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}
	public String getEnrollment_birth() {
		return enrollment_birth;
	}
	public void setEnrollment_birth(String enrollment_birth) {
		this.enrollment_birth = enrollment_birth;
	}
	public String getEnrollment_email() {
		return enrollment_email;
	}
	public void setEnrollment_email(String enrollment_email) {
		this.enrollment_email = enrollment_email;
	}
	public String getEnrollment_gender() {
		return enrollment_gender;
	}
	public void setEnrollment_gender(String enrollment_gender) {
		this.enrollment_gender = enrollment_gender;
	}
	public String getEnrollment_user() {
		return enrollment_user;
	}
	public void setEnrollment_user(String enrollment_user) {
		this.enrollment_user = enrollment_user;
	}
	public String getEnrollment_password() {
		return enrollment_password;
	}
	public void setEnrollment_password(String enrollment_password) {
		this.enrollment_password = enrollment_password;
	}
	public String getEnrollment_type() {
		return enrollment_type;
	}
	public void setEnrollment_type(String enrollment_type) {
		this.enrollment_type = enrollment_type;
	}
	public String getEnrollment_country() {
		return enrollment_country;
	}
	public void setEnrollment_country(String enrollment_country) {
		this.enrollment_country = enrollment_country;
	}
	public int getEnrollment_active() {
		return enrollment_active;
	}
	public void setEnrollment_active(int enrollment_active) {
		this.enrollment_active = enrollment_active;
	}
	






}
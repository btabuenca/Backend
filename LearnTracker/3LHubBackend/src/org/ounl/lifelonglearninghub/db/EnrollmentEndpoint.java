/*******************************************************************************
 * Copyright (C) 2014 Open University of The Netherlands
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

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.ounl.lifelonglearninghub.PMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;

@Api(name = "enrollmentendpoint", namespace = @ApiNamespace(ownerDomain = "ounl.org", ownerName = "ounl.org", packagePath = "lifelonglearninghub.db"))
public class EnrollmentEndpoint {

	/**
	 * This method lists all the entities inserted in datastore. It uses HTTP
	 * GET method and paging support.
	 * 
	 * @return A CollectionResponse class containing the list of all entities
	 *         persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listEnrollment")
	public CollectionResponse<Enrollment> listEnrollment(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		PersistenceManager mgr = null;
		Cursor cursor = null;
		List<Enrollment> execute = null;

		try {
			mgr = getPersistenceManager();
			Query query = mgr.newQuery(Enrollment.class);
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				HashMap<String, Object> extensionMap = new HashMap<String, Object>();
				extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
				query.setExtensions(extensionMap);
			}

			if (limit != null) {
				query.setRange(0, limit);
			}

			execute = (List<Enrollment>) query.execute();
			cursor = JDOCursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and
			// accomodate
			// for lazy fetch.
			for (Enrollment obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Enrollment> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET
	 * method.
	 * 
	 * @param id
	 *            the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getEnrollment")
	public Enrollment getEnrollment(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		Enrollment enrollment = null;
		try {
			enrollment = mgr.getObjectById(Enrollment.class, id);
		} finally {
			mgr.close();
		}
		return enrollment;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity
	 * already exists in the datastore, an exception is thrown. It uses HTTP
	 * POST method.
	 * 
	 * @param enrollment
	 *            the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertEnrollment")
	public Enrollment insertEnrollment(Enrollment enrollment) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (enrollment.getId() != null) {
				if (containsEnrollment(enrollment)) {
					throw new EntityExistsException("Object already exists");
				}
			}
			mgr.makePersistent(enrollment);
		} finally {
			mgr.close();
		}
		return enrollment;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does
	 * not exist in the datastore, an exception is thrown. It uses HTTP PUT
	 * method.
	 * 
	 * @param enrollment
	 *            the entity to be updated.
	 * @return The updated entity.
	 */
//	@ApiMethod(name = "updateEnrollment")
//	public Enrollment updateEnrollment(Enrollment enrollment) {
//		PersistenceManager mgr = getPersistenceManager();
//		try {
//			if (!containsEnrollment(enrollment)) {
//				throw new EntityNotFoundException("Object does not exist");
//			}
//			mgr.makePersistent(enrollment);
//		} finally {
//			mgr.close();
//		}
//		return enrollment;
//	}

	/**
	 * This method removes the entity with primary key id. It uses HTTP DELETE
	 * method.
	 * 
	 * @param id
	 *            the primary key of the entity to be deleted.
	 */
//	@ApiMethod(name = "removeEnrollment")
//	public void removeEnrollment(@Named("id") Long id) {
//		PersistenceManager mgr = getPersistenceManager();
//		try {
//			Enrollment enrollment = mgr.getObjectById(Enrollment.class, id);
//			mgr.deletePersistent(enrollment);
//		} finally {
//			mgr.close();
//		}
//	}

	private boolean containsEnrollment(Enrollment enrollment) {
		PersistenceManager mgr = getPersistenceManager();
		boolean contains = true;
		try {
			mgr.getObjectById(Enrollment.class, enrollment.getId());
		} catch (javax.jdo.JDOObjectNotFoundException ex) {
			contains = false;
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}

}

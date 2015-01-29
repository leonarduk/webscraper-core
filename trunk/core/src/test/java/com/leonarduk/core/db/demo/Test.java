/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core.db.demo;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Test.
 */
@Entity
@Table(name = "test")
public class Test {

	/** The id. */
	@Id
	private int	   id;

	/** The data. */
	@Basic
	private String	data;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public final String getData() {
		return this.data;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public final int getId() {
		return this.id;
	}

	/**
	 * Sets the data.
	 *
	 * @param dataValue
	 *            the new data
	 */
	public final void setData(final String dataValue) {
		this.data = dataValue;
	}

	/**
	 * Sets the id.
	 *
	 * @param idValue
	 *            the new id
	 */
	public final void setId(final int idValue) {
		this.id = idValue;
	}
}

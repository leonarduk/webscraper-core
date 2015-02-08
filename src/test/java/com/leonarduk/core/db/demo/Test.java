/**
 * All rights reserved. @Leonard UK Ltd.
 */
package com.leonarduk.core.db.demo;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class Test.
 *
 * @author stephen
 * @version $Author: $: Author of last commit
 * @version $Rev$: Revision of last commit
 * @version $Date: 2015-02-08 21:26:51 +0000 (Sun, 08 Feb 2015) $: Date of last commit
 * @since 8 Feb 2015
 */
@Entity
@Table(name = "test")
public class Test {

	/** The id. */
	@Id
	private int id;

	/** The data. */
	@Basic
	private String data;

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

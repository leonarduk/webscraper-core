/**
 *
 */
package com.leonarduk.core.db.demo;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class Test {

	@Id
	private int	   id;

	@Basic
	private String	data;

	public String getData() {
		return this.data;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	public void setData(final String data) {
		this.data = data;
	}

	public void setId(final int id) {
		this.id = id;
	}
}

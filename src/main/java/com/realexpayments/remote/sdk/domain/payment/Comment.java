package com.realexpayments.remote.sdk.domain.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * <p>
 * Class representing a Comment in a Realex request.
 * </p>
 * <p>
 * Helper methods are provided (prefixed with 'add') for object creation.
 * </p>
 * <p>
 * Example creation:
 * </p>
 * <p><code><pre>
 * Comment comment = new Comment().addId(1).addComment("My Comment"); 
 * </pre></code></p>
 * 
 * @author markstanford
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Comment {

	/**
	 * A free text comment.
	 */
	@XmlValue
	private String comment;

	/**
	 * The comment ID (1 or 2)
	 */
	@XmlAttribute(name = "id")
	private Integer id;

	/**
	 * Comment constructor.
	 */
	public Comment() {
	}

	/**
	 * Getter for comment.
	 * 
	 * @return String
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Setter for comment.
	 * 
	 * @param comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Getter for ID.
	 * 
	 * @return Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter for ID.
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Helper method for adding a comment. 
	 * 
	 * @param comment
	 * @return Comment
	 */
	public Comment addComment(String comment) {
		this.comment = comment;
		return this;
	}

	/**
	 * Helper for adding an ID.
	 * 
	 * @param id
	 * @return Comment
	 */
	public Comment addId(Integer id) {
		this.id = id;
		return this;
	}

}

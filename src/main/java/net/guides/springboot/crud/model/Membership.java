package net.guides.springboot.crud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document (collection = "Membership")
public class Membership {

	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	private long id;

	@NotBlank
    @Indexed(unique=true)
	private long personID;
	private long groupID;

	public Membership() {

	}

	public Membership(long personID, long groupID) {
		this.personID = personID;
		this.groupID = groupID;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getPersonID() {
		return personID;
	}
	public void setPersonID(long personID) {
		this.personID = personID;
	}

	public long getGroupID() {
		return groupID;
	}
	public void setGroupID(long groupID) {
		this.groupID = groupID;
	}


	@Override
	public String toString() {
		return "Membership [id=" + id + ", personID=" + personID + ", groupID=" + groupID
				+ "]";
	}	
}

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
    @Size(max=100)
	private String membershipName;
	private String personId;
	private String groupId;

	public Membership() {
		
	}
	
	public Membership(String membershipName, String personId, String groupId) {
		this.membershipName = membershipName;
		this.personId = personId;
		this.groupId = groupId;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getMembershipName() {
		return membershipName;
	}
	public void setMembershipName(String membershipName) {
		this.membershipName = membershipName;
	}
	
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}


	@Override
	public String toString() {
		return "Membership [id=" + id + ", membershipName=" + membershipName + ", personId=" + personId + ", groupId=" + groupId
				+ "]";
	}	
}

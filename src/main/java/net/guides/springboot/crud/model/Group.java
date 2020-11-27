package net.guides.springboot.crud.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Group")
public class Group {

	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private long id;
	
	@NotBlank
    @Size(max=100)
    @Indexed(unique=true)
	private String groupName;
	private String groupColour;

	public Group() {
		
	}
	
	public Group(String groupName, String groupColour) {
		this.groupName = groupName;
		this.groupColour = groupColour;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getGroupColour() {
		return groupColour;
	}
	public void setGroupColour(String groupColour) {
		this.groupColour = groupColour;
	}


	@Override
	public String toString() {
		return "Person [id=" + id + ", groupName=" + groupName + ", groupColour=" + groupColour
				+ "]";
	}	
}

package com.javabase.demo.entity.mongo;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 实体bean
 * 
 * @author bruce
 *
 */
@Document(collection="mongo_student")
public class MongoStudent implements Serializable {

	private static final long serialVersionUID = 1L;	@Id	private ObjectId _id;//   _id	private String studentName;//   studentName	private int studentAge;//   studentAge	private String className;//   className	private String description;//   description	private List<MongoTeacher> teachers;//   teachers		public ObjectId get_id() {	    return this._id;	}	public void set_id(ObjectId _id) {	    this._id=_id;	}		public String getStudentName() {	    return this.studentName;	}	public void setStudentName(String studentName) {	    this.studentName=studentName;	}		public int getStudentAge() {	    return this.studentAge;	}	public void setStudentAge(int studentAge) {	    this.studentAge=studentAge;	}		public String getClassName() {	    return this.className;	}	public void setClassName(String className) {	    this.className=className;	}		public String getDescription() {	    return this.description;	}	public void setDescription(String description) {	    this.description=description;	}		public List<MongoTeacher> getTeachers() {	    return this.teachers;	}	public void setTeachers(List<MongoTeacher> teachers) {	    this.teachers=teachers;	}
	
}


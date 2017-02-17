package com.javabase.demo.entity.mongo;

import java.io.Serializable;
import java.util.Date;
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
@Document(collection="mongo_teacher")
public class MongoTeacher implements Serializable {

	private static final long serialVersionUID = 1L;	@Id	private ObjectId _id;//   _id	private String teahcerName;//   teahcerName	private int teacherAge;//   teacherAge	private String teahcerMajor;//   teahcerMajor	private Date graduateTime;//   graduateTime	private String description;//   description	private String[] students;//   students		public ObjectId get_id() {	    return this._id;	}	public void set_id(ObjectId _id) {	    this._id=_id;	}		public String getTeahcerName() {	    return this.teahcerName;	}	public void setTeahcerName(String teahcerName) {	    this.teahcerName=teahcerName;	}		public int getTeacherAge() {	    return this.teacherAge;	}	public void setTeacherAge(int teacherAge) {	    this.teacherAge=teacherAge;	}		public String getTeahcerMajor() {	    return this.teahcerMajor;	}	public void setTeahcerMajor(String teahcerMajor) {	    this.teahcerMajor=teahcerMajor;	}		public Date getGraduateTime() {	    return this.graduateTime;	}	public void setGraduateTime(Date graduateTime) {	    this.graduateTime=graduateTime;	}		public String getDescription() {	    return this.description;	}	public void setDescription(String description) {	    this.description=description;	}		public String[] getStudents() {	    return this.students;	}	public void setStudents(String[] students) {	    this.students=students;	}
	
}


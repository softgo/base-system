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

	private static final long serialVersionUID = 1L;
	
}

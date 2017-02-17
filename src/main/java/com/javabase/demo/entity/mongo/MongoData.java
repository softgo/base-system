package com.javabase.demo.entity.mongo;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 实体bean
 * 
 * @author bruce
 *
 */
@Document(collection="mongo_data")
public class MongoData implements Serializable {

	private static final long serialVersionUID = 1L;
	
}

package edu.nyu.cs9033.repositories;

import java.io.InputStream;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

/**
 * @author Shilpan Patel
 * 
 */

@Service
public class FileRepository {

	@Autowired
	private GridFsOperations gridOperation;

	public String save(InputStream inputStream, String contentType,
			String filename) {

		DBObject metaData = new BasicDBObject();
		metaData.put("file_name", filename);
		metaData.put("content_type", contentType);

		GridFSFile file = gridOperation.store(inputStream, filename, metaData);

		return file.getId().toString();
	}

	public GridFSDBFile get(String id) {

		System.out.println("Finding by ID: " + id);
		return gridOperation.findOne(new Query(Criteria.where("_id").is(
				new ObjectId(id))));
	}

	@SuppressWarnings("rawtypes")
	public List listFiles() {
		return gridOperation.find(null);
	}

	public GridFSDBFile getByFilename(String filename) {
		return gridOperation.findOne(new Query(Criteria.where("filename").is(
				filename)));
	}
}
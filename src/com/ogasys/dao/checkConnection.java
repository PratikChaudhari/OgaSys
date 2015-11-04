package com.ogasys.dao;

import java.sql.SQLException;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.WriteResult;
import com.ogasys.model.User;

public class checkConnection {

		/**
	 * @param args
	 * @throws MongoSocketOpenException
	 */
	public static void main(String[] args) throws MongoSocketOpenException{
      MongoClient con= DBConnection.getInstance().getConnection();
	  if(con!=null){
		  System.out.println("Connection established Successfully");
		  DB db=con.getDB("ogasys");
	      DBCollection collection=db.getCollection("users");
	      System.out.println(collection);
	  }
	  else {
		  System.out.println("Connection not established");
	  }

	}

}

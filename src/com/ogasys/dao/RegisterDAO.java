package com.ogasys.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.ogasys.model.RegisterBean;

public class RegisterDAO {
	 public RegisterDAO() throws ClassNotFoundException, MongoException
	   {
	       con=DBConnection.getInstance().getConnection();
	   }
	   
	   public boolean doRegister(RegisterBean reg) throws ClassNotFoundException, MongoException
	   {
		   try{
			   DB db=con.getDB("mydb");
			   System.out.println("Connect to database successfully");
		       //boolean auth = db.authenticate(myUserName, myPassword);
			   //System.out.println("Authentication: "+auth);         
			   boolean collectionExists = db.collectionExists("user");  
			   System.out.println("collection exists is "+collectionExists);
			   if (collectionExists == false) {  
				    db.createCollection("users",null);  
				 }  
			   DBCollection collection=db.getCollection("user");
			   System.out.println("Collection user selected successfully");
			   BasicDBObject doc = new BasicDBObject("username",reg.getUsername()).
			            append("password",reg.getPassword());
			   collection.insert(doc); 
			   System.out.println("Document inserted successfully");
			   return true;
		   }
		   catch(Exception ex){
			   System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );   
		   }
	      return false; 
	   }
	   private final MongoClient con;
}

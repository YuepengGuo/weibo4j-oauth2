package weibo4j.examples.timeline;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class WeiboDao {
	private WeiboDao(){};
	
	public static final WeiboDao instance = new WeiboDao();
	
	public void save(String comments, String colname){
		MongoClient mongoClient = null;
		
		try {
			mongoClient = new MongoClient();
	    	DB mdb  = mongoClient.getDB("WEIBO");
			DBCollection coll = mdb.getCollection(colname);
			System.out.println(comments);
	        DBObject dbObject = (DBObject)  JSON.parse(comments);
	        DBObject q = new BasicDBObject("strid", dbObject.get("strid"));
	        if(null != coll.find(q)){
//	        	System.out.println("====updated?====");
	        	coll.update(q, dbObject,true,false);
	        }else{
	        	System.out.println("====new====");
	        	coll.save(dbObject);
	        }
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			mongoClient.close();
		}
	}

}

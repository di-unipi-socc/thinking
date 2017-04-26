package api;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.bson.Document;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import configuration.ThoughtsSharingConfiguration;

public class ThoughtsSharingApp extends Application<ThoughtsSharingConfiguration> {
	
	public static void main(String[] args) throws Exception {
		new ThoughtsSharingApp().run(args);
	}

	@Override
	public void run(ThoughtsSharingConfiguration conf, Environment env) throws Exception {
		// Create a client for the remote mongodb
		MongoClient mongoClient = new MongoClient(conf.getDbURL(),conf.getDbPort());
		// Get the database managing thoughts
		MongoDatabase thoughtsDb  = mongoClient.getDatabase(conf.getDbName());
		// Create and get the collection of thoughts
		MongoCollection<Document> thoughtsCollection = thoughtsDb.getCollection(conf.getCollectionName());
		
		// Allow "Cross-Origin"
		final FilterRegistration.Dynamic cors = env.servlets().addFilter("CORS", CrossOriginFilter.class);
		cors.setInitParameter("allowedOrigins", "*");
		cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        
        // Register and offer api
        final ThoughtsSharingAPI api = new ThoughtsSharingAPI(thoughtsCollection);
		env.jersey().register(api);;

	}

}

package api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

@Path("/thoughts")
@Produces(MediaType.APPLICATION_JSON)
public class ThoughtsSharingAPI {

	private final MongoCollection<Document> thoughtsCollection;
	
	public ThoughtsSharingAPI(MongoCollection<Document> thoughtsCollection) {
		this.thoughtsCollection = thoughtsCollection;
	}
	
	@POST
	@Timed
	public Response addThought(@QueryParam("thought") String thought, 
			@QueryParam("author") Optional<String> author, 
			@QueryParam("location") Optional<String> location) {
		// Create document representing the thought to be added
		Document newThought = new Document();
		newThought.append("thought", thought);
		newThought.append("author", author.or("Unknown"));
		newThought.append("location", location.or("Unknown"));
		
		// Add the new thought to the collection
		thoughtsCollection.insertOne(newThought);
		
		return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("Thought added").build();
	}
	
	@GET
	@Timed
	public Response getThoughts() {
		// Create the list of thoughts to be returned
		List<Document> thoughts = new ArrayList<Document>();
		
		// Fill the list of thoughts with (all) those contained in the thoughtsCollection
		FindIterable<Document> thoughtsIterable = thoughtsCollection.find();
		MongoCursor<Document> thoughtsIterator = thoughtsIterable.iterator();
		while(thoughtsIterator.hasNext()) 
			thoughts.add(thoughtsIterator.next());
		
		// Returns the list of thoughts
		return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(thoughts).build();
	}
}

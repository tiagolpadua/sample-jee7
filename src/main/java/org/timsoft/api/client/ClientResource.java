package org.timsoft.api.client;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// http://localhost:8080/sample-jee7/api/client
	
@RequestScoped
@Path("client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

//    @Inject
//    Message message;
	
	@GET
    public Response findAll() {
//        JsonArrayBuilder list = Json.createArrayBuilder();
//        List<String> all = Arrays.asList("foo", "bar");
//        all.stream().map(m -> m.toJson()).forEach(list::add);
		
		Client c = new Client(1, "john", "indiana");
		
        return Response.ok().entity(c).build();
    }

//    @GET
//    public JsonArray findAll() {
//        JsonArrayBuilder list = Json.createArrayBuilder();
//        List<GuestBook> all = this.message.findAll();
//        all.stream().map(m -> m.toJson().forEach(list::add);
//        return list.build();
//    }
//
//    @GET
//    @Path("{id}")
//    public JsonObject findById(@PathParam("id") Long id) {
//        GuestBook guestBook = this.message.findById(id);
//        return guestBook.toJson();
//    }
//
//    @POST
//    public Response save(@Valid GuestBook guestBook) {
//        this.message.create(guestBook);
//        return Response.ok().build();
//    }
}
package rest;

import rolo.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.util.List;

@Path("/hello-rest")
@Produces("text/plain")
public class HelloRest {
    @PersistenceContext(unitName = "jpaRolo")
    private EntityManager em;

    @GET
    public String hello() {
        return "Hello!";
    }

    @GET
    @Path("/users")
    public Response getAllUser(){
        try{
            Query quUsers = em.createQuery("select u from User u", User.class);
            List<User> users = quUsers.getResultList();
            return Response.ok(users.get(0).getName()).build();
        }catch(Exception e){
            throw new WebApplicationException(Response.status(404).type("text/plain")
                                                      .entity(MessageFormat.format("select u from User u -> {0}", e.getStackTrace()))
                                                      .build());
        }
     }
}

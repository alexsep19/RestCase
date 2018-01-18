package rest;

import dao.RoleDao;
import rest.api.AbstractEntityRest;
import rolo.Role;
import rolo.User;

import javax.ejb.Stateless;
import javax.ws.rs.*;

import java.util.List;

import static rest.api.IRestApiMediaType.JSON_UTF8;
import static rest.api.IRestApiMediaType.TEXT_UTF8;

@Path("/role")
@Consumes(JSON_UTF8)
@Produces(JSON_UTF8)
@Stateless
public class RoleRest  extends AbstractEntityRest<Long, Role, RoleDao> {
    public RoleRest() {
        super(Role.class);
    }
//    @GET
//    @Path("/echo")
//    @Produces(TEXT_UTF8)
//    public String echo(){ return super.echo(); }

    @GET
    public List<Role> list(@QueryParam("offset") Integer offset,
                           @QueryParam("length") Integer length){
        return super.list(offset,length);
    }
    @POST
    public void create(Role role){
        super.create(role);
    }

}

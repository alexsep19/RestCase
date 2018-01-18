package rest;

import dao.UrroDao;
import rest.api.AbstractEntityRest;
import rolo.Role;
import rolo.Urro;
import rolo.UrroId;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.List;

import static rest.api.IRestApiMediaType.JSON_UTF8;
import static rest.api.IRestApiMediaType.TEXT_UTF8;

@Path("/urro")
@Consumes(JSON_UTF8)
@Produces(JSON_UTF8)
@Stateless
public class UrroRest extends AbstractEntityRest<UrroId, Urro, UrroDao> {
    public UrroRest() {
        super(Urro.class);
    }
//    @GET
//    @Path("/echo")
//    @Produces(TEXT_UTF8)
//    public String echo(){ return super.echo(); }

    @GET
    public List<Urro> list(@QueryParam("offset") Integer offset,
                           @QueryParam("length") Integer length){
        return super.list(offset,length);
    }
    @POST
    public void create(Urro urro){
        super.create(urro);
    }

}

package rest;

import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.net.URLDecoder;

/**
 * Created by KryukovMV on 06.04.2017.
 */
@Path("/exec")
@Produces("text/plain")
public class ExecutionRest {

    private static final Logger logger = Logger.getLogger(ExecutionRest.class);

//    @Inject
//    private ExecutionBean executionBean;

//    @Secured
    @GET
    @Path("/java/{cmd}")
    public Response javaGet(@Context SecurityContext sc,
                            @PathParam("cmd") String cmd,
                            @QueryParam("q") String q) {

        return javaPost(sc, cmd, q);
    }

//    @Secured
    @POST
    @Path("/java/{cmd}")
    public Response javaPost(@Context SecurityContext sc,
                             @PathParam("cmd") String cmd,
                             @QueryParam("q") String q) {
        if (sc.getUserPrincipal() == null) return Response.status(Response.Status.NO_CONTENT).build();
        try {
            System.out.println("cmd = " + cmd);
            cmd = URLDecoder.decode(cmd, "UTF-8");
//            executionBean.javaProc(cmd);
            return Response.ok("OK!!!!!!!!!!!!").build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


//    @Secured
    @GET
    @Path("/{cmd}")
    public Response execGet(@Context SecurityContext sc,
                         @PathParam("cmd") String cmd,
                         @QueryParam("q") String q) {

        return execPost(sc, cmd, q);
    }

//    @Secured
    @POST
    @Path("/{cmd}")
    public Response execPost(@Context SecurityContext sc,
                         @PathParam("cmd") String cmd,
                         @QueryParam("q") String q) {
        if (sc.getUserPrincipal() == null) return Response.status(Response.Status.NO_CONTENT).build();
        try {
            System.out.println("cmd = " + cmd);
            cmd = URLDecoder.decode(cmd, "UTF-8");
//            executionBean.proc(cmd);
            return Response.ok("OK!!!!!!!!!!!!").build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

}

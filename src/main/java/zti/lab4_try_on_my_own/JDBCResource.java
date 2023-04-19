package zti.lab4_try_on_my_own;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import zti.model1.Database;
import zti.model1.Person;

@Path("/jdbc/person")
public class JDBCResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Person[] get() {
        System.out.println("GET");
        Database data = new Database();
        return data.getPersonList().toArray(new Person[0]);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Person get(@PathParam("id") String id) {
        System.out.println("GET");
        Database data = new Database();
        return data.readPerson(id);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String post(Person person) {
        System.out.println("POST");
        Database data = new Database();
        data.createPerson(person);
        return "add record" ;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String put(Person person) {
        System.out.println("PUT");
        Database data = new Database();
        data.updatePerson(person);
        return "update record" ;
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.TEXT_PLAIN})
    public String delete(@PathParam("id") String id) {
        System.out.println("DELETE");
        Database data = new Database();
        data.deletePerson(id);
        return "delete record" ;
    }

}


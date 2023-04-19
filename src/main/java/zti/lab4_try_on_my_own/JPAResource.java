package zti.lab4_try_on_my_own;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import zti.model2.Person;

@Path("/jpa/person")
public class JPAResource {

    private EntityManagerFactory managerFactory;
    private EntityManager entityManager; // = managerFactory.createEntityManager();
    private EntityTransaction entityTransaction;

    public JPAResource() {
        managerFactory = Persistence.createEntityManagerFactory("PU_Postgresql");
        entityManager = managerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();

    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Person[] get() {
        System.out.println("GET");
        List<Person> people = null;
        try {
            @SuppressWarnings("unchecked")
            List<Person> resultList = (List<Person>) entityManager.createNamedQuery("findAll").getResultList();
            //people = (List<Person>) entityManager.createNamedQuery("findAll").getResultList();
            people = resultList;
            // manager.close();
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return people.toArray(new Person[0]);
    }


    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Person get(@PathParam("id") String id) {
        System.out.println("GET");
        Person entity = (Person) entityManager.find(Person.class, Integer.parseInt(id));
        return entity;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String post(Person person) {
        System.out.println("POST");
        entityTransaction.begin();
        entityManager.persist(person);
        entityManager.flush();
        entityTransaction.commit();
        return "add record" ;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String put(Person person) {
        System.out.println("PUT");
        entityTransaction.begin();
        entityManager.merge(person);
        entityTransaction.commit();
        return "update record" ;
    }


    @DELETE
    @Path("{id}")
    @Produces({MediaType.TEXT_PLAIN})
    public String delete(@PathParam("id") String id) {
        System.out.println("DELETE");
        entityTransaction.begin();
        Person entity = (Person) entityManager.find(Person.class, Integer.parseInt(id));
        entityManager.remove(entity);
        entityManager.flush();
        entityTransaction.commit();
        return "delete record" ;
    }

}

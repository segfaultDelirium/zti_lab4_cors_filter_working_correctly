package zti.lab4_try_on_my_own;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class Lab04Application extends Application {

    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<Class<?>>();
        set.add(HelloResource.class);
        set.add(JDBCResource.class);
        set.add(JPAResource.class);
        set.add(CorsFilter.class);
        return set;
    }

}
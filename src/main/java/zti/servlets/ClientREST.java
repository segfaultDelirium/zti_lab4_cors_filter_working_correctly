package zti.servlets;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

/**
 * Servlet implementation class ClientREST
 */
@WebServlet("/ClientREST")
public class ClientREST extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientREST() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        Client client = ClientBuilder.newClient();
        String url = "http://localhost:31222/lab4_try_on_my_own-1.0-SNAPSHOT/api/jpa/person/";
        resp.setContentType("application/json");
        Response res ;
        // WebTarget target;
        if (req.getParameterMap().containsKey("id")) {
            //String id = req.getParameterValues("id")[0] ;
            String id = req.getParameter("id");
            res = client.target(url).path("/{id}").resolveTemplate("id", id).request("application/json").get();
        } else {
            res = client.target(url).request("application/json").get();
        }
        resp.getWriter().append(res.readEntity(String.class));
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}

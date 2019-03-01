package io.github.oliviercailloux.collaborative_exams;



import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.oliviercailloux.collaborative_exams.controller.EditDistanceRecursive;



/**
 * Servlet implementation class addNewAuthorServlet
 */
@WebServlet("/ReponseLibreServlet")
public class ReponseLibreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReponseLibreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher( "/ReponseLibre.jsp" ).forward( request, response );

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String reponse = request.getParameter( "textarea2" );
		String message = "" ;

		if (reponse.equals("") )
		{
			message += "Veuillez répondre à la question ! <br>" ;			
		} 
		
		if (!message.equals("")) {
			request.setAttribute("erreur", message);
			this.getServletContext().getRequestDispatcher( "/ReponseLibre.jsp" ).forward( request, response );
		}
		else	
		{
			
			int difference = EditDistanceRecursive.calculate(reponse,"Acteur");
			System.out.println(difference);
			if(difference>5)
				request.setAttribute("answer", "Votre réponse n'est pas correcte ! ");
			if(difference<=5)
				request.setAttribute("answer", "Bonne réponse ! ");
			
			this.getServletContext().getRequestDispatcher( "/ReponseLibre.jsp" ).forward( request, response );

			
			/*Author author = new Author (mail,mdp);
			
			 //EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDbFile.odb");
			QueryHelper qh= new QueryHelper();
			AuthorService as= new AuthorService(qh);
			as.persist(author);
			this.getServletContext().getRequestDispatcher( "/Success.jsp" ).forward( request, response );
			Author auth = as.findAuthor(5);
			System.out.print(auth.getId() +" "+auth.getMail()); */
			

			
		}
	}

}

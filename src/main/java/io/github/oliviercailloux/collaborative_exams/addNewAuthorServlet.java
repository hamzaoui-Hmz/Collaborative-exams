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

import io.github.oliviercailloux.collaborative_exams.Service.AuthorService;
import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.Author;


/**
 * Servlet implementation class addNewAuthorServlet
 */
@WebServlet("/addNewAuthorServlet")
public class addNewAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addNewAuthorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher( "/addNewAuthorForm.jsp" ).forward( request, response );

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String mail = request.getParameter( "mail" );
		String mdp = request.getParameter( "password" );
		String mdpconf = request.getParameter( "confPassword" );
		String message = "" ;

		if (mail.equals("") )
		{
			message += "Le mail ne doit pas être vide ! <br>" ;			
		} 
		
		if (mdp.equals("") )
		{
			message += "Le mot de passe ne doit pas être vide ! <br>" ;
							
		} 
		
		if (!mdp.equals(mdpconf) )
		{
			message += "Les deux mots de passe doivent être identiques !" ;
							
		}
		if (!message.equals("")) {
			request.setAttribute("erreur", message);
			this.getServletContext().getRequestDispatcher( "/addNewAuthorForm.jsp" ).forward( request, response );
		}
		else	
		{
			Author author = new Author (mail,mdp);
			
			 //EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDbFile.odb");
			QueryHelper qh= new QueryHelper();
			AuthorService as= new AuthorService(qh);
			as.persist(author);
			this.getServletContext().getRequestDispatcher( "/Success.jsp" ).forward( request, response );
			Author auth = as.findAuthor(5);
			System.out.print(auth.getId() +" "+auth.getEmail());
			

			
		}
	}

}

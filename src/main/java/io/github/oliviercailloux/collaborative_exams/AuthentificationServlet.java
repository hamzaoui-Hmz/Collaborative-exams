package io.github.oliviercailloux.collaborative_exams;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.oliviercailloux.collaborative_exams.Service.AuthorService;
import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;


/**
 * Servlet implementation class AuthentificationServlet
 */
@WebServlet("/AuthentificationServlet")
public class AuthentificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthentificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher( "/authentification.jsp" ).forward( request, response );

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String mail = request.getParameter( "mail" );
		String mdp = request.getParameter( "password" );
		String message = "" ;

		if (mail.equals("") )
		{
			message += "Le mail ne doit pas être vide ! <br>" ;			
		} 
		
		if (mdp.equals("") )
		{
			message += "Le mot de passe ne doit pas être vide ! <br>" ;
							
		} 
		
		if (!message.equals("")) {
			request.setAttribute("erreur", message);
			this.getServletContext().getRequestDispatcher( "/authentification.jsp" ).forward( request, response );
		}
		else	
		{
			QueryHelper qh= new QueryHelper();
			AuthorService as= new AuthorService(qh);
			if (as.findAuthorByEmail(mail, mdp) != null) 
			{
				this.getServletContext().getRequestDispatcher( "/Success.jsp" ).forward( request, response );
			}
			else
			{
				request.setAttribute("erreur", "Le mail ou le mot de passe erroné ");
				this.getServletContext().getRequestDispatcher( "/authentification.jsp" ).forward( request, response );
			}
		}	
	}
}

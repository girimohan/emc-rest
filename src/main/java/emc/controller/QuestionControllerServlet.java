package emc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;

import emc.model.Question;

/**
 * Servlet implementation class QuestionControllerServlet
 * Handles request and response for questions. 
 */

public class QuestionControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private Client client = ClientBuilder.newClient();

	private String REST_URI;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		 switch (action) {
		  case "/questions":
			  viewQuestionsToAnswer(request, response);
			  break;
			  
		  
			  
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
	/**
	 * Method gets question data from the rest service URL and sends data in request to JSP(View) for user to answer
	 * @author DGautam
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	
	private void viewQuestionsToAnswer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		REST_URI = "http://127.0.0.1:8080/_rest/question/all";
		 
		 WebTarget webTarget = client.target(REST_URI);
		 
	     Builder builder = webTarget.request();
	
	   	GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
		
		List<Question> candidatesList = builder.get(genericList);
		
		//Add candidates list to the request
				request.setAttribute("QUESTIONS_LIST", candidatesList);

				//Send to JSP Page (View)

				RequestDispatcher dispatcher = request.getRequestDispatcher("/questions.jsp");
				dispatcher.forward(request, response);	
		
	}
	

}

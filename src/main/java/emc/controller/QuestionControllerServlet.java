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
		List <Question> questionsList = viewAllQuestions(request, response);
		RequestDispatcher dispatcher;
		
		 switch (action) {
		  case "/questions":
			  
			  request.setAttribute("QUESTIONS_LIST", questionsList);
			  dispatcher=request.getRequestDispatcher("/questions.jsp");
			  dispatcher.forward(request, response);
			  break;
			  
		  case "/admin/questions":
			  request.setAttribute("QUESTIONS_LIST", questionsList);
			  dispatcher=request.getRequestDispatcher("/admin/questions.jsp");
			  dispatcher.forward(request, response);
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
	 * @author SURAJ
	 * @param request
	 * @param response
	 * @return 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	
	private List<Question> viewAllQuestions(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		REST_URI = "http://127.0.0.1:8080/_rest/question/all";
		 
		 WebTarget webTarget = client.target(REST_URI);
		 
	     Builder builder = webTarget.request();
	
	   	GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
		
		List<Question> questionsList = builder.get(genericList);
		
		return questionsList;
		
	}
	

}

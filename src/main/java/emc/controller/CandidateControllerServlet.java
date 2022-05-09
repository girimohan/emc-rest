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

import emc.model.Candidate;

/**
 * Servlet implementation class CandidateControllerServlet
 * Handles candidate operation
 */
public class CandidateControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Client client = ClientBuilder.newClient();

	private String REST_URI;
 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		List<Candidate> candidatesList =viewAllCandidates(request, response);
		RequestDispatcher dispatcher;
		
		 switch (action) {
		  case "/candidates":
			  request.setAttribute("CANDIDATES_LIST", candidatesList);
			  dispatcher = request.getRequestDispatcher("/candidates.jsp");
			  dispatcher.forward(request, response);
			  break;
		  case "/admin/candidates":
			  request.setAttribute("CANDIDATES_LIST", candidatesList);
			  dispatcher = request.getRequestDispatcher("/admin/candidates.jsp");
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
	 * @author DGautam
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * Method gets candidates data from the rest service URL and sends data in request to JSP(View) for user.
	 */
	
	private List<Candidate> viewAllCandidates(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		 REST_URI = "http://127.0.0.1:8080/_rest/candidate/all";
		 
		 WebTarget webTarget = client.target(REST_URI);
		 
	     Builder builder = webTarget.request();
	
	   	GenericType<List<Candidate>> genericList = new GenericType<List<Candidate>>() {};
		
		List<Candidate> candidatesList = builder.get(genericList);
		
		return candidatesList;	
		
	}
	

}

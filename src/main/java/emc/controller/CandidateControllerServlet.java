package emc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;

import emc.model.Candidate;
import emc.model.Party;
import emc.utils.UploadImage;

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
		
		String action = request.getServletPath().toLowerCase();
		String VIEW_URL=null;
		List<Candidate> candidatesList =viewAllCandidates(request, response);
		RequestDispatcher dispatcher;
		
		 switch (action) {
		  case "/candidates":
			  request.setAttribute("CANDIDATES_LIST", candidatesList);
			  VIEW_URL = "/candidates.jsp";
			  break;
		  case "/admin/candidates":
			  request.setAttribute("CANDIDATES_LIST", candidatesList);
			  VIEW_URL = "/admin/candidates.jsp";
			  break;
		 
		  case "/admin/candidates/add":
			  List<Party> partiesList = getAllParties(request, response);
			  request.setAttribute("PARTIES_LIST", partiesList);
			  VIEW_URL = "/admin/addcandidate.jsp";
			  break;
		  
		  case "/admin/candidates/delete":
			  candidatesList = deleteCandidate(request, response);
			  VIEW_URL = "/admin/candidates.jsp";
			  request.setAttribute("MSG", "Candidate deleted successfully");
			  request.setAttribute("CANDIDATES_LIST",candidatesList);
			  break;
			  
		 }
		 
		 dispatcher = request.getRequestDispatcher(VIEW_URL);
		 dispatcher.forward(request, response);
		
	}

	/**
	 * @author DGautam
	 *
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		
		String surname= request.getParameter("surname");
		String firstname= request.getParameter("firstname");
		int partyId= Integer.parseInt(request.getParameter("party"));
		String profession= request.getParameter("profession");
		int age= Integer.parseInt(request.getParameter("age"));
		String ideology = request.getParameter("ideology");
		String motive = request.getParameter("motive");
		
        Candidate candidate = new Candidate();
		
		candidate.setSurname(surname);
		candidate.setFirstname(firstname);
		candidate.setProfession(profession);
		candidate.setAge(age);
		candidate.setIdeology(ideology);
		candidate.setMotive(motive);
		
		// Input stream of the upload file
        InputStream inputStream = null;
        
     // Obtains the upload file
        // part in this multipart request
        Part filePart
            = request.getPart("img");
        
        if (filePart != null) {
        	  
            // Prints out some information
            // for debugging
            System.out.println(
                filePart.getName());
            System.out.println(
                filePart.getSize());
            System.out.println(
                filePart.getContentType());
            
            String filename = filePart.getName();
  
            // Obtains input stream of the upload file
            inputStream
                = filePart.getInputStream();
            
           if(UploadImage.copyImage(filename, inputStream)) {
        	   candidate.setImg(filename);
        	   
        	   
        	   
        	   
           }else {
        	   
           }
        }
	
				
				
				
		
		
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
	
	/**
	 * @author DGautam
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * Method gets parties data from the rest service URL and sends data in request to JSP(View) add candidates form .
	 */
	
	private List<Party> getAllParties(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		 REST_URI = "http://127.0.0.1:8080/_rest/parties/all";
		 
		 WebTarget webTarget = client.target(REST_URI);
		 
	     Builder builder = webTarget.request();
	
	   	GenericType<List<Party>> genericList = new GenericType<List<Party>>() {};
		
		List<Party> partiesList = builder.get(genericList);
		
		return partiesList;	
		
	}
	
	private List<Candidate> deleteCandidate(HttpServletRequest request,HttpServletResponse response){
		String candidateId = request.getParameter("id");
		REST_URI = "http://127.0.0.1:8080/_rest/candidate/delete/"+candidateId;
		WebTarget webTarget = client.target(REST_URI);
		Builder builder = webTarget.request();
		GenericType<List<Candidate>> genericList = new GenericType<List<Candidate>>() {};
		
		List<Candidate> candidatesList = builder.delete(genericList);
		return candidatesList;
		
	}

	

}

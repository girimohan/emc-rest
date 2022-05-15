package emc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import emc.model.Candidate;
import emc.model.Party;
import emc.utils.UploadImage;

/**
 * @author DGautam
 * Servlet implementation class CandidateControllerServlet
 * Handles candidate CRUD operation request. Serves as a client for rest services.
 */
@MultipartConfig( maxFileSize = 1024 * 1024 * 5)
public class CandidateControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Client client = ClientBuilder.newClient();

	private String REST_URI;
 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Response all the get request
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
		
		  case "/admin/candidate/update":
			  List<Party> partiesListu = getAllParties(request, response);
			  request.setAttribute("PARTIES_LIST", partiesListu);
			  Candidate candidateInfo = loadCandidateToUpdate(request, response);
			  request.setAttribute("CANDIDATE_INFO", candidateInfo);
			  VIEW_URL= "/admin/candidateupdate.jsp";
			  break;
			  
		 } 
		 
		 dispatcher = request.getRequestDispatcher(VIEW_URL);
		 dispatcher.forward(request, response);
		
	}

	/**
	 * @author DGautam
	 * Handles candidate post request to insert candidate and update candidate.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String action = request.getServletPath().toLowerCase();
		
		if(action.equalsIgnoreCase("/admin/candidate/update")) {
			
			updateCandidate(request, response);
			
		} else {
			
			addCandidate(request, response);
		
		
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
	
	/**
	 * @author DGautam 
	 * @param request
	 * @param response
	 * @return candidatesList
	 * Makes Delete request to REST_API for deleting candidates using client builder
	 */
	
	private List<Candidate> deleteCandidate(HttpServletRequest request,HttpServletResponse response){
		String candidateId = request.getParameter("id");
		REST_URI = "http://127.0.0.1:8080/_rest/candidate/delete/"+candidateId;
		WebTarget webTarget = client.target(REST_URI);
		Builder builder = webTarget.request();
		GenericType<List<Candidate>> genericList = new GenericType<List<Candidate>>() {};
		
		List<Candidate> candidatesList = builder.delete(genericList);
		return candidatesList;
		
	}
	
	/**
	 * @author Mohan
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * Gets Individual candidate data from REST Service by making get request.
	 * 
	 */
	
	
	private Candidate loadCandidateToUpdate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		REST_URI = "http://127.0.0.1:8080/_rest/candidate/get/"+ id;
		WebTarget webTarget = client.target(REST_URI);
		Builder builder = webTarget.request();
		Candidate candidateInfo = builder.get(Candidate.class);
		return candidateInfo;
		
		
	}
	
	/**
	 * @author DGautam
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * Gets form field data and create a post request to REST_API to insert candidate
	 */
	
	
	private void addCandidate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
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
		
		Party party = new Party();
		party.setPartyId(partyId);
		
		candidate.setParty(party);
		
		// Input stream of the upload file
        InputStream inputStream = null;
        
     // Obtains the upload file
        // part in this multipart request
        Part filePart
            = request.getPart("image");
        
        if (filePart != null) {
        	  
            // Prints out some information
            // for debugging
            System.out.println(
                filePart.getName());
            System.out.println(
                filePart.getSize());
            System.out.println(
                filePart.getContentType());
            
            String filename = UploadImage.extractFileName(filePart);
            String contentType = filePart.getContentType();
  
            // Obtains input stream of the upload file
            inputStream
                = filePart.getInputStream();
            
               UploadImage.copyFile(filename, contentType, inputStream);
        	   candidate.setImg(filename);
        	   
        	   REST_URI = "http://127.0.0.1:8080/_rest/candidate/add";
        	   WebTarget webTarget = client.target(REST_URI);
        	   Builder builder = webTarget.request();
        	   
        	   Entity<Candidate> ecandidates = Entity.entity(candidate, MediaType.APPLICATION_JSON);
        	   
        	   GenericType<List<Candidate>> genericList = new GenericType<List<Candidate>>() {};
        	   
        	   List<Candidate> candidatesList = builder.post(ecandidates,genericList);
        	   
        	   request.setAttribute("CANDIDATES_LIST", candidatesList);
        	   
        	   request.setAttribute("MSG", "Candidate Added Successfully" );
        	   
        	  RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/candidates.jsp");
      		  dispatcher.forward(request, response);
        	   
        	   
           }else {
        	   
        	   System.out.println("Unable to insert");
        	   RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/candidates.jsp");
       		  dispatcher.forward(request, response);
        	   
        	   
           }
		
	}
		
	
	
	/**
	 * @author Mohan
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * Gets Data from field and insert into database using jpa rest service.
	 * 
	 */
	
	private void updateCandidate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		int candidateId = Integer.parseInt(request.getParameter("id"));
		String surname= request.getParameter("surname");
		String firstname= request.getParameter("firstname");
		int partyId= Integer.parseInt(request.getParameter("party"));
		String profession= request.getParameter("profession");
		int age= Integer.parseInt(request.getParameter("age"));
		String ideology = request.getParameter("ideology");
		String motive = request.getParameter("motive");
		
        Candidate candidate = new Candidate();
		
        candidate.setCandidateId(candidateId);
		candidate.setSurname(surname);
		candidate.setFirstname(firstname);
		candidate.setProfession(profession);
		candidate.setAge(age);
		candidate.setIdeology(ideology);
		candidate.setMotive(motive);
		
		Party party = new Party();
		party.setPartyId(partyId);
		
		candidate.setParty(party);
        
       InputStream image = getImageStream(request, "image");
        
        if (image != null) {
            
        	Part filePart = request.getPart("image");
        	String filename = UploadImage.extractFileName(filePart);
            String contentType = filePart.getContentType();
  
        
            
               UploadImage.copyFile(filename, contentType, image);
        	   candidate.setImg(filename);
        	   
        } else {
        	
        	candidate.setImg(request.getParameter("prevImg"));
        }
        
        	   
        	   REST_URI = "http://127.0.0.1:8080/_rest/candidate/update";
        	   WebTarget webTarget = client.target(REST_URI);
        	   Builder builder = webTarget.request();
        	   
        	   Entity<Candidate> ecandidates = Entity.entity(candidate, MediaType.APPLICATION_JSON);
        	   
        	   GenericType<List<Candidate>> genericList = new GenericType<List<Candidate>>() {};
        	   
        	   List<Candidate> candidatesList = builder.put(ecandidates,genericList);
        	   
        	   request.setAttribute("CANDIDATES_LIST", candidatesList);
        	   
        	   request.setAttribute("MSG", "Candidate Updated Successfully" );
        	   
        	  RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/candidates.jsp");
      		  dispatcher.forward(request, response);
        	   
        	   
           
		
		
		
	}
	
	/**
	 * @author DGautam
	 * @param request
	 * @param image
	 * @return
	 * Checks for any empty file input in the form while uploading any file.
	 */
	
	private InputStream getImageStream(HttpServletRequest request, String image){       
        try {
            Part part = request.getPart(image);
            String header = part.getHeader("content-disposition");
            InputStream input = null;
            if(header.contains("filename")){
                input =  part.getInputStream();
            }            
            return input;
        } catch (IOException | ServletException e ){
            e.printStackTrace();
        }
        return null;
    }
	
	

	

}

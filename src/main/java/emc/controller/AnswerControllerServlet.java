package emc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import emc.model.Candidate;
import emc.model.CandidateAnswer;
import emc.model.CandidateAnswerPK;
import emc.model.Question;

/**
 * Servlet implementation class AnswerControllerServlet
 * Handles answers CRUD operation request. Serves as a client for rest services. 
 */

public class AnswerControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Client client = ClientBuilder.newClient();

	private String REST_URI;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String action = request.getServletPath().toLowerCase();
		String VIEW_URL=null;
		int candidateId = Integer.parseInt(request.getParameter("id"));
		RequestDispatcher dispatcher;
		
		switch (action) {
		  
		case "/admin/candidate/questions":
			
			List<Question> questionsList = getQuestionsForCandidateToAnswer(candidateId);
			request.setAttribute("QUESTIONS_LIST", questionsList);
			
			Candidate candidateInfo = getCandidateInfoById(candidateId);
			request.setAttribute("CANDIDATE_INFO", candidateInfo);
			
			VIEW_URL = "/admin/candidatequestions.jsp";
			
			break;
		
		
		}
		
		 dispatcher=request.getRequestDispatcher(VIEW_URL);
		  dispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		List<CandidateAnswer> candidateAnswers=null;
		
		 int candidateId = Integer.parseInt(request.getParameter("id"));
		
		  String []questionIds = request.getParameterValues("questionId");
		  for(String questionId:questionIds) {
			  
			  
			 
			 
			  int questionid = Integer.parseInt(questionId);
			
			  int answer = Integer.parseInt(request.getParameter("question_"+questionid));
			  
			  REST_URI = "http://127.0.0.1:8080/_rest/answers/insert/"+candidateId;
			   
			  
			  Question question = new Question();
			  Candidate candidate = new Candidate();
			  CandidateAnswerPK candidateAnswerPK = new CandidateAnswerPK();
			  CandidateAnswer candidateAnswer = new CandidateAnswer();
			  
			  
			  question.setQuestionId(questionid);
			  candidate.setCandidateId(candidateId);
			  
			  candidateAnswerPK.setCandidateId(candidateId);
			  candidateAnswerPK.setQuestionId(questionid);
			   
			  
			  candidateAnswer.setId(candidateAnswerPK);
			  candidateAnswer.setQuestion(question);
			  candidateAnswer.setCandidate(candidate);
			  candidateAnswer.setAnswer(answer);
			   
			  WebTarget webTarget = client.target(REST_URI);
	    	  Builder builder = webTarget.request();
	    	  
	         Entity<CandidateAnswer> eCandidateAnswer = Entity.entity(candidateAnswer, MediaType.APPLICATION_JSON);
    	   
    	     GenericType<List<CandidateAnswer>> genericList = new GenericType<List<CandidateAnswer>>() {};
    	   
    	     candidateAnswers = builder.post(eCandidateAnswer,genericList);
			
			  
			  
		  }
		  
		   
    	   
    
    	   
    	   request.setAttribute("C_ANSWERS_LIST", candidateAnswers);
    	   
		   
    	   RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/candidateanswer.jsp");
   		  dispatcher.forward(request, response);
		
		
	}
	
	private List<Question> getQuestionsForCandidateToAnswer(int candidateId) {
		
		REST_URI = "http://127.0.0.1:8080/_rest/candidate/questions/"+candidateId ;
		
		WebTarget webTarget = client.target(REST_URI);

	     Builder builder = webTarget.request();
	
	   	GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
		
		List<Question> questionsList = builder.get(genericList);
		
		return questionsList;
	    	
		
	}
	
	private Candidate getCandidateInfoById(int candidateId) throws ServletException, IOException {
		REST_URI = "http://127.0.0.1:8080/_rest/candidate/get/"+candidateId;
		WebTarget webTarget = client.target(REST_URI);
		Builder builder = webTarget.request();
		Candidate candidate = builder.get(Candidate.class);
	   return candidate;
		
	}

}

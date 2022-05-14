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
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import emc.model.Question;

/**
 * Servlet implementation class QuestionControllerServlet
 * Handles question CRUD operation request. Serves as a client for rest services. 
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
		
		String VIEW_URL = null;
		
		 switch (action) {
		 
		  case "/questions":

			  request.setAttribute("QUESTIONS_LIST", questionsList);
			  VIEW_URL = "/questions.jsp";
			  break;
			  
		  case "/admin/questions":
			  request.setAttribute("QUESTIONS_LIST", questionsList);
			  VIEW_URL = "/admin/questions.jsp";
			  break;
	     
		  case "/admin/questions/add":
			  VIEW_URL = "/admin/addquestion.jsp";
			  break;
			  

		  
		  case "/admin/question/update":
			  loadQuestionToUpdate(request, response);
			  VIEW_URL = "/admin/questionupdate.jsp";
			  break;
			  

		  case "/admin/questions/delete":
			List<Question> questionList = deleteQuestion(request, response);
			VIEW_URL = "/admin/questions.jsp";
			request.setAttribute("MSG", "Question deleted successfully");
			request.setAttribute("QUESTIONS_LIST", questionsList);
			break;
		  
			  

		 }
		 


		  dispatcher=request.getRequestDispatcher(VIEW_URL);
		  dispatcher.forward(request, response);

		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath().toLowerCase();
		
		if(action.equalsIgnoreCase("/admin/question/update")) {
			
			updateQuestion(request, response);
			
			
		}else {
		
		String question = request.getParameter("question");
		System.out.println(question);
		
		Question nquestion = new Question();
		
		nquestion.setQuestion(question);
		
		REST_URI = "http://127.0.0.1:8080/_rest/questions/add";
		WebTarget webTarget = client.target(REST_URI);
		Builder builder = webTarget.request();
		
		Entity<Question> equestion = Entity.entity(nquestion,MediaType.APPLICATION_JSON);
		
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
		
		List<Question> questionList = builder.post(equestion,genericList);
		
		request.setAttribute("QUESTIONS_LIST", questionList);
		request.setAttribute("MSG", "Question Added Successfully");
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/admin/questions.jsp");
		dispatcher.forward(request, response);
		
		
		}
		
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
		
		REST_URI = "http://127.0.0.1:8080/_rest/questions/all";
		 
		 WebTarget webTarget = client.target(REST_URI);
		 
	     Builder builder = webTarget.request();
	
	   	GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
		
		List<Question> questionsList = builder.get(genericList);
		
		return questionsList;
		
	}
	private List<Question> deleteQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String questionId=request.getParameter("id");
		REST_URI = "http://127.0.0.1:8080/_rest/questions/delete/"+questionId;
		WebTarget webTarget = client.target(REST_URI);
		Builder builder = webTarget.request();
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
		List<Question>questionList=builder.delete(genericList);
		request.setAttribute("QUESTIONS_LIST", questionList);
		return questionList;
		
		
	}

	

	//mo start
		private void loadQuestionToUpdate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			String id = request.getParameter("id");
			REST_URI = "http://127.0.0.1:8080/_rest/questions/get/"+id;
			WebTarget webTarget = client.target(REST_URI);
			Builder builder = webTarget.request();
			Question question = builder.get(Question.class);
			
			request.setAttribute("QUESTION",question);
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("/admin/questionupdate.jsp");
			dispatcher.forward(request, response);
			
		}
		//mo ends
		
		
		private void updateQuestion(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
			Question question =new Question();
			question.setQuestionId(Integer.parseInt(request.getParameter("id")));
			question.setQuestion(request.getParameter("question"));
			
			REST_URI = "http://127.0.0.1:8080/_rest/questions/update";
			Client c=ClientBuilder.newClient();
			WebTarget wt=c.target(REST_URI);
			Builder b=wt.request();
			//Here we create an Entity of a Question object as JSON string format
			Entity<Question> questionEntity=Entity.entity(question,MediaType.APPLICATION_JSON);
			//Create a GenericType to be able to get List of objects
			//This will be the second parameter of post method
			GenericType<List<Question>> genericListQuestion = new GenericType<List<Question>>() {};
			
	
			List<Question> questionsList=b.put(questionEntity, genericListQuestion);
			
			request.setAttribute("QUESTIONS_LIST", questionsList);
			request.setAttribute("MSG", "Question Updated Successfully");
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("/admin/questions.jsp");
			dispatcher.forward(request, response);
		}
		
		
	
	
	

	}





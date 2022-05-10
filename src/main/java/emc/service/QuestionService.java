package emc.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import emc.model.Question;

@Path("/questions")
public class QuestionService {
	
	/**
	 * Entity Manager
	 */
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("emc-rest");
	private EntityManager em = emf.createEntityManager();
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Question> getAllQuestions() throws ServletException, IOException
	{
		
		String query = "SELECT q FROM Question q ORDER BY q.questionId DESC";
		List<Question> questionsList = em.createQuery(query).getResultList(); 
		return questionsList;
				
   }
	//mo start
   @POST
   @Path("/add")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public List<Question> addQuestion(Question question) throws ServletException, IOException {
	   EntityManager em = emf.createEntityManager();
	   em.getTransaction().begin();
	   em.persist(question);
	   em.getTransaction().commit();
	   List<Question> questionList = getAllQuestions();
	   
	return questionList;
	   

   }//mo end
   
	//mo start
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public List<Question> updateQuestion(Question question) throws ServletException, IOException {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Question nquestion = em.find(Question.class, question.getQuestionId());
		if (nquestion!= null) {
			em.merge(question);//update line
		}
		em.getTransaction().commit();
		List<Question> questionsList = getAllQuestions();
		return questionsList;
		//mo end
	}
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Question getQuestionById(@PathParam("id") int questionId) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Question question=em.find(Question.class,questionId);
		em.getTransaction().commit();
		return question;
		
	}


   }
   @DELETE
   @Path("/delete/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   
   public List<Question>deleteQuestion(@PathParam("id")int questionId) throws ServletException, IOException{
	   em.getTransaction().begin();
	   Question question = em.find(Question.class, questionId);
	  if (question!=null) {
		  em.remove(question);
	  }
	  em.getTransaction().commit();
	  List<Question>questionlist=getAllQuestions();
	  return questionlist;
	   
   }


   

	


}

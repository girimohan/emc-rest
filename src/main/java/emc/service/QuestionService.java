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
		
		String query = "SELECT q FROM Question q";
		List<Question> questionsList = em.createQuery(query).getResultList(); 
		return questionsList;
				
   }
	
   @POST
   @Path("/add")
   @Produces(MediaType.APPLICATION_JSON)
   public List<Question> addQuestion(){
	   
	return null;
	   
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

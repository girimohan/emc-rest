package emc.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import emc.model.Question;

@Path("/question")
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
	
	

}

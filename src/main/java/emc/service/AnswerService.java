package emc.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import emc.model.CandidateAnswer;


/**
 * 
 * @author DGautam
 * REST SERVICE Class Handles crud operation for candidate answers.
 *
 */


@Path("/answers")
public class AnswerService {
	
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("emc-rest");
	EntityManager em = emf.createEntityManager();
	
	/**
	 * @author DGautam
	 * @param Candidate Object
	 * Handles post request to insert candidate.
	 * @throws IOException 
	 * @throws ServletException 
	 */
   
	@POST
	@Path("/insert/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<CandidateAnswer> insertCandidateAnswer(@PathParam ("id") int candidateId,CandidateAnswer candidateAnswer) throws ServletException, IOException {
		em.getTransaction().begin();
		em.persist(candidateAnswer);
		em.getTransaction().commit();
		List<CandidateAnswer> candidateAnswerList = getCandidateAnswer(candidateId);
		return candidateAnswerList;
		
	}
	
	/**
	 * @author DGautam
	 * @param candidateId
	 * @return 
	 */
	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public List<CandidateAnswer> getCandidateAnswer(@PathParam ("id") int candidateId) {
		
		String query = "SELECT ca FROM CandidateAnswer ca WHERE ca.id.candidateId =?1";
		
		@SuppressWarnings("unchecked")
		
		List<CandidateAnswer> candidateAnswerList = em.createQuery(query).setParameter(1, candidateId).getResultList(); 
		
		return candidateAnswerList;
	
		
		
		
		
	}

}

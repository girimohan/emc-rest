package emc.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import emc.model.Candidate;


@Path("/candidate")
public class CandidateService {
	
	
	/**
	 * Entity Manager
	 */
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("emc-rest");
	private EntityManager em = emf.createEntityManager();
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Candidate> getAllCandidates() throws ServletException, IOException
	{
		
		String query = "SELECT c FROM Candidate c";
		List<Candidate> candidatesList = em.createQuery(query).getResultList(); 
		return candidatesList;
				
   }
  
 
 
  
  
}

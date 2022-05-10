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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import emc.model.Candidate;


@Path("/candidate")
public class CandidateService {
	
	
	/**
	 * Entity Manager
	 */
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("emc-rest");
	EntityManager em = emf.createEntityManager();
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Candidate> getAllCandidates() throws ServletException, IOException
	{
		
		String query = "SELECT c FROM Candidate c";
		List<Candidate> candidatesList = em.createQuery(query).getResultList(); 
		return candidatesList;
		
				
   }
	
	/**
	 * @author DGautam
	 * @param candidate
	 */
   
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertCandidate(Candidate candidate) {
		em.getTransaction().begin();
		em.persist(candidate);
		em.getTransaction().commit();
		
	}
	
	/**
	 * @author DGautam
	 * @throws IOException 
	 * @throws ServletException 
	 */
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Candidate> deleteCandidate(@PathParam("id") int candidateId) throws ServletException, IOException{
		em.getTransaction().begin();
		Candidate candidate = em.find(Candidate.class, candidateId);
		if(candidate != null) {
			em.remove(candidate);
		}
		em.getTransaction().commit();
		
		List<Candidate> candidatesList = getAllCandidates();
		return candidatesList;
	}
	
	
	
	
  
	
 
  
  
}

package emc.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import emc.model.Candidate;

@Path("/candidates")
public class CandidateService {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("emc-rest");
	private EntityManager em = emf.createEntityManager();
	
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Candidate> getCandidates(){
		em.getTransaction().begin();
		List<Candidate> candidatesList = em.createQuery("SELECT c FROM Candidate c").getResultList();
		em.getTransaction().commit();
		return candidatesList;		
  }

}

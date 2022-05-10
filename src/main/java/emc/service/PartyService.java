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

import emc.model.Party;
import emc.model.Question;

@Path("/parties")
public class PartyService {
	
	/**
	 * Entity Manager
	 */
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("emc-rest");
	private EntityManager em = emf.createEntityManager();
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Party> getAllParties() throws ServletException, IOException
	{
		
		String query = "SELECT p FROM Party p";
		List<Party> partiesList = em.createQuery(query).getResultList(); 
		return partiesList;
				
   }

}

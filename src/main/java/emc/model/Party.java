package emc.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the parties database table.
 * 
 */
@Entity
@Table(name="parties")
@NamedQuery(name="Party.findAll", query="SELECT p FROM Party p")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Party implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="party_id")
	private int partyId;

	@Lob
	private String party;

	//bi-directional many-to-one association to Candidate
	@OneToMany(fetch = FetchType.LAZY,mappedBy="party")
	@JsonIgnoreProperties(value="party",allowSetters=true)
	private List<Candidate> candidates;

	public Party() {
	}

	public int getPartyId() {
		return this.partyId;
	}

	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}

	public String getParty() {
		return this.party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public List<Candidate> getCandidates() {
		return this.candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public Candidate addCandidate(Candidate candidate) {
		getCandidates().add(candidate);
		candidate.setParty(this);

		return candidate;
	}

	public Candidate removeCandidate(Candidate candidate) {
		getCandidates().remove(candidate);
		candidate.setParty(null);

		return candidate;
	}

}
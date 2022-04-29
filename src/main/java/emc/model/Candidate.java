package emc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the candidates database table.
 * 
 */
@Entity
@Table(name="candidates")
@NamedQuery(name="Candidate.findAll", query="SELECT c FROM Candidate c")
public class Candidate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="candidate_id")
	private int candidateId;

	private int age;

	private String firstname;

	private String party;

	@Lob
	private String password;

	private String profession;

	@Lob
	private String salt;

	private String surname;

	private String username;

	//bi-directional many-to-one association to CandidateAnswer
	@OneToMany(mappedBy="candidate")
	private List<CandidateAnswer> candidateAnswers;

	public Candidate() {
	}

	public int getCandidateId() {
		return this.candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getParty() {
		return this.party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<CandidateAnswer> getCandidateAnswers() {
		return this.candidateAnswers;
	}

	public void setCandidateAnswers(List<CandidateAnswer> candidateAnswers) {
		this.candidateAnswers = candidateAnswers;
	}

	public CandidateAnswer addCandidateAnswer(CandidateAnswer candidateAnswer) {
		getCandidateAnswers().add(candidateAnswer);
		candidateAnswer.setCandidate(this);

		return candidateAnswer;
	}

	public CandidateAnswer removeCandidateAnswer(CandidateAnswer candidateAnswer) {
		getCandidateAnswers().remove(candidateAnswer);
		candidateAnswer.setCandidate(null);

		return candidateAnswer;
	}

}
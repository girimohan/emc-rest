package emc.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the candidates database table.
 * 
 */
@Entity
@Table(name="candidates")
@NamedQuery(name="Candidate.findAll", query="SELECT c FROM Candidate c")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Candidate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="candidate_id")
	private int candidateId;

	private int age;

	private String firstname;

	private String ideology;

	private String img;

	@Lob
	private String motive;

	private String profession;

	private String surname;

	//bi-directional many-to-one association to CandidateAnswer
	@OneToMany(fetch = FetchType.LAZY,mappedBy="candidate")
	@JsonIgnoreProperties("candidate")
	private List<CandidateAnswer> candidateAnswers;

	//bi-directional many-to-one association to Party
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="party_id")
	@JsonIgnoreProperties("candidates")
	private Party party;

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

	public String getIdeology() {
		return this.ideology;
	}

	public void setIdeology(String ideology) {
		this.ideology = ideology;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getMotive() {
		return this.motive;
	}

	public void setMotive(String motive) {
		this.motive = motive;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public Party getParty() {
		return this.party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

}
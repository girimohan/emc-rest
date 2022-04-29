package emc.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the candidate_answers database table.
 * 
 */
@Entity
@Table(name="candidate_answers")
@NamedQuery(name="CandidateAnswer.findAll", query="SELECT c FROM CandidateAnswer c")
public class CandidateAnswer implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CandidateAnswerPK id;

	private byte answer;

	//bi-directional many-to-one association to Candidate
	@ManyToOne
	@JoinColumn(name="candidate_id")
	private Candidate candidate;

	//bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name="question_id")
	private Question question;

	public CandidateAnswer() {
	}

	public CandidateAnswerPK getId() {
		return this.id;
	}

	public void setId(CandidateAnswerPK id) {
		this.id = id;
	}

	public byte getAnswer() {
		return this.answer;
	}

	public void setAnswer(byte answer) {
		this.answer = answer;
	}

	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
package emc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the questions database table.
 * 
 */
@Entity
@Table(name="questions")
@NamedQuery(name="Question.findAll", query="SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="question_id")
	private int questionId;

	@Lob
	private String question;

	//bi-directional many-to-one association to CandidateAnswer
	@OneToMany(mappedBy="question")
	private List<CandidateAnswer> candidateAnswers;

	public Question() {
	}

	


	public Question(String question) {
		// TODO Auto-generated constructor stub
	}




	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<CandidateAnswer> getCandidateAnswers() {
		return this.candidateAnswers;
	}

	public void setCandidateAnswers(List<CandidateAnswer> candidateAnswers) {
		this.candidateAnswers = candidateAnswers;
	}

	public CandidateAnswer addCandidateAnswer(CandidateAnswer candidateAnswer) {
		getCandidateAnswers().add(candidateAnswer);
		candidateAnswer.setQuestion(this);

		return candidateAnswer;
	}

	public CandidateAnswer removeCandidateAnswer(CandidateAnswer candidateAnswer) {
		getCandidateAnswers().remove(candidateAnswer);
		candidateAnswer.setQuestion(null);

		return candidateAnswer;
	}

}
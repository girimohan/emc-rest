package emc.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the candidate_answers database table.
 * 
 */
@Embeddable
public class CandidateAnswerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="candidate_id", insertable=false, updatable=false)
	private int candidateId;

	@Column(name="question_id", insertable=false, updatable=false)
	private int questionId;

	public CandidateAnswerPK() {
	}
	public int getCandidateId() {
		return this.candidateId;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	public int getQuestionId() {
		return this.questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CandidateAnswerPK)) {
			return false;
		}
		CandidateAnswerPK castOther = (CandidateAnswerPK)other;
		return 
			(this.candidateId == castOther.candidateId)
			&& (this.questionId == castOther.questionId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.candidateId;
		hash = hash * prime + this.questionId;
		
		return hash;
	}
}
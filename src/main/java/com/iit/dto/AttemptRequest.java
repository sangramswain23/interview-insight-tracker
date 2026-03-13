package com.iit.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AttemptRequest {

    @NotNull
    private Long questionId;

    @NotNull
    private Boolean correct;

    @Min(1)
    private int timeTakenSeconds;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public int getTimeTakenSeconds() {
		return timeTakenSeconds;
	}

	public void setTimeTakenSeconds(int timeTakenSeconds) {
		this.timeTakenSeconds = timeTakenSeconds;
	}

    
}

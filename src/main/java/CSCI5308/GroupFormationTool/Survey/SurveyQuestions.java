package CSCI5308.GroupFormationTool.Survey;

import java.util.LinkedList;
import java.util.List;

import CSCI5308.GroupFormationTool.Question.IQuestion;

public class SurveyQuestions implements ISurveyQuestions{

	@Override
	public List<IQuestion> fetchSurveyQuestions(ISurveyPersistence surveyDB, long courseId) {
    	List<Long> surveyQuestionList = surveyDB.getSurveyQuestionsForCourse(courseId);
		List<IQuestion> groupFormationQuestions = new LinkedList<>();
		if (surveyQuestionList != null)
		{
			for (Long questionId : surveyQuestionList)
			{
				IQuestion question = surveyDB.getSurveyQuestion(questionId);
				if (question != null)
				{
					question.setId(questionId);
					groupFormationQuestions.add(question);
				}
			}
		} 
		return groupFormationQuestions;
	}
}

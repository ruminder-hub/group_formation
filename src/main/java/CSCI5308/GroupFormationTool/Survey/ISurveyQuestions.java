package CSCI5308.GroupFormationTool.Survey;

import java.util.List;

import CSCI5308.GroupFormationTool.Question.IQuestion;

public interface ISurveyQuestions
{
	public List<IQuestion> fetchSurveyQuestions(ISurveyPersistence surveyDB, long courseId);
}

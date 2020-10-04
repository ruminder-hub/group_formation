package CSCI5308.GroupFormationTool.SurveyTest;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Question.IOption;
import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.QuestionsTest.QuestionAbstractFactoryMock;
import CSCI5308.GroupFormationTool.Survey.IResponse;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;

public class SurveyDBMock implements ISurveyPersistence
{
    @Override
    public List<IQuestion> getAlreadyAddedQuestions(Long courseId)
    {
    	 List<IQuestion> questionList = new ArrayList<>();
         IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
         question.setId(10);
         question.setTitle("Mock Title");
         questionList.add(question);
         
        return questionList;
    }

    @Override
    public List<IQuestion> getNotAddedQuestions(Long courseId, String bannerId)
    {
    	List<IQuestion> questionList = new ArrayList<>();
        IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
        question.setId(10);
        question.setTitle("Mock Title");
        questionList.add(question);
        
       return questionList;
    }

    @Override
    public boolean addQuestionToSurvey(Long questionId, Long courseId)
    {
    	IQuestion question = getSurveyQuestion(questionId);
    	if (question != null)
    	{
    		return true;
    	}
        return false;
    }

    @Override
    public boolean deleteQuestionFromSurvey(Long questionId, Long courseId) {
        return true;
    }

    @Override
    public boolean publishSurvey(Long courseId) {
        return true;
    }

    @Override
    public boolean disableSurvey(Long courseId) {
        return true;
    }

    @Override
    public boolean isSurveyPublished(Long courseId) {
        return true;
    }

    @Override
    public List<IQuestion> getSurveyQuestions(Long courseId) {
        List<IQuestion> questionList = new ArrayList<>();
        IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
        question.setId(10);
        question.setTitle("Mock Title");
        question.setQuestion("Mock Question");
        question.setDateCreated("Mock Date");
        question.setAnswerOptions(new ArrayList<IOption>());
        questionList.add(question);
        return questionList;
    }

    @Override
    public List<IOption> getSurveyQuestionOptions(Long questionId) {
        List<IOption> optionList = new ArrayList<>();
        IOption option = QuestionAbstractFactoryMock.instance().getOption();
        option.setText("Mock text");
        option.setValue("Mock value");
        optionList.add(option);
        return optionList;
    }

    @Override
    public boolean storeResponses(IResponse response, int index) {
        response.setQuestionId(10);
        response.setBannerId("B-555555");
        response.setCourseId(10);
        response.setResponseList(new String[index]);
        return true;
    }
    
    @Override
    public List<Long> getSurveyQuestionsForCourse(Long courseId){
    	List<Long> surveyQuestions = new ArrayList<Long>();
    	surveyQuestions.add(1111L);
        return surveyQuestions;
    }
    
    @Override
    public IQuestion getSurveyQuestion(Long questionId){
    	IQuestion question = QuestionAbstractFactoryMock.instance().getQuestion();
    	question.setId(questionId);
    	question.setTitle("Provide your feedback");
        return question;
    }

	@Override
	public List<String> getStudentBannersWhoFilledSurvey(long courseId) {
		List<String> studentList = new ArrayList<String>();
		studentList.add("B-0000");
        return studentList;
	}

	@Override
	public IResponse getStudentResponseCorrespondingToQuestion(long qId, long courseId, String bannerId) {
		IResponse response = SurveyAbstractFactoryMock.instance().getResponse();
		response.setQuestionId(qId);
		response.setCourseId(courseId);
		response.setBannerId(bannerId);
		return response;
	}
}

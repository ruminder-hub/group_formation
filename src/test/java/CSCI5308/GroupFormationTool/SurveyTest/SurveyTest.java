package CSCI5308.GroupFormationTool.SurveyTest;

import CSCI5308.GroupFormationTool.Question.IOption;
import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.Option;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Survey.IResponse;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.Response;
import CSCI5308.GroupFormationTool.Survey.SurveyAbstractFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
@SuppressWarnings("deprecation")
public class SurveyTest
{
    @Test
    public void getSurveyQuestionsTest()
    {
        ISurveyPersistence surveyDBMock = SurveyAbstractFactoryMock.instance().getSurveyDBMock();
        List<IQuestion> questionList = surveyDBMock.getSurveyQuestions(10L);
        Assert.isTrue(questionList.size() == 1);
        Assert.isTrue(questionList.get(0).getId() == 10);
    }

    @Test
    public void getSurveyQuestionOptionsTest()
    {
        ISurveyPersistence surveyDBMock = SurveyAbstractFactoryMock.instance().getSurveyDBMock();
        List<IOption> optionList = surveyDBMock.getSurveyQuestionOptions(10L);
        Assert.isTrue(optionList.size() == 1);
        Assert.isTrue(optionList.get(0).getText().equals("Mock text"));
        Assert.isTrue(optionList.get(0).getValue().equals("Mock value"));
    }

    @Test
    public void storeResponsesTest()
    {
    	ISurveyPersistence surveyDBMock = SurveyAbstractFactoryMock.instance().getSurveyDBMock();
		IResponse response = SurveyAbstractFactoryMock.instance().getResponse();;
		response.setBannerId("B-1111");
		response.setCourseId(123L);
		response.setQuestionId(11L);
		Assert.isTrue(surveyDBMock.storeResponses(response, 2));
    }

    @Test
	public void getAlreadyAddedQuestionsTest() 
    {
		ISurveyPersistence surveyDBMock = SurveyAbstractFactoryMock.instance().getSurveyDBMock();
		Long courseId = 123L;
		Assert.isTrue(surveyDBMock.getAlreadyAddedQuestions(courseId).size() == 1);
	}

	@Test
	public void getNotAddedQuestionsTest()
	{
		ISurveyPersistence surveyDBMock = SurveyAbstractFactoryMock.instance().getSurveyDBMock();
		Long courseId = 123L;
		Assert.isTrue(surveyDBMock.getNotAddedQuestions(courseId, "B-0333").size() == 1);
	}

	@Test
	public void addQuestionToSurveyTest()
	{
		ISurveyPersistence surveyDBMock = SurveyAbstractFactoryMock.instance().getSurveyDBMock();
		Assert.isTrue(surveyDBMock.addQuestionToSurvey(12L, 1234L));
	}

	@Test
	public void deleteQuestionFromSurveyTest() 
	{
		ISurveyPersistence surveyDBMock = SurveyAbstractFactoryMock.instance().getSurveyDBMock();
		Assert.isTrue(surveyDBMock.deleteQuestionFromSurvey(12L, 1234L));
	}

	@Test
	public void publishSurveyTest() 
	{
		ISurveyPersistence surveyDBMock = SurveyAbstractFactoryMock.instance().getSurveyDBMock();
		Assert.isTrue(surveyDBMock.publishSurvey(1111L));
	}

	@Test
	public void disableSurveyTest() {
		ISurveyPersistence surveyDBMock = SurveyAbstractFactoryMock.instance().getSurveyDBMock();
		Assert.isTrue(surveyDBMock.disableSurvey(2222L));
	}

	@Test
	public void isSurveyPublished() 
	{
		ISurveyPersistence surveyDBMock = SurveyAbstractFactoryMock.instance().getSurveyDBMock();
		Assert.isTrue(surveyDBMock.isSurveyPublished(3333L));
	}


	@Test
	public void getSurveyQuestionsForCourseTest() 
	{
		ISurveyPersistence surveyDBMock = SurveyAbstractFactoryMock.instance().getSurveyDBMock();
		List<Long> surveyQuestionList = surveyDBMock.getSurveyQuestionsForCourse(1231L);
		Assert.isTrue(surveyQuestionList.size() > 0);
	}

	@Test
	public void getSurveyQuestionTest() 
	{
		ISurveyPersistence surveyDBMock = SurveyAbstractFactoryMock.instance().getSurveyDBMock();
		IQuestion question = surveyDBMock.getSurveyQuestion(123L);
		Assert.isTrue(question.getId() == 123L);
	}

	@Test
	public void getStudentBannersWhoFilledSurveyTest() 
	{
		ISurveyPersistence surveyDBMock = SurveyAbstractFactoryMock.instance().getSurveyDBMock();
		List<String> studentList = surveyDBMock.getStudentBannersWhoFilledSurvey(1223L);
		Assert.isTrue(studentList.size() > 0);
	}

	@Test
	public void getStudentResponseCorrespondingToQuestionTest()
	{
		ISurveyPersistence surveyDBMock = SurveyAbstractFactoryMock.instance().getSurveyDBMock();
		IResponse response = surveyDBMock.getStudentResponseCorrespondingToQuestion(12L, 22323L, "B-000");
		Assert.isTrue(response.getBannerId().equals("B-000"));
	}
}

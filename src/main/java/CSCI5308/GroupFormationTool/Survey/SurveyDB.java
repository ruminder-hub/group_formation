package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.AccessControl.AccessControlAbstractFactory;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Question.IOption;
import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.QuestionAbstractFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SurveyDB implements ISurveyPersistence {
    private Logger log = LoggerFactory.getLogger(SurveyDB.class);

    @Override
    public List<IQuestion> getAlreadyAddedQuestions(Long courseId)
    {
        List<IQuestion> alreadyAddedQuestionList = new ArrayList<>();
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spAlreadyAddedQuestions(?)");
            proc.setParameter(1, courseId);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    IQuestion question = QuestionAbstractFactory.instance().getQuestion();
                    Long id = results.getLong("id");
                    String title = results.getString("title");
                    String text = results.getString("text");
                    question.setId(id);
                    question.setTitle(title);
                    question.setQuestion(text);
                    alreadyAddedQuestionList.add(question);
                }
            }
        }
        catch (SQLException e)
        {
            log.error("Error occured in loading Already added questions : " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return alreadyAddedQuestionList;
    }

    @Override
    public List<IQuestion> getNotAddedQuestions(Long courseId, String bannerId)
    {
        List<IQuestion> notAddedQuestionList = new ArrayList<>();
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spNotAddedQuestions(?,?)");
            proc.setParameter(1, courseId);
            proc.setParameter(2, bannerId);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    IQuestion question = QuestionAbstractFactory.instance().getQuestion();
                    Long id = results.getLong("id");
                    String title = results.getString("title");
                    String text = results.getString("text");
                    question.setId(id);
                    question.setTitle(title);
                    question.setQuestion(text);
                    notAddedQuestionList.add(question);
                }
            }
        }
        catch (SQLException e)
        {
            log.error("Error occured in loading Not added questions : " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return notAddedQuestionList;
    }

    @Override
    public boolean addQuestionToSurvey(Long questionId, Long courseId)
    {
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spAddQuestionToSurvey(?,?)");
            proc.setParameter(1, questionId);
            proc.setParameter(2, courseId);
            proc.execute();
        }
        catch (SQLException e)
        {
            log.error("Error occured in Adding question to survey : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return true;
    }

    @Override
    public boolean deleteQuestionFromSurvey(Long questionId, Long courseId)
    {
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spDeleteQuestionFromSurvey(?,?)");
            proc.setParameter(1, questionId);
            proc.setParameter(2, courseId);
            proc.execute();
        }
        catch (SQLException e)
        {
            log.error("Error occured in Deleting question from survey : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return true;
    }

    @Override
    public boolean publishSurvey(Long courseId)
    {
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spPublishSurvey(?)");
            proc.setParameter(1, courseId);
            proc.execute();
        }
        catch (SQLException e)
        {
            log.error("Error occured in Adding question to survey : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return true;
    }

    @Override
    public boolean disableSurvey(Long courseId) {
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spDisableSurvey(?)");
            proc.setParameter(1, courseId);
            proc.execute();
        }
        catch (SQLException e)
        {
            log.error("Error occured in Disabling the survey : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        finally
        {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return true;
    }

    @Override
    public boolean isSurveyPublished(Long courseId)
    {
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spIsSurveyPublished(?)");
            proc.setParameter(1, courseId);
            ResultSet results = proc.executeWithResults();
            if (results.next())
            {
                Long status = results.getLong("Status");
                if (status == 0)
                    return false;
                else
                    return true;
            }
        }
        catch (SQLException e)
        {
            log.error("Error occured in Adding question to survey : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        finally
        {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return false;
    }

    @Override
    public List<IQuestion> getSurveyQuestions(Long courseId)
    {
        CallStoredProcedure proc = null;
        List<IQuestion> questionList = new ArrayList<>();
        try
        {
            proc = new CallStoredProcedure("spSurveyQuestionsByCourseId(?)");
            proc.setParameter(1, courseId);
            ResultSet results = proc.executeWithResults();

            while (results.next())
            {
                IQuestion question = QuestionAbstractFactory.instance().getQuestion();
                question.setId(results.getLong(1));
                question.setTitle(results.getString(2));
                question.setQuestion(results.getString(3));
                question.setType(results.getString(4));
                question.setDateCreated(results.getString(5));
                questionList.add(question);
            }
        }
        catch (SQLException e)
        {
            log.error("Error while retriving questions for survey : " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return questionList;
    }

    @Override
    public List<IOption> getSurveyQuestionOptions(Long questionId)
    {
        CallStoredProcedure proc = null;
        List<IOption> optionList = new ArrayList<IOption>();
        try
        {
            proc = new CallStoredProcedure("spSurveyQuestionOptionsByQuestionId(?)");
            proc.setParameter(1, questionId);
            ResultSet results = proc.executeWithResults();

            while (results.next())
            {
                IOption option = QuestionAbstractFactory.instance().getOption();
                option.setValue(results.getString(3));
                option.setText(results.getString(2));
                optionList.add(option);
            }

        }
        catch (SQLException e)
        {
            log.error("Error while retriving options for question : " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return optionList;
    }

    @Override
    public boolean storeResponses(IResponse response, int index)
    {
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spStoreSurveyResponse(?, ?, ?, ?)");
            proc.setParameter(1, response.getQuestionId());
            proc.setParameter(2, response.getBannerId());
            proc.setParameter(3, response.getCourseId());
            proc.setParameter(4, response.getResponseList()[index]);
            proc.execute();
        }
        catch (SQLException e)
        {
            log.error("Error while inserting survey responses: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return true;
    }

	@Override
	public List<Long> getSurveyQuestionsForCourse(Long courseId)
    {
		CallStoredProcedure proc = null;
		List<Long> questionIdList = null;
        try
        {
            proc = new CallStoredProcedure("spGetSurveyQuestionIdByCourseId(?)");
            proc.setParameter(1,courseId);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
            	questionIdList = new LinkedList<Long>();
                while (results.next())
                {
                    Long id = results.getLong("QuestionID");
                    questionIdList.add(results.getLong("QuestionID"));
                    
                }
            }
        }
        catch (SQLException e)
        {
            log.error("Error in getting question lists of survey : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return questionIdList;
	}
	
	@Override
	public IQuestion getSurveyQuestion(Long questionId)
	{
		CallStoredProcedure proc = null;
		IQuestion question = null;
        try
        {
            proc = new CallStoredProcedure("spGetQuestionTextAndTypeByQuestionId(?)");
            proc.setParameter(1,questionId);
            ResultSet results = proc.executeWithResults();
            if (results.next())
            {
            	question = QuestionAbstractFactory.instance().getQuestion();
            	question.setTitle(results.getString("title"));
                question.setType(results.getString("type"));
            }
        }
        catch (SQLException e)
        {
            log.error("Error occured in getting question type of survey question: " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return question;
	}
	
	@Override
	public List<String> getStudentBannersWhoFilledSurvey(long courseId)
	{
		CallStoredProcedure proc = null;
		List<String> studentBannerList = null;
        try
        {
            proc = new CallStoredProcedure("spGetAllStudentsOfSurveyByCourseId(?)");
            proc.setParameter(1, courseId);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
            	studentBannerList = new LinkedList<String>();
                while (results.next())
                {
                    studentBannerList.add(results.getString("bannerId"));
                }
            }
        }
        catch (SQLException e)
        {
            log.error("Error in getting student list who filled the survey : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return studentBannerList;
	}
	
	@Override
	public IResponse getStudentResponseCorrespondingToQuestion(long qId, long courseId, String bannerId)
	{
		IResponse response = null;
		
		CallStoredProcedure proc = null;
		IQuestion question = null;
        try
        {
            proc = new CallStoredProcedure("spGetSurveyResponseQuestionIdCourseIdStudentId(?, ?, ?)");
            proc.setParameter(1,qId);
            proc.setParameter(2, courseId);
            proc.setParameter(3, bannerId);
            ResultSet results = proc.executeWithResults();
            if (results.next())
            {
            	response = SurveyAbstractFactory.instance().getResponse();
            	response.setBannerId(bannerId);
            	response.setCourseId(courseId);
            	String questionResponse = results.getString("response");
            	String []resArr = questionResponse.split(",");
            	response.setResponseList(resArr);
            }
        }
        catch (SQLException e)
        {
            log.error("Error occured in getting student response for Question " + qId + " due to: " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
		return response;
	}
}
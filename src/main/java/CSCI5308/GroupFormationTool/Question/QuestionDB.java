package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuestionDB implements IQuestionPersistence
{
	
	private Logger log = LoggerFactory.getLogger(QuestionDB.class);
	
	@Override
	public Boolean saveQuestion(IQuestion question, String id)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateQuestion(?, ?, ?)");
			proc.setParameter(1, question.getTitle());
			proc.setParameter(2, question.getQuestion());
			proc.setParameter(3, question.getType());
			proc.execute();
		}
		catch (SQLException e)
		{
			log.error("Error occured in saving question: " + e.getMessage());
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
		log.info("Question successfully saved");
		Integer questionId = getQuestionIdByTitleTextType(question);
		if (null != questionId)
		{
			saveQuestionForInstructor(questionId, id);
		}
		
		if (question.getType().equals(QuestionTypes.MULTIPLE_CHOICE_CHOOSE_MANY.name()) || question.getType().equals(QuestionTypes.MULTIPLE_CHOICE_CHOOSE_ONE.name())) 
		{
			saveMultipleOptions(question);
		}
		return true;
	}
	
	private boolean saveMultipleOptions(IQuestion question)
	{
		Integer id = getQuestionIdByTitleTextType(question);
		if (id == null)
		{
			return false;
		}
		for(IOption option : question.getAnswerOptions())
		{
			saveQuestionOption(id, option);
		}
		return true;
	}
	
	private boolean saveQuestionForInstructor(Integer questionId, String id)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spAddInstructorToQuestion(?, ?)");
			proc.setParameter(1, id);
			proc.setParameter(2, questionId);
			proc.execute();
		}
		catch (SQLException e)
		{
			log.error("Error occured in saving question for instructor: " + e.getMessage());
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
		log.info("Question successfully added to instructor database");
		return true;
	}
	
	public Integer getQuestionIdByTitleTextType(IQuestion question)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spGetQuestionByTitleTextType(?, ?, ?)");
			proc.setParameter(1, question.getTitle());
			proc.setParameter(2, question.getQuestion());
			proc.setParameter(3, question.getType());
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					Integer questionId = results.getInt(1);
					log.info("Questionid of question with title: " + question.getTitle() + " is: " + questionId);
					return questionId;
				}
			}
			
		}
		catch (SQLException e)
		{
			log.error("Error occured in getting question by title text and type: " + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return null;
	}
	
	private boolean saveQuestionOption(int id, IOption option)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spAddQuestionOptions(?, ?, ?)");
			proc.setParameter(1, id);
			proc.setParameter(2, option.getText());
			proc.setParameter(3, option.getValue());
			proc.execute();
		}
		catch (SQLException e)
		{
			log.error("Error occured in saving question options: " + e.getMessage());
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
		log.info("QuestionOption successfully saved");
		return true;
	}

	@Override
	public List<List<String>> getQuestionsByInstructorID(String instructorId, String order)
	{
		log.debug("Get questions for instructor whith id " + instructorId);
		List<List<String>> questionList = new ArrayList<List<String>>();
		CallStoredProcedure proc = null;
		try
		{
			if (order.equals("Q.title") || order.equals(""))
			{
				proc = new CallStoredProcedure("spGetQuestionsForInstructorFromBannerIdTitle(?,?)");
			}
			else if (order.equals("Q.title DESC"))
			{
				proc = new CallStoredProcedure("spGetQuestionsForInstructorFromBannerIdTitleDESC(?,?)");
			}
			else if (order.equals("Q.dateCreated"))
			{
				proc = new CallStoredProcedure("spGetQuestionsForInstructorFromBannerIdDate(?,?)");
			}
			else if (order.equals("Q.dateCreated DESC"))
			{
				proc = new CallStoredProcedure("spGetQuestionsForInstructorFromBannerIdDateDESC(?,?)");
			}
			proc.setParameter(1, instructorId);
			proc.setParameter(2, order);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					ArrayList<String> questionInfo = new ArrayList<String>();
					questionInfo.add(results.getString(1));
					questionInfo.add(results.getString(2));
					questionInfo.add(results.getString(3));
					questionInfo.add(results.getString(4));
					questionInfo.add(results.getString(5));
					questionList.add(questionInfo);
				}
			}
		}
		catch (SQLException e)
		{
		    log.error("Error occured in getting question by instructorId: " + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		log.info("Instructor with id: " + instructorId + " has " + questionList.size() + " questions");
		return questionList;
	}

	@Override
	public boolean removeQuestionFromDatabase(String questionID)
	{
		log.debug("Removing question with id " + questionID + " from database");
		boolean flag = false;
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spRemoveQuestionfromDB(?)");
			proc.setParameter(1, Long.parseLong(questionID));
			proc.execute();
			flag = true;
		}
		catch (SQLException e)
		{
			log.error("Error occured in removing question from database: " + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return flag;
	}
}

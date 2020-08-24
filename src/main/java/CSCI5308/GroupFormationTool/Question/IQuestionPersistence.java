package CSCI5308.GroupFormationTool.Question;

import java.util.List;

public interface IQuestionPersistence
{
    public Boolean saveQuestion(IQuestion question, String id);

    public Integer getQuestionIdByTitleTextType(IQuestion question);

    public List<List<String>> getQuestionsByInstructorID(String instructorId, String order);

    public boolean removeQuestionFromDatabase(String questionID);  
}

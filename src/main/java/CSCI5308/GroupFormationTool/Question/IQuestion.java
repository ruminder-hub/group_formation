package CSCI5308.GroupFormationTool.Question;

import java.util.List;

public interface IQuestion
{
    public void setDefaults();

    public long getId();

    public void setId(long id);

    public String getTitle();

    public void setTitle(String title);

    public String getQuestion();

    public void setQuestion(String question);

    public String getType();

    public void setType(String type);

    public List<IOption> getAnswerOptions();

    public void setAnswerOptions(List<IOption> answerOptions);

    public String getDateCreated();

    public void setDateCreated(String dateCreated);

    public void saveQuestion(IQuestionPersistence questionPersistence, String id);
}

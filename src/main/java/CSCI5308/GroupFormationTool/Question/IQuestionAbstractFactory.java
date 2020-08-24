package CSCI5308.GroupFormationTool.Question;

public interface IQuestionAbstractFactory
{
    public IQuestionPersistence getQuestionDB();

    public IQuestion getQuestion();

    public IOption getOption();
}

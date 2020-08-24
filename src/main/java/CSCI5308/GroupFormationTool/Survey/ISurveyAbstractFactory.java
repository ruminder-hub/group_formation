package CSCI5308.GroupFormationTool.Survey;

public interface ISurveyAbstractFactory
{
    public ISurveyPersistence getSurveyDB();

    public ISurveyQuestions getSurveyQuestions();

    public IResponse getResponse();

    public IGroup getGroup();

    public IGroupCreationResponse getGroupCreationResponse();
}

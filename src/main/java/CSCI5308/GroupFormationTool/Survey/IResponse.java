package CSCI5308.GroupFormationTool.Survey;

public interface IResponse
{
    public long getQuestionId();

    public void setQuestionId(long questionId);

    public String getBannerId();

    public void setBannerId(String bannerId);

    public long getCourseId();

    public void setCourseId(long courseId);

    public String[] getResponseList();

    public void setResponseList(String[] responseList);
}

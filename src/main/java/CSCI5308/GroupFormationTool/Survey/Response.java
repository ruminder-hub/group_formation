package CSCI5308.GroupFormationTool.Survey;

public class Response implements IResponse
{
    private long questionId;
    private String bannerId;
    private long courseId;
    private String[] responseList;

    public Response()
    {
    }

    public long getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(long questionId)
    {
        this.questionId = questionId;
    }

    public String getBannerId()
    {
        return bannerId;
    }

    public void setBannerId(String bannerId)
    {
        this.bannerId = bannerId;
    }

    public long getCourseId()
    {
        return courseId;
    }

    public void setCourseId(long courseId)
    {
        this.courseId = courseId;
    }

    public String[] getResponseList()
    {
        return responseList;
    }

    public void setResponseList(String[] responseList)
    {
        this.responseList = responseList;
    }
}

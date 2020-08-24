package CSCI5308.GroupFormationTool.Survey;

public interface IGroupCreationResponse
{
    public void setDefaults();

    public long getId();

    public void setId(long id);

    public int getResponse();

    public void setResponse(int response);

    public int getxValue();

    public void setxValue(int xValue);

    public boolean isIncludeMinOneWithValueGreaterThanX();

    public void setIncludeMinOneWithValueGreaterThanX(boolean includeMinOneWithValueGreaterThanX);

    public boolean isIncludeMinOneWithValueLessThanX();

    public void setIncludeMinOneWithValueLessThanX(boolean includeMinOneWithValueLessThanX);
}

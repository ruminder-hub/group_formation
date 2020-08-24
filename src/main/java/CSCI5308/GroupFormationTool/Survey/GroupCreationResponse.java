package CSCI5308.GroupFormationTool.Survey;

public class GroupCreationResponse implements IGroupCreationResponse
{
	private long id;
	private int response;
	private int xValue;
	private boolean includeMinOneWithValueGreaterThanX;
	private boolean includeMinOneWithValueLessThanX;
	
	public GroupCreationResponse()
	{
		setDefaults();
	}
	
	public void setDefaults()
	{
		id = -1;
		response = -1;
		xValue = -1;
		includeMinOneWithValueGreaterThanX = false;
		includeMinOneWithValueLessThanX = false;
	}
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public int getResponse()
	{
		return response;
	}
	
	public void setResponse(int response)
	{
		this.response = response;
	}
	
	public int getxValue()
	{
		return xValue;
	}
	
	public void setxValue(int xValue)
	{
		this.xValue = xValue;
	}
	
	public boolean isIncludeMinOneWithValueGreaterThanX()
	{
		return includeMinOneWithValueGreaterThanX;
	}
	
	public void setIncludeMinOneWithValueGreaterThanX(boolean includeMinOneWithValueGreaterThanX)
	{
		this.includeMinOneWithValueGreaterThanX = includeMinOneWithValueGreaterThanX;
	}
	
	public boolean isIncludeMinOneWithValueLessThanX()
	{
		return includeMinOneWithValueLessThanX;
	}
	
	public void setIncludeMinOneWithValueLessThanX(boolean includeMinOneWithValueLessThanX)
	{
		this.includeMinOneWithValueLessThanX = includeMinOneWithValueLessThanX;
	}

}

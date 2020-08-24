package CSCI5308.GroupFormationTool.Question;

public class Option implements IOption
{
	private String text;
	private String value;
	
	public Option()
	{
		setDefaults();
	}
	
	public void setDefaults()
	{
		text = "";
		value = "";
	}
	
	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}

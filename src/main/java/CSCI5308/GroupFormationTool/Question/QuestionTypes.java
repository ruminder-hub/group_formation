package CSCI5308.GroupFormationTool.Question;

public enum QuestionTypes 
{
	NUMERIC("Numeric"),
	MULTIPLE_CHOICE_CHOOSE_ONE("Multiple Choice Choose 1"),
	MULTIPLE_CHOICE_CHOOSE_MANY("Multiple Choice Choose Many"),
	FREE_TEXT("Free Text");
	
	private String value;
	
	public String getValue()
	{
		return this.value;
	}
	
	private QuestionTypes(String value)
	{
		this.value = value;
	}
}

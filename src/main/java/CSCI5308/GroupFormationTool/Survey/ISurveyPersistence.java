package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Question.IOption;
import CSCI5308.GroupFormationTool.Question.IQuestion;

import java.util.List;

public interface ISurveyPersistence
{
    public List<IQuestion> getAlreadyAddedQuestions(Long courseId);

    public List<IQuestion> getNotAddedQuestions(Long courseId, String bannerId);

    public boolean addQuestionToSurvey(Long questionId, Long courseId);

    public boolean deleteQuestionFromSurvey(Long questionId, Long courseId);

    public boolean publishSurvey(Long courseId);

    public boolean disableSurvey(Long courseId);

    public boolean isSurveyPublished(Long courseId);

    public List<IQuestion> getSurveyQuestions(Long courseId);

    public List<IOption> getSurveyQuestionOptions(Long questionId);

    public boolean storeResponses(IResponse response, int index);
    
    public List<Long> getSurveyQuestionsForCourse(Long courseId);
    
    public IQuestion getSurveyQuestion(Long questionId);
    
    public List<String> getStudentBannersWhoFilledSurvey(long courseId);
    
    public IResponse getStudentResponseCorrespondingToQuestion(long qId, long courseId, String bannerId);
}

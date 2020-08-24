package CSCI5308.GroupFormationTool.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ViewQuestionsController
{
    private static Logger log = LoggerFactory.getLogger(ViewQuestionsController.class); 
    private IQuestionPersistence questionPersistence = QuestionAbstractFactory.instance().getQuestionDB();

    @RequestMapping("/viewQuestions")
    public String viewQuestions
    (
        Model model,
        @RequestParam(value = "id") String instructorId,
        @RequestParam(value = "order", required = false) String order
    )
    {
    	log.debug("Entered ViewQuestionController.vuewQuestion with instructorId: " + instructorId + " and order questions by : " + order);
        List<List<String>> questionList;
        try
        {
            questionList = questionPersistence.getQuestionsByInstructorID(instructorId, order);
            model.addAttribute("questionList", questionList);
        }
        catch (Exception ex)
        {
            return "error";
        }
        return "question/viewQuestions";
    }

    @RequestMapping("/viewQuestionsSorted")
    public String viewQuestionsSorted(Model model, @RequestParam("order") String order)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "redirect:/viewQuestions?id=" + authentication.getName() + "&order=" + order;
    }

    @RequestMapping("/deleteQuestion")
    public String deleteQuestion(Model model, @RequestParam("questionId") String questionId)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        log.debug("Entered ViewQuestionController.deleteQuestion with questionId: " + questionId + " userId: " + id);
        try
        {
            questionPersistence.removeQuestionFromDatabase(questionId);
        }
        catch (Exception ex)
        {
            return "error";
        }
        return "redirect:/viewQuestions?id=" + id + "&order";
    }
}

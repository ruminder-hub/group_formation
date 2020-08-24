package CSCI5308.GroupFormationTool.Question;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddQuestionController {
	private static final String VIEW_QUES_PAGE = "viewQuestions";
	private static final String CREATE_QUES_PAGE = "question/createQuestion";
	private static final String MULTIPLE_CHOICE__PAGE = "question/multipleChoiceQuestion";
	private static final String SURVEY_VIEW_NUMERIC_QUES = "question/surveyViewOfNumericQuestion";
	private static final String SURVEY_VIEW_MULTIPLE_CHOICE_CHOOSE_ONE = "question/surveyViewOfMultipleChoiceChooseOne";
	private static final String SURVEY_VIEW_MULTIPLE_CHOICE_CHOOSE_MANY = "question/surveyViewOfMultipleChoiceChooseMany";
	private static final String SURVEY_VIEW_FREE_TEXT = "question/surveryViewOfFreeText";

	private static final Logger log = LoggerFactory.getLogger(AddQuestionController.class);

	@GetMapping("/question/create")
	public String addQuestion(Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        log.debug("Entered AddQuestionController.addQuestion creating question with id " + id);
		QuestionTypes []questionTypes = QuestionTypes.values();
		model.addAttribute("questionTypes", questionTypes);
		model.addAttribute("instructorId", id);
		return CREATE_QUES_PAGE;	
	}
	
	@GetMapping("/question/type")
	public String setQuestionType
	(
		@RequestParam String title,
		@RequestParam String question,
		@RequestParam String type, Model model
	)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        log.debug("Entered AddQuestionController.setQuestionType with title: " + title + " question: " + question + " type: " + type + " with id: " + id);
		model.addAttribute("title", title);
		model.addAttribute("question", question);
		model.addAttribute("type", type);
		model.addAttribute("instructorId", id);
		
		if (type.equals(QuestionTypes.MULTIPLE_CHOICE_CHOOSE_ONE.name()) || type.equals(QuestionTypes.MULTIPLE_CHOICE_CHOOSE_MANY.name())) 
		{
			return MULTIPLE_CHOICE__PAGE;
		}
		else if (type.equals(QuestionTypes.NUMERIC.name()))
		{
			return SURVEY_VIEW_NUMERIC_QUES;
		}
		else if (type.equals(QuestionTypes.FREE_TEXT.name()))
		{
			 return SURVEY_VIEW_FREE_TEXT;
		}
		else
		{
			return "NO_SUCH_QUESTION_TYPE";
		}
	}
	
	@PostMapping("/question/submit")
	public String submitQuestion
	(
		@RequestParam String title,
		@RequestParam String question,
		@RequestParam String type,
		@RequestParam(required = false) List<String> option,
		@RequestParam(required = false) List<String> value,
		Model model
	)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        log.debug("Entered AddQuestionController.submitQuestion with title: " + title + " question: " + question + " type: " + type + " with id: " + id);
		model.addAttribute("title", title);
		model.addAttribute("question", question);
		model.addAttribute("type", type);
		model.addAttribute("answerOptions", option);
		model.addAttribute("valueOptions", value);
		model.addAttribute("instructorId", id);
		log.info("Exiting submitQuestion");
		
		if (type.equals(QuestionTypes.MULTIPLE_CHOICE_CHOOSE_ONE.name()))
		{
			return SURVEY_VIEW_MULTIPLE_CHOICE_CHOOSE_ONE;
		}
		else
		{
			return SURVEY_VIEW_MULTIPLE_CHOICE_CHOOSE_MANY;
		}
	}
	
	@PostMapping("/question/save")
	public String saveQuestion
	(
		@RequestParam String title,
		@RequestParam String question,
		@RequestParam String type,
		@RequestParam(required = false) List<String> option,
		@RequestParam(required = false) List<String> value
	)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        log.debug("Entered AddQuestionController.saveQuestion with title: " + title + " question: " + question + " type: " + type + " with id: " + id);
		IQuestion questionObject = QuestionAbstractFactory.instance().getQuestion();
		
		questionObject.setTitle(title);
		questionObject.setQuestion(question);
		questionObject.setType(type);
		
		List<IOption> optionList = null;
		if (CollectionUtils.isEmpty(option)==false)
		{
			optionList = new ArrayList<IOption>();
			int index = 0;
			for(String optionStr : option)
			{
				IOption optionObject = QuestionAbstractFactory.instance().getOption();
				optionObject.setText(optionStr);
				optionObject.setValue(value.get(index));
				optionList.add(optionObject);
				index++;
			}
		}
		questionObject.setAnswerOptions(optionList);
		IQuestionPersistence questionPersistence = QuestionAbstractFactory.instance().getQuestionDB();
		questionObject.saveQuestion(questionPersistence, id);
        
		return "redirect:/" + VIEW_QUES_PAGE + "?id=" + id + "&order";
	}
}

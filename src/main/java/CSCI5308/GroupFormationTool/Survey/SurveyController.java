package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.QuestionTypes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SurveyController
{
	private Logger log = LoggerFactory.getLogger(SurveyController.class);
	
    private static final String QUESTIONID = "questionId";
    private static final String COURSEID = "courseId";
    private static final String BANNER = "bannerId";
    private static final String COUNT = "count";
    private static final String RESPONSELIST = "responseList";
    private static final String GROUP_CREATION_VIEW = "creategroups";
    private static final String RESPONSE = "response";
    private static final String DISPLAY_GROUPS = "displaygroups";

    @GetMapping("/survey/create")
    public ModelAndView createSurvey(@RequestParam(name = COURSEID) long courseId,
                                     @RequestParam(name = BANNER) String bannerId)
    {
    	log.info("Received request at createSurvey with courseId: " + courseId + " and bannerId: " + bannerId);
        ISurveyPersistence surveyDB = SurveyAbstractFactory.instance().getSurveyDB();
        ModelAndView modelAndView = new ModelAndView("createsurvey");
        modelAndView.addObject("courseId",courseId);
        List<IQuestion> alreadyAddedQuestionList = surveyDB.getAlreadyAddedQuestions(courseId);
        List<IQuestion> notAddedQuestionList = surveyDB.getNotAddedQuestions(courseId,bannerId);
        modelAndView.addObject("alreadyAddedQuestions", alreadyAddedQuestionList);
        modelAndView.addObject("notAddedQuestions", notAddedQuestionList);

        if (surveyDB.isSurveyPublished(courseId) == false)
        {
            modelAndView.addObject("surveynotpublished", true);
        }
        else
        {
            modelAndView.addObject("surveypublished", true);
        }

        return modelAndView;
    }

    @PostMapping("/survey/addquestion")
    public ModelAndView addQuestion(@RequestParam(name = COURSEID) long courseId,
                                    @RequestParam(name = QUESTIONID) long questionId,
                                    @RequestParam(name = BANNER) String bannerId)
    {
    	log.info("Received request at addQuestion with courseId: " + courseId + " , bannerId: " + bannerId + " questionId: " + questionId);
        ISurveyPersistence surveyDB = SurveyAbstractFactory.instance().getSurveyDB();
        ModelAndView modelAndView = new ModelAndView("redirect:/survey/create?"+COURSEID+"="+courseId+"&"+BANNER+"="+bannerId);
        surveyDB.addQuestionToSurvey(questionId, courseId);
        return modelAndView;
    }

    @PostMapping("/survey/publish")
    public ModelAndView publishSurvey(@RequestParam(name = COURSEID) long courseId)
    {
    	log.info("Received request at publish survey with courseId: " + courseId);
        ISurveyPersistence surveyDB = SurveyAbstractFactory.instance().getSurveyDB();
        surveyDB.publishSurvey(courseId);
        ModelAndView modelAndView = new ModelAndView("redirect:/course/course?id=" + courseId);
        return modelAndView;
    }

    @PostMapping("/survey/disablesurvey")
    public ModelAndView disableSurvey(@RequestParam(name = COURSEID) long courseId)
    {
    	log.info("Received request at disable survey with courseId: " + courseId);
        ISurveyPersistence surveyDB = SurveyAbstractFactory.instance().getSurveyDB();
        surveyDB.disableSurvey(courseId);
        ModelAndView modelAndView = new ModelAndView("redirect:/course/course?id=" + courseId);
        return modelAndView;
    }

    @PostMapping("/survey/deletequestion")
    public ModelAndView deleteQuestion(@RequestParam(name = COURSEID) long courseId,
                                       @RequestParam(name = QUESTIONID) long questionId,
                                       @RequestParam(name = BANNER) String bannerId)
    {
    	log.info("Received request at deleteQuestion with courseId: " + courseId + " , bannerId: " + bannerId + " questionId: " + questionId);
        ISurveyPersistence surveyDB = SurveyAbstractFactory.instance().getSurveyDB();
        ModelAndView modelAndView = new ModelAndView("redirect:/survey/create?"+COURSEID+"="+courseId+"&"+BANNER+"="+bannerId);
        surveyDB.deleteQuestionFromSurvey(questionId, courseId);
        return modelAndView;
    }

    @GetMapping("/survey/takeSurvey")
    public ModelAndView takeSurvey(Model model,
                                   @RequestParam(name = COURSEID) long courseId)
    {
        ISurveyPersistence surveyDB = SurveyAbstractFactory.instance().getSurveyDB();
        ModelAndView modelAndView = new ModelAndView("takesurvey");
        List<IQuestion> questionList = surveyDB.getSurveyQuestions(courseId);
        for (IQuestion question : questionList)
        {
            question.setAnswerOptions(surveyDB.getSurveyQuestionOptions(question.getId()));
        }
        modelAndView.addObject("response", new Response());
        modelAndView.addObject("courseId", courseId);
        modelAndView.addObject("questionList", questionList);
        modelAndView.addObject("listSize", questionList.size() - 1);
        return modelAndView;
    }

    @GetMapping("/survey/submitSurvey")
    public String submitSurvey(@ModelAttribute(name = RESPONSE) Response response,
                               @RequestParam(name = COURSEID) long courseId, Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ISurveyPersistence surveyDB = SurveyAbstractFactory.instance().getSurveyDB();
        List<IQuestion> questionList = surveyDB.getSurveyQuestions(courseId);
        boolean surveySaved = false;
        for (int i = 0; i < response.getResponseList().length; i++)
        {
            response.setQuestionId(questionList.get(i).getId());
            response.setBannerId(authentication.getName());
            response.setCourseId(courseId);
            surveySaved = surveyDB.storeResponses(response, i);
            if (surveySaved == false)
            {
                model.addAttribute("error", "Failed to save survey. Kindly try again");
                model.addAttribute("linkMessage", "Take survey again");
                model.addAttribute("url", "/survey/takeSurvey?id="+ courseId);
                return "errorpage";
            }
        }
        return "redirect:/course/course?id="+ courseId;
    }
    
    @GetMapping("/survey/creategroups")
    public ModelAndView createGroups(@RequestParam(name = COURSEID) long courseId,
            @RequestParam(name = BANNER) String bannerId) 
    {
    	log.info("Received request at createGroups with courseId: " + courseId + " , bannerId: " + bannerId );
    	ModelAndView modelAndView = new ModelAndView(GROUP_CREATION_VIEW);
    	
    	ISurveyPersistence surveyDB = SurveyAbstractFactory.instance().getSurveyDB();
		List<IQuestion> groupFormationQuestions = new LinkedList<IQuestion>();
		ISurveyQuestions surveyQuestions = SurveyAbstractFactory.instance().getSurveyQuestions();
		groupFormationQuestions = surveyQuestions.fetchSurveyQuestions(surveyDB, courseId);
		long i = 0;
		if (groupFormationQuestions != null && groupFormationQuestions.size() > 0)
		{
			for (IQuestion question : groupFormationQuestions)
			{
				question.setId(i++);
			}
		}
		else
        {
			log.info("Question list is null");
		}

    	modelAndView.addObject("questionCount", groupFormationQuestions.size());
    	modelAndView.addObject("questionList", groupFormationQuestions);
    	modelAndView.addObject("courseId", courseId);
    	modelAndView.addObject("bannerId", bannerId);
    	return modelAndView;
    }
    
    @PostMapping("/survey/creategroupalgo")
    public ModelAndView createGroupsFromAlgo(
    		@RequestParam long courseId,
            @RequestParam String bannerId,
    		@RequestParam(required = true) Integer groupSize,
    		@RequestParam(required = true) Integer questionCount,
    		HttpServletRequest request,
    		@RequestParam(required = false) List<String> numericValues)
    {
    	log.info("Received request at createGroupsFromAlgo with courseId: " + courseId + " , bannerId: " + bannerId + " groupSize: " + groupSize + " questions count: " + questionCount);
    	if (groupSize < 2)
    	{
    		log.info("Group size cannot be less than 2");
    		return new ModelAndView("redirect:/survey/creategroups?"+COURSEID+"="+courseId+"&"+BANNER+"="+bannerId);
    	}
    	List<IGroupCreationResponse> surveyQuestionResponseList = new LinkedList<>();
    	ISurveyPersistence surveyDB = SurveyAbstractFactory.instance().getSurveyDB();
		List<IQuestion> groupFormationQuestions = new LinkedList<IQuestion>();
		ModelAndView modelAndView = new ModelAndView(DISPLAY_GROUPS);
		
		ISurveyQuestions surveyQuestion = SurveyAbstractFactory.instance().getSurveyQuestions();
		groupFormationQuestions = surveyQuestion.fetchSurveyQuestions(surveyDB, courseId);
    	for (int i = 0; i < questionCount; i++)
    	{
    		IGroupCreationResponse response = SurveyAbstractFactory.instance().getGroupCreationResponse();
    		response.setId(groupFormationQuestions.get(i).getId());
    		response.setResponse(Integer.parseInt(request.getParameter("" + i).toString()));
    		if (groupFormationQuestions.get(i).getType().equals(QuestionTypes.NUMERIC.name()))
    		{
    			String xValueStr = "" + i + "xValue";
    			String option1 = "" + i + "option1";
    			String option2 = "" + i + "option2";
    			if (request.getParameter(xValueStr) != null && (request.getParameter(option1) != null || request.getParameter(option2) != null))
    			{
    				log.info(request.getParameter(xValueStr).toString());
    				response.setxValue(Integer.parseInt(request.getParameter(xValueStr).toString()));
    				if (request.getParameter(option1) != null)
    				{
    					response.setIncludeMinOneWithValueGreaterThanX(Boolean.TRUE);
    				}
    				if (request.getParameter(option2) != null)
    				{
    					response.setIncludeMinOneWithValueLessThanX(Boolean.TRUE);
    				}
    			}
    		}
    		surveyQuestionResponseList.add(response);
    	}
    	
    	IGroup formGroups = SurveyAbstractFactory.instance().getGroup();
        List<IGroup> allGroups = new ArrayList<>();
    	allGroups = formGroups.createGroups(surveyQuestionResponseList, courseId, groupSize, surveyDB);
    	modelAndView.addObject("Group",allGroups);
    	log.info("Groups created");
    	
    	return modelAndView;
    }
}
package CSCI5308.GroupFormationTool.SurveyTest;

import CSCI5308.GroupFormationTool.Survey.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@SuppressWarnings("deprecation")
public class ResponseTest {

    @Test
    public void ConstructorTests()
    {
        Response response = new Response();
        Assert.isTrue(response.getQuestionId() == 0);
        Assert.isTrue(response.getBannerId() == null);
        Assert.isTrue(response.getCourseId() == 0);
        Assert.isTrue(response.getResponseList() == null);
    }

    @Test
    public void getQuestionIdTest() {
        Response response = new Response();
        response.setQuestionId(10);
        Assert.isTrue(10 == response.getQuestionId());
    }

    @Test
    public void setQuestionIdTest() {
        Response response = new Response();
        response.setQuestionId(10);
        Assert.isTrue(10 == response.getQuestionId());
    }

    @Test
    public void getBannerIdTest() {
        Response response = new Response();
        response.setBannerId("B-555555");
        Assert.isTrue("B-555555" == response.getBannerId());
    }

    @Test
    public void setBannerIdTest() {
        Response response = new Response();
        response.setBannerId("B-555555");
        Assert.isTrue("B-555555" == response.getBannerId());
    }

    @Test
    public void getCourseIdTest() {
        Response response = new Response();
        response.setCourseId(10);
        Assert.isTrue(10 == response.getCourseId());
    }

    @Test
    public void setCourseIdTest() {
        Response response = new Response();
        response.setCourseId(10);
        Assert.isTrue(10 == response.getCourseId());
    }

    @Test
    public void getResponseListTest() {
        Response response = new Response();
        String[] testArray = new String[2];
        testArray[0] = "abc";
        testArray[1] = "pqr";
        response.setResponseList( new String[] {"abc","pqr"});
        Assert.isTrue(testArray.length == response.getResponseList().length);
        Assert.isTrue(testArray[0] == response.getResponseList()[0]);
        Assert.isTrue(testArray[1] == response.getResponseList()[1]);
    }

    @Test
    public void setResponseListTest() {
        Response response = new Response();
        String[] testArray = new String[2];
        testArray[0] = "abc";
        testArray[1] = "pqr";
        response.setResponseList( new String[] {"abc","pqr"});
        Assert.isTrue(testArray.length == response.getResponseList().length);
        Assert.isTrue(testArray[0] == response.getResponseList()[0]);
        Assert.isTrue(testArray[1] == response.getResponseList()[1]);
    }
}

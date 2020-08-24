package CSCI5308.GroupFormationTool.CoursesTest;

import CSCI5308.GroupFormationTool.Courses.ICourse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;

@SpringBootTest
@SuppressWarnings("deprecation")
class CourseTest 
{
	@Test
	public void ConstructorTests() 
	{
		ICourse course = CoursesAbstractFactoryMock.instance().getCourse();
		Assert.isTrue(course.getId() == -1);
		Assert.isTrue(course.getTitle().isEmpty());

		ICoursePersistence courseDB = CoursesAbstractFactoryMock.instance().getCourseDBMock();
		course = new Course(0, courseDB);
		Assert.isTrue(course.getId() == 0);
		Assert.isTrue(course.getTitle().equals("Software Engineering"));
	}

	@Test
	public void setIdTest() 
	{
		ICourse course = CoursesAbstractFactoryMock.instance().getCourse();
		course.setId(7);
		Assert.isTrue(course.getId() == 7);
	}

	@Test
	public void getIdTest() 
	{
		ICourse course = CoursesAbstractFactoryMock.instance().getCourse();
		course.setId(7);
		Assert.isTrue(course.getId() == 7);
	}

	@Test
	public void setTitleTest() 
	{
		ICourse course = CoursesAbstractFactoryMock.instance().getCourse();
		course.setTitle("Advanced Topics in Software Development");
		Assert.isTrue(course.getTitle().equals("Advanced Topics in Software Development"));
	}

	@Test
	public void getTitleTest() 
	{
		ICourse course = CoursesAbstractFactoryMock.instance().getCourse();
		course.setTitle("Advanced Topics in Software Development");
		Assert.isTrue(course.getTitle().equals("Advanced Topics in Software Development"));
	}

	@Test
	public void deleteCourseTest() 
	{
		ICoursePersistence courseDB = CoursesAbstractFactoryMock.instance().getCourseDBMock();
		boolean status = courseDB.deleteCourse(0);
		Assert.isTrue(status);
	}

	@Test
	public void createCourseTest() 
	{
		ICoursePersistence courseDB = CoursesAbstractFactoryMock.instance().getCourseDBMock();
		ICourse course = CoursesAbstractFactoryMock.instance().getCourse();
		course.setId(0);
		course.setTitle("Software Engineering");
		courseDB.createCourse(course);
		Assert.isTrue(course.getId() == 0);
		Assert.isTrue(course.getTitle().equals("Software Engineering"));
	}
}

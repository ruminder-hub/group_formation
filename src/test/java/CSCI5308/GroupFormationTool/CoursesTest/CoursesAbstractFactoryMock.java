package CSCI5308.GroupFormationTool.CoursesTest;

import CSCI5308.GroupFormationTool.Courses.*;

public class CoursesAbstractFactoryMock
{
	private static CoursesAbstractFactoryMock uniqueInstance = null;
	private ICoursePersistence courseDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private ICourseUserRelationship courseUserRelationship;


	private CoursesAbstractFactoryMock()
	{
		courseDB = new CourseDBMock();
		courseUserRelationshipDB = new CourseUserRelationshipDBMock();
		courseUserRelationship = new CourseUserRelationship();
	}

	public static CoursesAbstractFactoryMock instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new CoursesAbstractFactoryMock();
		}
		return uniqueInstance;
	}

	public ICoursePersistence getCourseDBMock()
	{
		return courseDB;
	}

	public ICourseUserRelationshipPersistence getCourseUserRelationshipDBMock()
	{
		return courseUserRelationshipDB;
	}

	public ICourseUserRelationship getCourseUserRelationshipMock()
	{
		return courseUserRelationship;
	}

	public ICourse getCourse()
	{
		return new Course();
	}
}

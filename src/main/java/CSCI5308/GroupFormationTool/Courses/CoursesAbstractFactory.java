package CSCI5308.GroupFormationTool.Courses;

import org.springframework.web.multipart.MultipartFile;

public class CoursesAbstractFactory implements ICoursesAbstractFactory
{
	private static CoursesAbstractFactory uniqueInstance = null;
	private ICoursePersistence courseDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private ICourseUserRelationship courseUserRelationship;

	private CoursesAbstractFactory()
	{
		courseDB = new CourseDB();
		courseUserRelationshipDB = new CourseUserRelationshipDB();
		courseUserRelationship = new CourseUserRelationship();
	}

	public static CoursesAbstractFactory instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new CoursesAbstractFactory();
		}
		return uniqueInstance;
	}

	public ICoursePersistence getCourseDB()
	{
		return courseDB;
	}

	public ICourseUserRelationshipPersistence getCourseUserRelationshipDB()
	{
		return courseUserRelationshipDB;
	}

	public ICourseUserRelationship getCourseUserRelationship()
	{
		return courseUserRelationship;
	}

	public ICourse getCourse()
	{
		return new Course();
	}

	public IStudentCSVParser getCSVParser(MultipartFile file)
	{
		return new StudentCSVParser(file);
	}

	public IStudentCSVImport getCSVImport(IStudentCSVParser parser, ICourse course)
	{
		return new StudentCSVImport(parser, course);
	}
}

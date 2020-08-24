package CSCI5308.GroupFormationTool.Courses;

import org.springframework.web.multipart.MultipartFile;

public interface ICoursesAbstractFactory
{
    public ICoursePersistence getCourseDB();

    public ICourseUserRelationshipPersistence getCourseUserRelationshipDB();

    public ICourseUserRelationship getCourseUserRelationship();

    public ICourse getCourse();

    public IStudentCSVParser getCSVParser(MultipartFile file);

    public IStudentCSVImport getCSVImport(IStudentCSVParser parser, ICourse course);
}

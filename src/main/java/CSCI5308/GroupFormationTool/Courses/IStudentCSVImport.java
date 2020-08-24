package CSCI5308.GroupFormationTool.Courses;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import java.util.List;

public interface IStudentCSVImport
{
    public void enrollStudentFromRecord();

    public List<IUser> getNewStudents();

    public List<String> getSuccessResults();

    public List<String> getFailureResults();
}

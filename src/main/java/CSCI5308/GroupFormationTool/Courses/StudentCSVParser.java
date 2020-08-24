package CSCI5308.GroupFormationTool.Courses;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.AccessControl.AccessControlAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import CSCI5308.GroupFormationTool.AccessControl.User;

public class StudentCSVParser implements IStudentCSVParser
{
	private MultipartFile uploadedFile;
	private Logger log =  LoggerFactory.getLogger(StudentCSVParser.class);
	private List<IUser> studentList = new ArrayList<>();

	public StudentCSVParser(MultipartFile file) 
	{
		this.uploadedFile = file;
	}
	
	@Override
	public List<IUser> parseCSVFile(List<String> failureResults)
	{
		log.debug("Parsing CSV file");
		try
		{
			Reader reader = new InputStreamReader(uploadedFile.getInputStream());
			CSVReader csvReader = new CSVReaderBuilder(reader).build();
			List<String[]> records = csvReader.readAll();
			Iterator<String[]> iter = records.iterator();
			IUser user;
			while (iter.hasNext())
			{
				String[] record = iter.next();
				String bannerID = record[0];
				String firstName = record[1];
				String lastName = record[2];
				String email = record[3];
				user = AccessControlAbstractFactory.instance().getUser();
				user.setBannerID(bannerID);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				studentList.add(user);
			}
		}
		catch (IOException e)
		{
			log.error("Failed reading upload file due to " + e.getMessage());
			failureResults.add("Failure reading uploaded file: " + e.getMessage());
		}
		catch (Exception e)
		{
			log.error("Failed to parse csv file due to " + e.getMessage());
			failureResults.add("Failure parsing CSV file: " + e.getMessage());
		}
		return studentList;
	}
}

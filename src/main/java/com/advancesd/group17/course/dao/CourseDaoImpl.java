package com.advancesd.group17.course.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.database.DatabaseConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CourseDaoImpl implements CourseDao {

	public static Logger log = LoggerFactory.getLogger(CourseDaoImpl.class);

	@Override
	public List<Course> getcoursesbybannerid(String bannerid) {

		List<Course> crs = new ArrayList<>();

		try (Connection conn = createDbConnection();
				CallableStatement st = conn.prepareCall("{CALL getcoursesbybannerid(?)}");) {
			st.setString(1, bannerid);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Course c = new Course();
				c.setCourseName(rs.getString("course_name"));
				crs.add(c);
			}

			st.close();

			return crs;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return crs;
	}

	@Override
	public List<Course> getAllCourses() {
		log.info("Entered CourseDaoImpl.getAllCourses");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Connection connection = null;
		List<Course> courseList = new ArrayList<Course>();
		try {
			connection = createDbConnection();
			CallableStatement stmt = connection.prepareCall("{CALL getallcourses}");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.info("Result set of all courses" + rs.getString("course_name"));
				Course course = new Course(rs.getInt("course_id"), rs.getString("course_name"));
				courseList.add(course);
			}
			connection.close();
			if (CollectionUtils.isEmpty(courseList)) {
				return null;
			}
			return courseList;

		} catch (Exception e) {
			log.error("Error occured " + e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Course addNewCourse(Course course) {
		Connection connection = null;

		try {
			connection = createDbConnection();
			if (connection == null) {
				return null;
			}
			CallableStatement stmt = connection.prepareCall("{CALL createcourse(?, ?, ?)}");
			stmt.setString(1, course.getCourseName());
			stmt.setString(2, course.getCourseDesc());
			stmt.setInt(3, course.getCourseCredits());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Course addedCourse = new Course();
				addedCourse.setCourseId(rs.getInt("course_id"));
				addedCourse.setCourseName(rs.getString("course_name"));
				addedCourse.setCourseCredits(rs.getInt("course_credits"));
				addedCourse.setCourseDesc(rs.getString("course_desc"));
				return addedCourse;
			}
			connection.close();
			return null;

		} catch (Exception e) {
			log.error("Error occured: " + e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean deleteCourse(Integer courseId) {
		Connection connection = null;

		try {

			connection = createDbConnection();
			if (connection == null) {
				return Boolean.FALSE;
			}
			CallableStatement stmt = connection.prepareCall("{CALL deletecoursebyid(?)}");
			stmt.setInt(1, courseId);

			ResultSet resultSet = stmt.executeQuery();
			connection.close();
			if (resultSet.next()) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}

		} catch (Exception e) {
			log.error("Error occured: " + e);
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}

	Connection createDbConnection() {
		Connection connection = null;
		try {
			connection = DatabaseConfig.getInstance().getConnection();
			if (connection == null) {
				log.info("Connection null");
			} else {
				log.info("Connection established");
			}
		} catch (Exception e) {
			log.error("Error occured: " + e);
			e.printStackTrace();
		}
		return connection;

	}

	@Override
	public Course getCourseDetails(Integer courseId) {
		log.info("Entered CourseDaoImpl.getCourseDetails with courseId: " + courseId);
		try {
			Connection connection = createDbConnection();
			if (connection == null) {
				return null;
			}
			Course course = null;
			CallableStatement stmt = connection.prepareCall("{CALL getcoursedetailbyid(?)}");
			stmt.setInt(1, courseId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				log.info("Course details found");
				course = new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				course.setCourseCredits(rs.getInt("course_credits"));
				course.setCourseDesc(rs.getString("course_desc"));
			} else {
				log.info("Course details are null");
			}
			connection.close();
			return course;

		} catch (Exception e) {

		}
		return null;
	}

}

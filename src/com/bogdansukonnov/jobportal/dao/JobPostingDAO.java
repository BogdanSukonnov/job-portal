package com.bogdansukonnov.jobportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bogdansukonnov.jobportal.entity.JobPosting;

public class JobPostingDAO {
	
	private Connection conn;
	
	public JobPostingDAO() {
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		final String Database_URL = "jdbc:mysql://localhost/jobPortal?useTimezone=true&serverTimezone=UTC";
		final String Database_User = "root";
		final String Database_Password = "RootMySql7!";
		
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(Database_URL, Database_User, Database_Password);			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public JobPosting getJobPostingById(int id) {
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from jobPostings where id = " + id);
			if (rs.next()) {
				String messageBody = rs.getString("messageBody");
				String jobName = rs.getString("jobName");
				String posterName = rs.getString("posterName");
				String contactPhone = rs.getString("contactPhone");
				String jobPostingPassword = rs.getString("jobPostingPassword");
				JobPosting jobPost = new JobPosting(id, messageBody, jobName, posterName, contactPhone, jobPostingPassword);
				return jobPost;
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	public void insertJobPosting(JobPosting posting) {
		String sep = "','";
		try {
			Statement st = conn.createStatement();
			String sqlQuery = "insert into jobPostings(messageBody, jobName, posterName, contactPhone, jobPostingPassword) values('"
					+ posting.getMessageBody() + sep + posting.getJobName() + sep + posting.getPosterName()
					+ sep + posting.getContactPhone() + sep + posting.getJobPostingPassword() + "')";
			System.out.println(sqlQuery);
			st.executeUpdate(sqlQuery);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
	
	public void removeJobPosting(int jobPostId) {
		try {
			Statement st = conn.createStatement();
			st.executeUpdate("delete from jobPostings where id = " + jobPostId);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public List<JobPosting> getAllJobPostings() {
		List<JobPosting> jobs = new ArrayList<>();		
		try {
			Statement st = conn.createStatement();
			String sqlQuery = "select * from jobPostings limit 200";
			ResultSet rs = st.executeQuery(sqlQuery);
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String messageBody = rs.getString("messageBody");
				String jobName = rs.getString("jobName");
				String posterName = rs.getString("posterName");
				String contactPhone = rs.getString("contactPhone");				
				JobPosting jobPosting = new JobPosting(id, messageBody, jobName, posterName, contactPhone, "");
				jobs.add(jobPosting);				
			}
			rs.close();
			st.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jobs;
	}
	
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

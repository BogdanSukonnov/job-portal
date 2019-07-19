package com.bogdansukonnov.jobportal.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bogdansukonnov.jobportal.dao.JobPostingDAO;
import com.bogdansukonnov.jobportal.entity.JobPosting;

@WebServlet("/PostJob")
public class PostJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostJobServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/postJob.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String messageBody = request.getParameter("messageBody");
		String contactPhone = request.getParameter("phone");
		String jobName = request.getParameter("jobname");
		String posterName = request.getParameter("postername");
		String jobPostingPassword = request.getParameter("password");
		
		JobPosting jobPost = new JobPosting(0, messageBody, jobName, posterName, contactPhone, jobPostingPassword);
		
		JobPostingDAO dao = new JobPostingDAO();
		
		dao.insertJobPosting(jobPost);
		
		RequestDispatcher rd = request.getRequestDispatcher("/postJobSuccess.jsp");
		rd.forward(request, response);		
	}

}

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

@WebServlet("/RemoveJob")
public class RemoveJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveJobServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/removeJob.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JobPostingDAO dao = new JobPostingDAO();
		int jobPosId = Integer.parseInt(request.getParameter("id"));
		JobPosting job = dao.getJobPostingById(jobPosId);
		RequestDispatcher rd;
		
		if (job == null) {
			request.setAttribute("error", true);
			rd = request.getRequestDispatcher("/removeJob.jsp");
		} else if (job.getJobPostingPassword().equals(request.getParameter("password"))) {
			dao.removeJobPosting(jobPosId);
			rd = request.getRequestDispatcher("/removeJobSuccess.jsp");			
		} else {
			request.setAttribute("error", true);
			rd = request.getRequestDispatcher("/removeJob.jsp");			
		}
		rd.forward(request, response);
	}

}

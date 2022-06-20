package com.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.dao.DAO;

public class EmployeeDeleteAction extends HttpServlet {
	private final Logger logger = Logger.getLogger(EmployeeDeleteAction.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		BasicConfigurator.configure();
		logger.debug("---EmployeeDeleteAction---");
		String employeeId = request.getParameter("employeeId");
		logger.debug("---employeeId = "+employeeId);
		DAO daoDelete = new DAO();
		try {
			daoDelete.deleteEmployee(employeeId);
			RequestDispatcher dispatch = request.getRequestDispatcher("jsp/employeeSearch.jsp");
			dispatch.forward(request, response);
		} catch (Exception e) {
			logger.debug("--EmployeeDeleteAction exception--");
			e.printStackTrace();
		}
		
		
	}

}

package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.dao.DAO;
import com.dto.EmployeeDTO;

public class EmployeeSearchAction extends HttpServlet {
	private final Logger logger = Logger.getLogger(EmployeeSearchAction.class);
       
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		BasicConfigurator.configure();
		logger.debug("---EmployeeSearchAction---");
		String employeeSearchName = request.getParameter("txtEmployeeSearch");
		DAO daoSearch = new DAO();
		ArrayList<EmployeeDTO> employeeBeanList = null;
		try {
			employeeBeanList = daoSearch.getEmployeeSearch(employeeSearchName);
			request.setAttribute("EMPLOYEEDETAILS", employeeBeanList);
			RequestDispatcher dispatch = request.getRequestDispatcher("jsp/employeeSearchResult.jsp");
			dispatch.forward(request, response);
		} catch (Exception e) {
			logger.debug("--EmployeeSearchAction exception--");
			e.printStackTrace();
		}
		
		
	}


}

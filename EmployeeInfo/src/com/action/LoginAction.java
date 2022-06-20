package com.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.dao.DAO;

/**
 * Servlet implementation class LoginAction
 */
public class LoginAction extends HttpServlet {
	private final Logger logger = Logger.getLogger(LoginAction.class);
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean userCredFlag = false;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("txtName");
		String userPass = request.getParameter("txtPassword");
		
		// Set up a simple configuration that logs on the console.
	     BasicConfigurator.configure();
	
		logger.debug("Login username :: " +userName);
		logger.debug("Login password :: " +userPass);
		
		DAO dao = new DAO();
		userCredFlag = dao.getUserCredentialFlag(userName, userPass);
		logger.debug("userCredFlag :: " +userCredFlag);
		RequestDispatcher dispatch;
		if(userCredFlag){
			dispatch = request.getRequestDispatcher("jsp/employeeAdd.jsp");
			dispatch.forward(request, response);
		}else{
			dispatch = request.getRequestDispatcher("jsp/login.jsp");
			dispatch.forward(request, response);
		}
		
	}
	
	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}


}
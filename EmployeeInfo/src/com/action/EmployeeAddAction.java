package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dao.DAO;
import com.dto.EmployeeDTO;


public class EmployeeAddAction extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int employeeId = 0;
		String employeeStrId = null;
		String empName = "";
		String gender = "";
		String location = "";
		String dob = "";
		java.sql.Date dateOfBirth = null;
		empName = request.getParameter("txtEmpName");
		gender = request.getParameter("comboEmpGender");
		location = request.getParameter("txtEmpLocation");
		dob = request.getParameter("txtEmpDateOfBirth");
		System.out.println("--EmployeeAddAction : doPost()");
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
			java.util.Date dt = formatter.parse(dob);
			dateOfBirth = new java.sql.Date(dt.getTime());
				
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmpName(empName);
			employeeDTO.setGender(gender);
			employeeDTO.setLocation(location);
			employeeDTO.setDateOfBirth(dateOfBirth);
			
			DAO daoAdd = new DAO();
			
			employeeId = daoAdd.addEmployee(employeeDTO);
			System.out.println("--employeeId = "+employeeId);
			employeeStrId = Integer.toString(employeeId);
			request.setAttribute("EMPID", employeeStrId);
			RequestDispatcher dispatch = request.getRequestDispatcher("jsp/employeeAddConfirmation.jsp");
			dispatch.forward(request, response);
		}catch(Exception e){
			System.out.println("--Exception: EmployeeAddAction = ");
			e.printStackTrace();
		}
	}

}

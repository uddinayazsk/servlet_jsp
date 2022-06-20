package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DAO;
import com.dto.EmployeeDTO;

public class EmployeeUpdateAction extends HttpServlet {

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
		EmployeeDTO empDTO = null;
		employeeStrId = request.getParameter("empid");
		empName = request.getParameter("txtEmpName");
		gender = request.getParameter("comboEmpGender");
		location = request.getParameter("txtEmpLocation");
		dob = request.getParameter("txtEmpDateOfBirth");
		System.out.println("--EmployeeUpdateAction-- ");
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
			java.util.Date dt = formatter.parse(dob);
			dateOfBirth = new java.sql.Date(dt.getTime());
				
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmpId(Integer.parseInt(employeeStrId));
			employeeDTO.setEmpName(empName);
			employeeDTO.setGender(gender);
			employeeDTO.setLocation(location);
			employeeDTO.setDateOfBirth(dateOfBirth);
			
			DAO daoUpdate = new DAO();
			
			empDTO = daoUpdate.updateEmployee(employeeDTO);
			
			if(empDTO != null){
				request.setAttribute("EMPID", employeeStrId);
				request.setAttribute("EMPDETAIL", empDTO);
				request.setAttribute("UPSUCC", "recordupdatesuccess");
			}
			RequestDispatcher dispatch = request.getRequestDispatcher("jsp/employeeEdit.jsp");
			dispatch.forward(request, response);
		}catch(Exception e){
			System.out.println("--Exception: EmployeeUpdateAction ");
			e.printStackTrace();
		}
	}

}

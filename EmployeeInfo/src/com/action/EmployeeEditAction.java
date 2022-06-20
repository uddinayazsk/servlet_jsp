package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DAO;
import com.dto.EmployeeDTO;


public class EmployeeEditAction extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String emplIdStr = request.getParameter("employeeId");
			System.out.println("--EmployeeEditAction--");
			
			System.out.println("emp id = "+emplIdStr);
			EmployeeDTO employeeDTO;
			int emplId = 0;
			if(emplIdStr != null && emplIdStr.length() > 0){
				emplId = Integer.parseInt(emplIdStr);
			}
			DAO daoEmpDetail = new DAO();
			employeeDTO = daoEmpDetail.getEmployeeDetails(emplId);
			request.setAttribute("EMPDETAIL", employeeDTO);
			RequestDispatcher dispatch = request.getRequestDispatcher("jsp/employeeEdit.jsp");
			dispatch.forward(request, response);
		}catch(Exception e){
			System.out.println("--Exception: EmployeeAddAction = ");
			e.printStackTrace();
		}
		
	}
	
}

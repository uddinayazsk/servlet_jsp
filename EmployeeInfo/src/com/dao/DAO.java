package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.dto.EmployeeDTO;


public class DAO {
	private final Logger logger = Logger.getLogger(DAO.class);
	
	public boolean getUserCredentialFlag(String username, String password) {
		System.out.println("--DAO--");
		Connection con = null;
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		boolean userLoginFlag = false;
		String lvUserName = null;
		String lvPassword = null;
		String query = null;
		query = "select username, password from user where username = ? and password = ?";
		logger.debug("Query --> "+query);
		BasicConfigurator.configure();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loading Success");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test2","root", "root");
		} catch (ClassNotFoundException e1) {
			System.out.println("Driver Loading failure");
			e1.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		try{
			prepareStatement = con.prepareCall(query);
			prepareStatement.setString(1, username);
			prepareStatement.setString(2, password);
			result = prepareStatement.executeQuery();
			System.out.println("Query executed success");
			while(result.next()){
				lvUserName = result.getString("username");
				lvPassword = result.getString("password");
				System.out.println("DB username --> "+lvUserName);
				System.out.println("DB password --> "+lvPassword);
				if((lvUserName != null && lvUserName.equals(username)) && (lvPassword != null && lvPassword.equals(password))){
					userLoginFlag = true;
				}
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		try{
			if(result != null){
				result.close();
			}
			if(prepareStatement != null){
				prepareStatement.close();
			}
			if(con != null){
				con.close();
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		logger.debug("userLoginFlag --> "+userLoginFlag);
		return userLoginFlag;
	}
	//addEmployee
	public int addEmployee(EmployeeDTO employeeDTO) throws Exception{
		Connection con = null;
		PreparedStatement prepareStatement = null;
		Statement statement = null;
		ResultSet result = null;
		int employeeRecCreated = 0;
		int employeeId = 0;
		StringBuffer empAddquery = new StringBuffer();
		String employeeIdQuery = "SELECT MAX(employeeid) from EMPLOYEE";
		empAddquery.append("INSERT INTO EMPLOYEE(name, gender, location, dateofbirth)");
		empAddquery.append(" VALUES (?, ?, ?, ?)");
		System.out.println("--DAO : addEmployee()");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loading Success");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "mysql");
		} catch (ClassNotFoundException e1) {
			System.out.println("Driver Loading failure");
			e1.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		try{
			con.setAutoCommit(false);
			prepareStatement = con.prepareStatement(empAddquery.toString());
			prepareStatement.setString(1, employeeDTO.getEmpName());
			prepareStatement.setString(2, employeeDTO.getGender());
			prepareStatement.setString(3, employeeDTO.getLocation());
			prepareStatement.setDate(4, employeeDTO.getDateOfBirth());
			System.out.println("--Query = "+empAddquery.toString());
			employeeRecCreated = prepareStatement.executeUpdate();
			System.out.println("--employeeRecCreated count = "+employeeRecCreated);
			statement = con.createStatement();
			System.out.println("--EmployeeId Query = "+employeeIdQuery);
			result = statement.executeQuery(employeeIdQuery);
			System.out.println("--Query execute success= ");
			while(result.next()){
				employeeId = result.getInt(1);
				System.out.println("--EmployeeId = "+employeeId);
			}
			con.commit();
			con.setAutoCommit(true);
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		try{
			if(result != null){
				result.close();
			}
			if(prepareStatement != null){
				prepareStatement.close();
			}
			if(statement != null){
				statement.close();
			}
			if(con != null){
				con.close();
			}
		}catch(SQLException sqle){
			System.out.println("--DAO - addEmployee()- SQLException ");
			sqle.printStackTrace();
		}
		return employeeId;
	}
	//getEmployeeSearch
	public ArrayList getEmployeeSearch(String employeeSearchName) {
		Connection con = null;
		ArrayList<EmployeeDTO> employeeBeanList = new ArrayList<EmployeeDTO>();
		EmployeeDTO employeeDTO = null;
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		String query = "select employeeid, name, location, dateofbirth, gender from employee  where name like ?";
		//select u.employeeid, u.name, u.location from test.employee u where u.name like '%Abdullah%';
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loading Success");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "mysql");
		} catch (ClassNotFoundException e1) {
			System.out.println("Driver Loading failure");
			e1.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		try{
					
			prepareStatement = con.prepareStatement(query);
			prepareStatement.setString(1, "%" + employeeSearchName + "%");
			
			result = prepareStatement.executeQuery();
			while(result.next()){
				employeeDTO = new EmployeeDTO();
				employeeDTO.setEmpId(result.getInt("employeeid"));
				employeeDTO.setEmpName(result.getString("name"));
				employeeDTO.setLocation(result.getString("location"));
				employeeDTO.setGender(result.getString("gender"));
				employeeDTO.setDateOfBirth(result.getDate("dateofbirth"));
				employeeBeanList.add(employeeDTO);
			}
			System.out.println("--Result count = "+employeeBeanList.size());
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		try{
			if(prepareStatement != null){
				prepareStatement.close();
			}
			if(result != null){
				result.close();
			}
			if(con != null){
				con.close();
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return employeeBeanList;
	}
	//getEmployeeDetails
	public EmployeeDTO getEmployeeDetails(int empId) throws Exception{
		System.out.println("--DAO : getEmployeeDetails()--");
		PreparedStatement prepareStatement = null;
		Connection con = null;
		List<EmployeeDTO> empList = new ArrayList<EmployeeDTO>();
		EmployeeDTO employeeDTO = null;
		ResultSet result = null;
		StringBuilder searchEmpDetail = new StringBuilder();
		searchEmpDetail.append("SELECT employeeid, name, location, gender, dateofbirth ");
		searchEmpDetail.append("FROM EMPLOYEE WHERE employeeid = ?");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loading Success");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "mysql");
		} catch (ClassNotFoundException e1) {
			System.out.println("Driver Loading failure");
			e1.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		try{
			
			prepareStatement = con.prepareStatement(searchEmpDetail.toString());
			prepareStatement.setInt(1, empId);
			System.out.println("--empId = "+empId);
			System.out.println("--EmpDetailQuery = "+searchEmpDetail);
			result = prepareStatement.executeQuery();
			//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			while(result.next()){
				employeeDTO = new EmployeeDTO();
				System.out.println("--inside = "+result.getInt("employeeid"));
				employeeDTO.setEmpId(result.getInt("employeeid"));
				employeeDTO.setEmpName(result.getString("name"));
				employeeDTO.setGender(result.getString("gender"));
				employeeDTO.setLocation(result.getString("location"));
				//employeeDTO.setDateOfBirth(formatter.format(result.getString("dateofbirth")));
				employeeDTO.setDateOfBirth(result.getDate("dateofbirth"));

			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		try{
			if(result != null){
				result.close();
			}
			if(prepareStatement != null){
				prepareStatement.close();
			}
			if(con != null){
				con.close();
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return employeeDTO;
	}
	//updateEmployee
	public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws Exception{
		System.out.println("--DAO : updateEmployee()--");
		Connection con = null;
		PreparedStatement prepareStatement = null;
		Statement statement = null;
		ResultSet result = null;
		EmployeeDTO empDTO = null;
		StringBuilder updateEmpDetail = new StringBuilder();
		//UPDATE test.employee set name='Habeeba' where employeeid = 46;
		updateEmpDetail.append("UPDATE EMPLOYEE set name = ?, location = ?, gender = ?, dateofbirth = ? ");
		//updateEmpDetail.append("UPDATE EMPLOYEE employeeid, name, location, gender, dateofbirth ");
		updateEmpDetail.append("WHERE employeeid = ?");
		StringBuilder searchEmpDetail = new StringBuilder();
		searchEmpDetail.append("SELECT employeeid, name, location, gender, dateofbirth ");
		searchEmpDetail.append("FROM EMPLOYEE WHERE employeeid = "+employeeDTO.getEmpId());
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loading Success");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "mysql");
		} catch (ClassNotFoundException e1) {
			System.out.println("Driver Loading failure");
			e1.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		try{
			prepareStatement = con.prepareStatement(updateEmpDetail.toString());
			prepareStatement.setString(1, employeeDTO.getEmpName());
			prepareStatement.setString(2, employeeDTO.getLocation());
			prepareStatement.setString(3, employeeDTO.getGender());
			prepareStatement.setDate(4, employeeDTO.getDateOfBirth());
			prepareStatement.setInt(5, employeeDTO.getEmpId());
			System.out.println("--EmpUpdateQuery = "+updateEmpDetail);
			prepareStatement.executeUpdate();
			
			statement = con.createStatement();
			result = statement.executeQuery(searchEmpDetail.toString());
			while(result.next()){
				empDTO = new EmployeeDTO();
				empDTO.setEmpId(result.getInt("employeeid"));
				empDTO.setEmpName(result.getString("name"));
				empDTO.setGender(result.getString("gender"));
				empDTO.setLocation(result.getString("location"));
				empDTO.setDateOfBirth(result.getDate("dateofbirth"));

			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		try{
			if(statement != null){
				statement.close();
			}
			if(prepareStatement != null){
				prepareStatement.close();
			}
			if(con != null){
				con.close();
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return empDTO;
	}

	//deleteEmployee
	public void deleteEmployee(String empIdStr) throws Exception{
		System.out.println("--DAO : deleteEmployee()--");
		PreparedStatement prepareStatement = null;
		Connection con = null;
		ResultSet result = null;
		StringBuilder deleteEmpRec = new StringBuilder();
		int deleteRecCount = 0;
		int empId = 0;
		empId = Integer.parseInt(empIdStr);
		//delete from test.employee where employeeid=15
		deleteEmpRec.append("DELETE FROM EMPLOYEE WHERE employeeid = ?");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loading Success");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "mysql");
		} catch (ClassNotFoundException e1) {
			System.out.println("Driver Loading failure");
			e1.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		try{
			prepareStatement = con.prepareStatement(deleteEmpRec.toString());
			prepareStatement.setInt(1, empId);
			System.out.println("--empId = "+empIdStr);
			System.out.println("--EmpDeleteQuery = "+deleteEmpRec);
			deleteRecCount = prepareStatement.executeUpdate();
			System.out.println(" delete record count = "+ deleteRecCount);
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		try{
			
			if(prepareStatement != null){
				prepareStatement.close();
			}
			if(con != null){
				con.close();
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
}

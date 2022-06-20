<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Date, java.text.SimpleDateFormat, java.util.ArrayList,java.util.Iterator" %>
<%@ page import="com.dto.EmployeeDTO" %>    
<jsp:useBean id="employeeDTO1" class="com.dto.EmployeeDTO" scope="request">
</jsp:useBean>

<%
	ArrayList employeeBeanList = (ArrayList)request.getAttribute("EMPLOYEEDETAILS");
	request.getSession().setAttribute("EMPLOYEEDETAILS", employeeBeanList);
	EmployeeDTO employeeDTO;
%> 

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Employee Search Result</title>
	<script type="text/javascript">
		function deleteRec(){
			//alert(document.getElementById("chk").value);
			var empId = document.getElementById("chk").value;
			//alert('empId = '+empId);
			document.frmEmpSearch.method="post";
			document.frmEmpSearch.action = "/EmployeeInfo/employeeDeleteAction?employeeId="+empId;
			document.frmEmpSearch.submit();
		}
	
	</script>
	</head>
	<body>
	
	<jsp:include page="header.jsp"></jsp:include>
	
		<form method="post" name="frmEmpSearch" action="/EmployeeInfo/employeeEditAction">
			<fieldset>
		    	<legend width="80%">Employee Search List</legend>
		    	<table align="left" width="80%" height="" background="green" cellpadding="1" border="1">
			    <tr>
			    	<td>&nbsp;</td>
					<td>EmployeeId</td>
					<td>Employee Name</td>
					<td>Gender</td>
					<td>Date of Birth</td>
					<td>Operation</td>
				</tr>
				
					<%  
						Iterator itr = employeeBeanList.iterator();
						if (employeeBeanList != null){
							while(itr.hasNext()){
								employeeDTO = (EmployeeDTO)itr.next(); 
						%>
							<tr>
								<td><input type="checkbox" id="chk" name="chk" value="<%=employeeDTO.getEmpId()%>" ></td>
								<td><a name="aEmp" href="/EmployeeInfo/employeeEditAction?employeeId=<%=employeeDTO.getEmpId()%>"><%=employeeDTO.getEmpId()%><a></td>
								<td><%=employeeDTO.getEmpName()%></td>
								<td><%=employeeDTO.getGender()%></td>
								<td><%=employeeDTO.getDateOfBirth()%></td>
								<td><input type="button" name="btnDelete" value="delete" onclick="deleteRec()"></td>
						<%} %>
					<%} %>
							</tr>	
				    
			    </table>
		    </fieldset>
		</form>
	</body>
</html>
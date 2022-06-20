<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 
	System.out.println("--employeeAdd page--");
   
%>    
<html>
	<head>
	<script type="text/javascript">
		
	</script>
	
	<title>Employee Add Screen</title>
	</head>
	<body>
	
		<jsp:include page="header.jsp"></jsp:include>
		
		<form name="frmEmployeeAdd" method="post" action="/EmployeeInfo/employeeAddAction">
			
			<fieldset>
			    <legend width="80%">Personal Info</legend>
			    <table align="left" width="80%" height="" background="green" cellpadding="1" border="1">
				    <tr>
						<td width="40%">Employee Name</td>
						<td width="50%"><input type="text" name="txtEmpName" value="" size="20"></td>
					</tr>
					<tr>
						<td width="40%">Gender</td>
						<td width="50%">
							<select id="comboEmpGender" name="comboEmpGender">
								<option value="m" >Male</option>
								<option value="f" >Female</option>
								<option value="a" >Other</option>
							</select>
						</td>
					</tr>
				    <tr>
						<td width="40%">Date of Birth</td>
						<td width="50%"><input type="text" name="txtEmpDateOfBirth" value="" size="20">&nbsp;(dd/mm/yyyy)</td>
					</tr>	
					<tr>
						<td width="40%">Location</td>
						<td width="50%"><input type="text" name="txtEmpLocation" value="" size="20"></td>
					</tr>	
					<tr>
						<td width="100%" colspan="2" align="center"><input type="submit" name="submit" value="Save" size="20"></td>
					</tr>    
			    </table>
		    </fieldset>
			
		</form>
	</body>
</html>
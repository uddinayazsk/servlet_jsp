<%@ page import="java.sql.Date, java.text.SimpleDateFormat" %>    
<%@ page import="com.dto.EmployeeDTO" %>
<%@ page import="java.sql.Date, java.text.SimpleDateFormat" %>
<jsp:useBean id="employeeDTO1" class="com.dto.EmployeeDTO" scope="request">
</jsp:useBean>
    
<% 
    EmployeeDTO employeeDTO;
	System.out.println("--employeeEdit page --");
	String employeeIdStr = null;
	boolean updateFlag = false;
	if(request.getParameter("employeeId") != null){
		employeeIdStr = request.getParameter("employeeId");
	}else{
		employeeIdStr = (String)request.getAttribute("EMPID");
		updateFlag = true;
	}
	
	int employeeId = Integer.parseInt(employeeIdStr);
	System.out.println("--employeeId = "+employeeId);
	
	String name = "";
	String gender = "";
	String location = "";
	String dateOfBirth = "";
	Date dob = null;
	employeeDTO = (EmployeeDTO)request.getAttribute("EMPDETAIL");
	if(employeeDTO != null){
		name = employeeDTO.getEmpName();
		gender = employeeDTO.getGender();
		location = employeeDTO.getLocation();
		dob = employeeDTO.getDateOfBirth();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		dateOfBirth = formatter.format(dob);
	}
%>    
<html>
	<head>
	<script type="text/javascript">
		function selectGender(){
			var len = document.frmEmployeeUpdate.comboEmpGender.length;
			var gen = '<%=gender%>';
			for(i=1; i<=len; i++){
				if(document.frmEmployeeUpdate.comboEmpGender[i].value == gen){
					document.frmEmployeeUpdate.comboEmpGender[i].selected = true;
				}
			}
		}
	
	</script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Employee Add Screen</title>
	</head>
	<body onload="selectGender()">
		
		<%
			if (updateFlag){
		%>
			<div style="color:green">Your record is updated successfully!!!</div>
		<%
			}
		%>
		<jsp:include page="header.jsp"></jsp:include>
		
		<form name="frmEmployeeUpdate" method="post" action="employeeUpdateAction?empid=<%=employeeId%>">
			<fieldset>
			    <legend width="80%">Personal Info</legend>
			    <table align="left" width="80%" height="" background="green" cellpadding="1" border="1">
				    <tr>
						<td width="40%">Employee Name</td>
						<td width="50%"><input type="text" name="txtEmpName" value="<%=name%>" size="20"></td>
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
						<td width="50%"><input type="text" name="txtEmpDateOfBirth" value="<%=dateOfBirth%>" size="20">&nbsp;(dd/mm/yyyy)</td>
					</tr>	
					<tr>
						<td width="40%">Location</td>
						<td width="50%"><input type="text" name="txtEmpLocation" value="<%=location%>" size="20"></td>
					</tr>	
					<tr>
						<td width="100%" colspan="2" align="center"><input type="submit" name="submit" value="Update" size="20"></td>
					</tr>    
			    </table>
		    </fieldset>
		</form>
	</body>
</html>
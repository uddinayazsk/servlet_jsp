<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		
		<form method="post" action="/EmployeeInfo/employeeSearchAction">
				<fieldset>
				    <legend width="80%"><i>Search Employee</i></legend>
				    <table align="left" width="40%" height="" background="green" cellpadding="1" border="0">
					    <tr>
							<td width="20%">Enter employee name</td>
							<td width="20%"><input type="text" name="txtEmployeeSearch" size="15"></td>
						</tr>
						<tr>
							<td colspan="2" width="40%"><input type="submit" name="search" value="Search" ></td>
						</tr>    
				    </table>
			    </fieldset>
		</form>
	</body>
</html>
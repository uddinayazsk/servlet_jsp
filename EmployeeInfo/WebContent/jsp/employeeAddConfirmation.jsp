<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 
	System.out.println("--employeeAdd confirmation page--");
	String employeeStrId = (String)request.getAttribute("EMPID");
	System.out.println("--employeeId = "+employeeStrId);
%>    
<html>
	<head>
	<script type="text/javascript">
		
	</script>
	
	<title>Employee Add Screen</title>
	</head>
	<body>
	
		<jsp:include page="header.jsp"></jsp:include>
		
		<%if(employeeStrId != null){ %>
				<div style="background: green">Congratulation! Your Employee Id is <%=employeeStrId%></div>
		<%} %>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	session.invalidate();
%>

<html>
	<head>
	<script type="text/javascript">
		function logout(){
			document.frmLogout.action="login.jsp";
			document.frmLogout.submit();
		}
	</script>
	
	<title>Employee logout Screen</title>
	</head>
	<body onload="logout()">
		<form name="frmLogout" method="post"> </form>
	</body>
</html>
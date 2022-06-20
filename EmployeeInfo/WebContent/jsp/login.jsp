<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<script type="text/javascript">
	function valid(){
		var name = document.getElementById("txtName").value;
		var pass = document.getElementById("txtPassword").value;
		if(name == ""){
			alert('username field cannot be empty');
			return false;
		}else if(pass == ""){
			alert('password field cannot be empty');
			return false;
		}else{
			return true;	
		}
	}
</script>
</head>
	<body>
		<form name="frmLogin" method="post" action="/EmployeeInfo/loginAction" onsubmit="return valid();">
			<table align="center" width="50%" height="50%" background="green" cellpadding="1" border="1">
				<tr><td colspan="2" align="center">User Login Screen</td></tr>
				<tr>
					<td width="50%">User Name </td>
					<td width="50%"><input type="text" id="txtName" name="txtName" value="" size="20"></td>
				</tr>
				<tr>
					<td width="50%">Password</td>
					<td width="50%"><input type="password" size="20" id="txtPassword" name="txtPassword"></td>
				</tr>
				<tr><td colspan="2"><input type="submit" value="Login" ></td></tr>
			</table>
		</form>
	</body>
</html>
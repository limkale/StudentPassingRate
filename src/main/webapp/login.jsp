<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
	<div align="center">
		<h1>Login Form</h1>
		<form id="login-form" action="login" method="post">
			<table style="with: 100%">
				<tr>
					<td>UserId</td>
					<td><input id="userId" type="text" name="userId" required/></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input id="password" type="password" name="password" required/></td>
				</tr>

			</table>
			<input type="submit" value="Login" />
		</form>
	</div>
	
	<script>
		$(document).ready(function() {
  			$('#login-form').submit(function(e) {
    			$('input[required]').each(function() {
      				if ($(this).val().trim() === '') {
        				alert("All fields are mandatory");
        				e.preventDefault();
        				return false;
      					}
    				});
  				});
			});
	</script>

</body>
</html>
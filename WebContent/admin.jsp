<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.PrintWriter"%>
<%
	String uname = (String) session.getAttribute("user");
if (uname != null) {
	response.sendRedirect("adminprofile.jsp");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link href="assets/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="assets/js/jquery.js"></script>
<script type="text/javascript" src="assets/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.validate.min.js"></script>
<style type="text/css">
.navbar-inner {
	background: #330099;
	border-bottom: 5px solid ;
	height: 120px;
}

.navbar-inner .brand {
	color: #000;
}
</style>
</head>


<body style="background-color: #E0E0E0;">
	<script type="text/javascript" src="assets/js/bootstrap-button.js"></script>

	<script type="text/javascript" src="assets/js/modal.js"></script>
	<script type="text/javascript" src="script1.js"></script>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">

				<a href="#" class="brand"> <img src="./assets/img/daiict.jpg"
					alt="Exam Show" width="100px" height="120px" /></a> <br />
				<h1 class="brand" style="font-weight: bold;color:white;padding-left:600px;font-size:40px;">Online Examination
					System</h1>
			</div>
		</div>

	</div>

	<div class="container well">
		<div class="row">
			<div class="span6">
				<p style="font-weight: bold; font-size: 25px; color: #330099;">Admin
					Panel</p>
			</div>

			<div class="span6">

				<form action='<%=response.encodeURL("adminlogin")%>'
					id="contact-form" class="form-horizontal" method="post">
					<div class="control-group">
						<label class="control-label" for="name">Admin ID</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-user"></i></span> <input
									type="text" class="input-large" name="username" id="username"
									placeholder="User Name" onkeyup="loadXMLDoc()" /> <span
									id="err"> </span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">Password</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><i class="icon-lock"></i></span> <input
									type="password" class="input-large" name="passwd" id="passwd"
									placeholder="******" />
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label"></label>
						<div class="controls">

							<c:if test='${not empty param["RetryAdmin"]}'>
								<p style="color: red; font-weight: bold;">Wrong Username or
									Password.</p>

							</c:if>
							<button type="submit" class="btn btn-success"
								data-loading-text="Loading..." style="background:#330099">Login</button>
						</div>
					</div>
				</form>


			</div>
		</div>
	</div>

</body>

</html>
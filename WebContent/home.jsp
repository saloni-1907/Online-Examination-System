<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String fuser = (String)session.getAttribute("facultyname");
String user = (String)session.getAttribute("studentid"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Home Online Examination System</title>
<link href="style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="assets/js/jquery.js"></script>
		<script type="text/javascript" src="assets/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="assets/js/jquery.validate.min.js"></script>
               
</head>


<body style="background-color: lightgray;">
    <script type="text/javascript" src="assets/js/bootstrap-button.js"></script>
    <script type="text/javascript" src="assets/js/bootstrap-tab.js"></script>
    <script type="text/javascript" src="assets/js/modal.js"></script>
             <%
                    if(user != null ||fuser!=null)
               {%>

        <%@include file="header1.jsp" %>
        <%  }
                else
                {%>
                 <%@ include file="header1.jsp" %>
                 <% }
   %>
<div class="container well">
    <div class="row">
        <div class="span2">
       
            <ul class="nav nav-tabs nav-stacked nav-justified"  style='background-color:white;'>
                    <li>
                    <a href="#" data-toggle="tab">Home</a>
                    </li>
                    <li>
                    <a href="#" data-toggle="tab">Online Examination System</a>
                    </li>
                    <li>
                         <a href="./<%=session.getAttribute("which")%>profile.jsp">Profile</a>
                    </li>
            </ul>
        </div>

        <div class="span10">
            <center>    <img src="assets/img/background_img.jpg" alt="Online Exam System" height="700px" width="700px"/>  </center>
        
        </div>
        
    </div>
<br/><br/>
</div>
<br/><br/><br/><br/>
 <%@include file="footer.jsp" %>
</body>

</html>
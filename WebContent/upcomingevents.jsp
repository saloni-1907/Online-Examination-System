
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.sql.*"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="connection.Config"%>
<%

            String user = (String)session.getAttribute("studentid");          
            String ffname = (String)session.getAttribute("facultyname");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Upcoming Events Online Examination System</title>
		<script type="text/javascript" src="assets/js/jquery.js"></script>
		<script type="text/javascript" src="assets/js/jquery-1.7.1.min.js"></script>


</head>


<body style="background-color:  #E0E0E0;">
  <script>

window.location.hash="no-back-button";
window.location.hash="Again-No-back-button";
window.onhashchange=function(){window.location.hash="no-back-button";}
</script>
           <script type="text/javascript" src="assets/js/bootstrap-tab.js"></script>
                <script src="assets/js/bootstrap-collapse.js" type="text/javascript"></script>

                <script src="assets/js/bootstrap.js" type="text/javascript"></script>

                       <script src="assets/js/jquery-1.js" type="text/javascript"></script>
                <script src="assets/js/jquery_1.js" type="text/javascript"></script>
                    <script src="assets/js/application_1.js" type="text/javascript"></script>
                    <script type="text/javascript" src="assets/js/bootstrap-button.js"></script>
                   <%
                    if(user != null || ffname!=null )
               {%>

        <%@include file="header1.jsp" %>
        <%  }
                else
                {%>
                 <%@ include file="header.jsp" %>
                 <% }
   %>
                   
<div class="container">
<div class="row">
<c:if test='${not empty param["Register"]}'>
 <p style="color:green;font-weight:bold;">You are successfully Registered.</p>
</c:if>
    <table id="sortTableExample" class='table zebra-striped'>
           <thead>
            <tr>
                <th class="header">S No.</th>
                <th class="red header">Subject Code</th>
                <th class="blue header">Start Date</th>
                <th class="green header headerSortUp">End Date</th>
                <th class="yellow header">Register</th>
            </tr>
            </thead>
            <tbody>
<%        
     
        Connection con=null;
        Statement st=null;
        Statement stt=null;
        ResultSet rs=null;
        ResultSet rss=null;
        Config c = new Config();
        con = c.getcon();
        st=con.createStatement();
        stt=con.createStatement();
        int i=0,cnt=0;
        String scode="";
        try
        {
            String   qry = "select * from exams";
            rs = st.executeQuery(qry);
            
            while(rs.next())
            {
                i++;
                cnt=0;
                scode = rs.getString("scode");
                System.out.println(scode);
%>
                <tr>
                    <td>
                        <%=i%>
                    </td>
                    <td>
                        <a href='quiz.jsp?scode=<%=scode%>'><%=scode%></a>
                    </td>
                    <td>
                        <%=rs.getTimestamp("startdate")%>
                    </td>
                    <td>
                        <%=rs.getTimestamp("enddate")%>
                    </td>
                    <td>
                      <%
                           qry = "select count(*) as col from result"+scode+" where username='"+user+"'";
                           rss = stt.executeQuery(qry);
                           if(rss.next())
                           {
                               cnt = Integer.parseInt(rss.getString("col"));
                            }
                           if(cnt == 0)
                               {
                       %>
                                     <form action="reg.jsp" method="post">
                                            <input type="hidden" name="scode" value="<%=scode%>" />
                                            <button type='submit' class='btn btn-danger'>Register</button>
                                     </form>
                           <%
                                }//if
                                           else
                                {
                                %>
                                 <p style="color:green;font-weight:bold;">Registered</p>
                                 <%
                                 }//else
                          %>
                    </td>
                </tr>
   
 <%

           }//while
 %>

        </tbody></table>
<%
        }
        catch(Exception e){}

        con.close();
 %>



</div>
 <br/><br/>
</div>
  <br/><br/>
 <%@include file="footer.jsp" %>
</body>
</html>
<%@page import="java.sql.*"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="connection.Config"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>View Result Online Examination System</title>

<link href="assets/css/bootstrap_1.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bootstrap-responsive.css" rel="stylesheet" type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="assets/js/jquery.js"></script>
		<script type="text/javascript" src="assets/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="assets/js/jquery.validate.min.js"></script>
             
<style type="text/css">
          .navbar-inner {
	background: #330099;
	border-bottom: 5px solid;
	height: 120px;
}
.navbar-inner .brand {
	color: #000;}
</style>
</head>

<!-- Body -->

<body style="background-color:#E0E0E0;">
    <script type="text/javascript" src="assets/js/bootstrap-button.js"></script>
    <script type="text/javascript" src="assets/js/bootstrap-tab.js"></script>
    <script type="text/javascript" src="script1.js"></script>
    <script src="assets/js/jquery-1.js" type="text/javascript"></script>
    <script src="assets/js/jquery_1.js" type="text/javascript"></script>
    <script src="assets/js/application_1.js" type="text/javascript"></script>
                    
<div class="navbar">
<div class="navbar-inner">
<div class="container">

<a href="#" class="brand"> <img src="./assets/img/daiict.jpg" alt="Exam Show" width="100px" height="70px"/></a>
<br/>
<h1 class="brand" style="font-weight: bold;color:white;padding-left:650px;font-size:40px;">Online Examination System</h1>
<form action="logout" method="post" class="pull-right">

<button class="btn btn-primary" style="background:#787878;height:45px;width:140px;font-size:20px;"> Logout </button>
</form>
<p class="pull-right" style="color:white;font-size:20px;">
<br />

<%
response.setHeader("cache-control","no-cache");
response.setHeader("cache-control","no-store");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
             String uname = (String)session.getAttribute("facultyname");
             if(uname==null)
                {
                    response.sendRedirect("index.jsp");
                }
            else
            {
                   out.println("<b> Welcome , <a href='facultyprofile.jsp' style='color:white'>" +uname+ "</a></b>");
            }
%>
&nbsp;&nbsp;
</p>
</div>
</div>
</div>

<div class="container well">
    <div class="row">

        <div class="span2">
            <ul class="nav nav-tabs nav-stacked nav-justified"  style='background-color:white;'>
                    <li class="active">
                    <a href="home.jsp" data-toggle="tab">Home</a>
                    </li>
                    <li>
                    <a href="<%=session.getAttribute("which")%>profile.jsp" >Profile</a>
                    </li>
                     <li>
                    <a href="#viewsub" data-toggle="tab">View Subjects</a>
                    </li>
            </ul>
        </div>
   <div id="maincontent" class="span9 pull-right">
            <div id="myTabContent" class="tab-content">
                <div id="paper" class="tab-pane fade in active">
                <form action="facultyprofile.jsp" method="post">
                    <%
                     PrintWriter writer=response.getWriter();
                     String scode=request.getParameter("scode");
                     String sname="";
                     session.setAttribute("subcode",scode);
                     String author = (String)session.getAttribute("facultyid");
                     //session.setAttribute("subname",sname);
                     

                    Connection con=null;
                    Statement st=null,st1=null,st2=null;
                    ResultSet rs=null,rs1=null,rs2=null;

                    Config c = new Config();
                    con = c.getcon();
                    
                    int cnt = 0,nofcol=0;
                    String ans="",myans="",user="";
                    try
                        {
                            st=con.createStatement();
                            st1=con.createStatement();
                            st2=con.createStatement();
                            String   qry = "select count(*) as col from subjects where subjectcode='"+scode+"' and author='"+author+"'";
                            rs = st.executeQuery(qry);
                            if(rs.next())
                                cnt = Integer.parseInt(rs.getString("col"));
                            if(cnt==0)
                                response.sendRedirect("facultyprofile.jsp?ErrorResult=True");
                            else
                                {
                            qry = "select subjectname from subjects where subjectcode='"+scode+"'";
                            rs = st.executeQuery(qry);
                            if(rs.next())
                                sname = rs.getString("subjectname");
  
                            session.setAttribute("subname",sname);
                            out.println("<center><h1 style='color:black;'> "+sname+" ScoreCard: </h1></center><br/>");
                            String query = "SELECT COUNT(*) as col FROM information_schema.COLUMNS WHERE WHERE table_schema = 'examshow'AND table_name = 'result"+scode+"'";
      
                                     rs1 = st1.executeQuery(query);
//out.println("her");
                            if(rs1.next())
                                nofcol = Integer.parseInt(rs1.getString("col"));
                            nofcol -= 7;
//           out.println(nofcol);
                            query="select * from result"+scode;
                            rs=st.executeQuery(query);

   
       
                            int wrong=0,correct=0,skipped=0,score=0;
                            while(rs.next())
                            {
                                wrong=0;correct=0;skipped=0;score=0;
                                user = rs.getString("username");
                                for(int q=1;q<=nofcol;q++)
                                {
                                    myans = rs.getString("q"+q);
                                    query = "select * from "+scode+" where qno="+q;
                                    rs2 = st2.executeQuery(query);
                                    if(rs2.next())
                                    {
                                        ans = rs2.getString("ans");
                                        if(ans.equals(myans))
                                            correct++;
                                        else if(myans.equals("null"))
                                            skipped++;
                                        else
                                            wrong++;
                                      }
                                }//one row complete process
                                score = correct*4 - wrong;
                                query = "update result"+scode+" set score="+score+",correct="+correct+",wrong="+wrong+",skipped="+skipped+" where username='"+user+"'";
                                st1.executeUpdate(query);
                             }

                          query = "select * from result"+scode;
                          rs1 = st1.executeQuery(query);
   %>

              <table id='sortTableExample' class='table zebra-striped'>
                  <thead><tr>
                <th class="header">S No.</th>
                <th class="red header">Username</th>
                <th class="green header">Correct</th>
                <th class="red header">Wrong</th>
                <th class="yellow header">Skipped</th>
                <th class="blue header headerSortUp">Score</th>
                      </tr></thead>
                <tbody>
<%
                          int i=1;
                          while(rs1.next())
                          {

                       %>


                          <tr style="font-weight:bold;">
                              <td><%=i%></td>
                              <td><%=rs1.getString("username")%></td>
                              <td><%=rs1.getString("correct") %></td>
                              <td><%=rs1.getString("wrong") %></td>
                              <td><%=rs1.getString("skipped") %></td>
                              <td><%=rs1.getString("score") %></td>
                          </tr>
                
                         <%
                         i++;
                          }
                            out.println(" </tbody></table>");
                           

                        }//else
                     }//try
                    catch(Exception e)
                        {
                            System.out.println("Error="+e);
                        }


                    %>
                    <br/>
                     <button type="submit" class="btn btn-success" style="background:#330099">Go Back to Profile</button>
                </form>

                </div>


                <div id="viewsub" class="tab-pane fade in">

                  <%

                                try
                                {
                                    String query="select * from subjects";
                                    rs=st.executeQuery(query);
                                    out.println("<table class='table table-striped'>");
                                    while(rs.next())
                                    {
                                        out.println("<tr>");
                                        out.println("<td>"+rs.getString("subjectname")+"</td>");
                                        out.println("<td>"+rs.getString("subjectcode")+"</td>");
                                        out.println("</tr>");
                                    }
                                    out.println("</table>");
                                }
                                catch(Exception e){}


                    con.close();
                  %>

                </div>

            </div>
            </div>
        </div>

    </div>
 <%@include file="footer.jsp" %>
</body>
</html>
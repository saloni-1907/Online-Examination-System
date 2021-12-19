

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import connection.Config;
import java.sql.*;

@WebServlet(name = "showresult_servlet", urlPatterns = {"/showresult_servlet"})
public class showresult_servlet extends HttpServlet {
   HttpSession session=null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String scode = request.getParameter("scode");
        String user = session.getAttribute("studentid").toString();
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        Statement stt=null;
        ResultSet rss=null;
        Config c = new Config();
        con = c.getcon();
        int cnt=0,rows;
        try {
            st=con.createStatement();
            stt=con.createStatement();
            String qry = "select count(*) as col from subjects wher subjectcode='"+scode+"'";
            rs = st.executeQuery(qry);
            if(rs.next())
                cnt = Integer.parseInt(rs.getString("col"));
            
            if(cnt==1)
            {
                //subject code exists
                java.util.Date date= new java.util.Date();
                Timestamp ts = new Timestamp(date.getTime());


               qry = "select * from exams where scode='"+scode+"'";
               rs = st.executeQuery(qry);
               if(rs.next())
               {
                   Timestamp sdate = rs.getTimestamp("startdate");
                   Timestamp edate =  rs.getTimestamp("enddate");
                   if(ts.compareTo(edate)>0)
                   {
                        //contest ended
                        qry = "select * from result"+scode+" where username='"+user+"'";
                        rss = stt.executeQuery(qry);
                        if(rss.next())
                        {
                            con.close();
                            response.sendRedirect("result.jsp");
                        }
                        else
                        {
                            //not given exam
                            con.close();
                            response.sendRedirect("studentprofile.jsp?NotGiven=True");
                        }


                   }
                   else
                   {
                       con.close();
                        response.sendRedirect("studentprofile.jsp?Unavailable=True");
                   }

                }

            }
            else
            {
                con.close();
                response.sendRedirect("studentprofile.jsp?ErrorCode=True");
            }
           
            con.close();
        }
        catch(Exception e){}
        
        finally {
            out.close();
        }
    } 

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        session  = request.getSession();
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

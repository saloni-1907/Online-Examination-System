

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import connection.*;
import java.security.MessageDigest;
import sun.misc.*;
import mail.*;
//import mail.MailVerify;
import mail.AuthCode;

@WebServlet(name = "studentregistration", urlPatterns = {"/studentregistration"})
public class studentregistration extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name=request.getParameter("sname");
        String rollno=request.getParameter("username");
        String passwd=request.getParameter("passwd");
        String institute=request.getParameter("institute");
        String sem=request.getParameter("sem");
        //String sem="00;
        String email=request.getParameter("email");
        String number=request.getParameter("number");
        String d = request.getParameter("day");
        String m = request.getParameter("month");
        String y = request.getParameter("year");
        String dob = d+"/"+m+"/"+y;
        String sex=request.getParameter("sex");
        ResultSet rs;
          
           
        try
        {
            AuthCode authCode=new AuthCode();
            String code=authCode.generateCode();
            //password encription
            MessageDigest MD5=MessageDigest.getInstance("MD5");
            MD5.update(passwd.getBytes(),0,passwd.getBytes().length);
            byte[] hashvalue=MD5.digest();
            String newpasswd=Base64.getEncoder().encodeToString(passwd.getBytes());
            Config c = new Config();
            Connection con = c.getcon();
            Statement st = con.createStatement();
            String qry = "select count(*) as col from student where studentid ='"+rollno+"'";
            rs = st.executeQuery(qry);
            int check=0;
            if(rs.next())
            {
                check = Integer.parseInt(rs.getString("col"));
            }
            if(check==0)
            {
                String query="insert into student values('"+name+"','"+rollno+"','"+newpasswd+"','"+institute+"','"+sem+"','"+email+"','"+number+"','"+dob+"','"+sex+"','"+code+"')";
               // out.println(query);
                st.executeUpdate(query);
                con.close();
                response.sendRedirect("index.jsp?RegisterStudent=True");
            }
            else
            {
                con.close();
                response.sendRedirect("index.jsp?existsStudent=True");
            }

            con.close();
        }
        catch(Exception e)
        {
            System.out.println("Error="+e);
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
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

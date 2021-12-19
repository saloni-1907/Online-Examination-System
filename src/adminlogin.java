
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import connection.*;

@WebServlet(name = "adminlogin", urlPatterns = {"/adminlogin"})
public class adminlogin extends HttpServlet {

    HttpSession session;
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String uname=request.getParameter("username");
        String passwd=request.getParameter("passwd");
       
        ResultSet rs;
        try
        {
            Config c = new Config();
            Connection con = c.getcon();
            Statement st = con.createStatement();
            String query="select * from admin where username='"+uname+"'";
            rs=st.executeQuery(query);
            if(rs.next())
            {
                if(passwd.equals(rs.getString("password")))
                {

                    session.setAttribute("user",rs.getString("username"));
                    session.setAttribute("which", "admin");
                    con.close();
                    response.sendRedirect("adminprofile.jsp");
                }
                else
                {
                    con.close();
                    response.sendRedirect("adminpanel.jsp?RetryAdmin=True");
                }
            }
            else{
                con.close();
                response.sendRedirect("adminpanel.jsp?RetryAdmin=True");
            }

            con.close();
        }
        catch(Exception e)
        {
           out.println("Error "+e);
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
        session = request.getSession(true);
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

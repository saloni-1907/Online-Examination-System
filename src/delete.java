
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

@WebServlet(name = "delete", urlPatterns = {"/delete"})
public class delete extends HttpServlet {

    HttpSession session;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username =  request.getParameter("user");
        String who = request.getParameter("who");
        out.println(username);
       String query="";
    
    try
        {
            Config c = new Config();
            Connection con = c.getcon();
            Statement st = con.createStatement();
            if(who.equals("student"))
            {
                query = "delete from student where studentid='"+username+"'";
                st.executeUpdate(query);
                con.close();
                response.sendRedirect("adminprofile.jsp?studentSuccess=True&student=True");
            }
            else if(who.equals("faculty"))
            {
                query = "delete from faculty where facultyid='"+username+"'";
                st.executeUpdate(query);
                con.close();
                response.sendRedirect("adminprofile.jsp?facultySuccess=True&faculty=True");
            }
            else if(who.equals("subject"))
            {
                query = "drop table "+username;
                st.executeUpdate(query);
                query = "drop table result"+username;
                st.executeUpdate(query);
                query = "delete from subjects where subjectcode='"+username+"'";
                st.executeUpdate(query);
                con.close();
                response.sendRedirect("adminprofile.jsp?subjectSuccess=True&subject=True");
            }


            con.close();
        }
        catch(Exception e){out.println("Error "+e);}

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

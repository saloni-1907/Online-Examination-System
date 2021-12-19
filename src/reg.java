
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import connection.*;
import javax.servlet.http.HttpSession;

@WebServlet(name = "reg", urlPatterns = {"/reg"})
public class reg extends HttpServlet {
    HttpSession session;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String scode = request.getParameter("scode");
        out.println(session);
        String user="sdsda";
           

        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        Config c = new Config();
        con = c.getcon();

        int qno=0,cnt=0,cnt2=0;
        try
        {
            user = session.getAttribute("studentid").toString();
            out.println(user);
            if(user.equals(""))
            {
           
                response.sendRedirect("index.jsp");
            }

            st=con.createStatement();
            String qry = "insert into result"+scode+"(username) values('"+user+"')";
            st.executeUpdate(qry);
            con.close();
            response.sendRedirect("upcomingevents.jsp?Register=True");
        }

        catch(Exception e)
        {
            System.out.println("Error="+e);
        }

 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession(true);
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

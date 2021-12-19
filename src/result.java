

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

@WebServlet(name = "result", urlPatterns = {"/result"})
public class result extends HttpServlet {
    HttpSession session;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String scode = session.getAttribute("subcode").toString();
        String user = session.getAttribute("studentid").toString();
        String qid = request.getParameter("q_id");
        int q_id = Integer.parseInt(qid);
        session.setAttribute("q_id",qid);

      
        
        q_id -= 1;
        String ans = request.getParameter("q"+q_id);
        ResultSet rs;
        try
        {
            Config c = new Config();
            Connection con = c.getcon();
            Statement st = con.createStatement();
            String query="update result"+scode+" set q"+q_id+" = '"+ans+"' where username='"+user+"'";
            st.executeUpdate(query);
            con.close();
            response.sendRedirect("showpaper.jsp");
        }
        catch(Exception e)
        {
            System.out.println("Error="+e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

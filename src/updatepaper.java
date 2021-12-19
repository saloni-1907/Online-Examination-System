

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

@WebServlet(name = "updatepaper", urlPatterns = {"/updatepaper"})
public class updatepaper extends HttpServlet {
HttpSession session;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String scode=session.getAttribute("sbcode").toString();
        String qno=request.getParameter("qno");
        String qname=request.getParameter("ques");
        String opt1=request.getParameter("op1");
        String opt2=request.getParameter("op2");
        String opt3=request.getParameter("op3");
        String opt4=request.getParameter("op4");
        String ans=request.getParameter("ans");
        ResultSet rs;
        try
        {
            Config c = new Config();
            Connection con = c.getcon();
            Statement st = con.createStatement();
            String query="UPDATE "+scode+" SET qname='"+qname+"', opt1='"+opt1+"', opt2='"+opt2+"', opt3='"+opt3+"', opt4='"+opt4+"', ans='"+ans+"' WHERE qno="+qno+"";
            st.executeUpdate(query);
            con.close();
            response.sendRedirect("facultyprofile.jsp");
            
        }
        catch(Exception e)
        {
            out.println("Error="+e);
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
        session = request.getSession();
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

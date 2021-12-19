

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

@WebServlet(name = "mkpaper", urlPatterns = {"/mkpaper"})
public class mkpaper extends HttpServlet {
    HttpSession session;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String scode=session.getAttribute("subcode").toString();
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
            String query="insert into " + scode + " values("+qno+",'"+qname+"','"+opt1+"','"+opt2+"','"+opt3+"','"+opt4+"','"+ans+"')";
       
            st.executeUpdate(query);

            query= "ALTER TABLE result"+scode+" ADD q"+qno+" varchar(300)";
        
            
            st.executeUpdate(query);
            con.close();
            response.sendRedirect("makepaper.jsp?Success=True&scode="+scode );
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

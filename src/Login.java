//Log in check

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;

public class Login extends HttpServlet
{

public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException, ServletException
{
PrintWriter out=res.getWriter();
Connection con=null;
Statement s=null;
ResultSet rs=null;
ResultSet rs2=null;
boolean check=true;

try{
Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/irides","root"," ");
s=con.createStatement();
try{
String sap=req.getParameter("user");
rs=s.executeQuery("select * from participant");
if(rs.next()){
if(rs.getObject("sap").toString().equals(sap))
{
if(rs.getObject("passwd").toString().equals(req.getParameter("passwd"))){
HttpSession ss=req.getSession();
ss.setAttribute("sap",sap);
ss.setAttribute("ctr",0);
res.sendRedirect("http://202.177.231.177:8080/irides/User");
}
else{
res.sendRedirect("http://202.177.231.177:8080/irides/incorrectPass.html");
}
}
}
else{
res.sendRedirect("http://202.177.231.177:8080/irides/incorrectPass.html");
}
}
catch(Exception e)
{
out.println(e);
out.println("<html><body>A problem was encountered due to which the operation could not be completed. Please go back to the login page and try again.</body></html>");
}
finally{
rs2.close();
rs.close();
s.close();
con.close();
}
}
catch(Exception e)
{
out.println("Your username is incorrect. Please go back and try again");
}
}

public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException, ServletException
{
doGet(req,res);
}

}
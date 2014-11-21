//Logout procedure

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Logout extends HttpServlet
{
public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
HttpSession ss=req.getSession();
ss.invalidate();
res.sendRedirect("http://202.177.231.177:8080/irides/fl.html");
}
public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
doGet(req,res);
}
}
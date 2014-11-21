//Player filter

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class PFilter extends HttpServlet
{
public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
PrintWriter out=res.getWriter();
HttpSession ss=req.getSession();
String p=ss.getAttribute("player").toString();
String t=ss.getAttribute("column").toString();
String sap=ss.getAttribute("sap").toString();
Connection con;
Statement s,s2;
ResultSet rs,rs2;
try
{
Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/irides","root"," ");
s=con.createStatement();
s2=con.createStatement();
rs=s.executeQuery("select * from players where pname='"+p+"' and sport='"+t+"'");
rs.next();
rs2=s2.executeQuery("select * from players p where sport='"+rs.getObject("sport").toString()+"' and position='"+rs.getObject("position").toString()+"' and pname!='"+p+"' and not exists (select * from team t where sap='"+sap+"' AND (p.pname = t.player1 OR p.pname = t.player2 OR p.pname = t.player3 OR p.pname = t.player4 OR p.pname = t.player5 OR p.pname = t.player6 OR p.pname = t.player7 OR p.pname = t.player8)) order by price desc;");
out.print("<html><body bgcolor=\"white\"><div class=\"puc\"><table align =\"center\" border=\"1\" width=\"1024px\"><th>Player Name</th><th>Year</th><th>Department</th><th>Position</th><th>Price</th><th>Points in last round</th><th></th>");

while(rs2.next())
{
if(rs2.getObject("sport").toString().equalsIgnoreCase("chess"))
out.print("<tr align =\"center\"><td></td><td></td><td>"+rs2.getObject("dept").toString()+"</td><td>"+rs2.getObject("price").toString()+"</td><td>"+rs2.getObject("points").toString()+"</td><td><form action=\"http://202.177.231.177:8080/irides/PChange\" method=POST><input type=submit value=\"SELECT\" name=\""+rs2.getObject("pname").toString()+"\"></form></td></tr>");
else if(rs2.getObject("sport").toString().equalsIgnoreCase("throwball"))
out.print("<tr align =\"center\"><td></td><td></td><td>"+rs2.getObject("dept").toString()+"</td><td>"+rs2.getObject("price").toString()+"</td><td>"+rs2.getObject("points").toString()+"</td><td><form action=\"http://202.177.231.177:8080/irides/PChange\" method=POST><input type=submit value=\"SELECT\" name=\""+rs2.getObject("pname").toString()+"\"></form></td></tr>");
else if(rs2.getObject("sport").toString().equalsIgnoreCase("cricket"))
out.print("<tr align =\"center\"><td></td><td>"+rs2.getObject("year").toString()+"</td><td>"+rs2.getObject("dept").toString()+"</td><td></td><td>"+rs2.getObject("price").toString()+"</td><td>"+rs2.getObject("points").toString()+"</td><td><form action=\"http://202.177.231.177:8080/irides/PChange\" method=POST><input type=submit value=\"SELECT\" name=\""+rs2.getObject("pname").toString()+"\"></form></td></tr>");
else if(rs2.getObject("sport").toString().equalsIgnoreCase("tt"))
out.print("<tr align =\"center\"><td></td><td>"+rs2.getObject("year").toString()+"</td><td>"+rs2.getObject("dept").toString()+"</td><td></td><td>"+rs2.getObject("price").toString()+"</td><td>"+rs2.getObject("points").toString()+"</td><td><form action=\"http://202.177.231.177:8080/irides/PChange\" method=POST><input type=submit value=\"SELECT\" name=\""+rs2.getObject("pname").toString()+"\"></form></td></tr>");
else
out.print("<tr align =\"center\"><td>"+rs2.getObject("pname").toString()+"</td><td>"+rs2.getObject("year").toString()+"</td><td>"+rs2.getObject("dept").toString()+"</td><td>"+rs2.getObject("position").toString()+"</td><td>"+rs2.getObject("price").toString()+"</td><td>"+rs2.getObject("points").toString()+"</td><td><form action=\"http://202.177.231.177:8080/irides/PChange\" method=POST><input type=submit value=\"SELECT\" name=\""+rs2.getObject("pname").toString()+"\"></form></td></tr>");
}
out.print("</table><br></body></html>");
rs.close();rs2.close();
s.close();s2.close();
con.close();
}
catch(Exception e)
{
out.println(e);
e.printStackTrace();
}
}
public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
doGet(req,res);
}
}
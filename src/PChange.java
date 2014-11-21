//Update database to change player

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class PChange extends HttpServlet
{
public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
PrintWriter out=res.getWriter();
Enumeration name=req.getParameterNames();
String p=name.nextElement().toString();
HttpSession ss=req.getSession();
String sap=ss.getAttribute("sap").toString();
float bal=Float.parseFloat(ss.getAttribute("bal").toString());
float price=Float.parseFloat(ss.getAttribute("price").toString()); //From the player filter
bal+=price;
String col=ss.getAttribute("column").toString();
Connection con;
Statement s,st;
ResultSet rs;

try
{
Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/irides","root"," ");
s=con.createStatement();
st=con.createStatement();
rs=s.executeQuery("select price from players where pname='"+p+"' and sport='"+col+"'");
rs.next();
float pr=Float.parseFloat(rs.getObject("price").toString());
out.println(pr);
String plfoot=ss.getAttribute("plfoot").toString();
out.println(col);
if(bal>=pr)
			{
				if(plfoot.indexOf("player")!=-1)
				{
				int result=st.executeUpdate("update team set "+plfoot+"='"+p+"' where sap='"+sap+"'");
				bal-=pr;
				int res2=st.executeUpdate("update participant set balance="+bal+" where sap='"+sap+"'");
				ss.setAttribute("bal",bal);
				}
				else if(col.indexOf("cricket")!=-1)
				{
				int result=st.executeUpdate("update team set cyear='"+p.substring(0,2)+"',cdept='"+p.substring(p.indexOf("-")+1)+"' where sap='"+sap+"'");
				bal-=pr;
				int res2=st.executeUpdate("update participant set balance="+bal+" where sap='"+sap+"'");
				ss.setAttribute("bal",bal);
				}
				else if(col.indexOf("chess")!=-1)
				{
				int result=st.executeUpdate("update team set chessyear='"+p.substring(0,1)+"',chessdept='"+p.substring(p.indexOf("-")+2)+"' where sap='"+sap+"'");
				bal-=pr;
				int res2=st.executeUpdate("update participant set balance="+bal+" where sap='"+sap+"'");
				ss.setAttribute("bal",bal);
				}
				else if(col.indexOf("tt")!=-1)
				{
				int result=st.executeUpdate("update team set ttyear='"+p.substring(0,2)+"',ttdept='"+p.substring(p.indexOf("-")+1)+"' where sap='"+sap+"'");
				bal-=pr;
				int res2=st.executeUpdate("update participant set balance="+bal+" where sap='"+sap+"'");
				ss.setAttribute("bal",bal);
				}
				else if(col.indexOf("throwball")!=-1)
				{
				int result=st.executeUpdate("update team set tyear='"+p.substring(0,1)+"',tdept='"+p.substring(p.indexOf("-")+2)+"' where sap='"+sap+"'");
				bal-=pr;
				int res2=st.executeUpdate("update participant set balance="+bal+" where sap='"+sap+"'");
				ss.setAttribute("bal",bal);
				}
				res.sendRedirect("http://202.177.231.177:8080/irides/User");
			}
			else
			{
				ss.setAttribute("check","false");
				st.close();
				con.close();				
				res.sendRedirect("http://202.177.231.177:8080/irides/User");
			}
			st.close();
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
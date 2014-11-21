import java.util.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

public class TeamServlet extends HttpServlet
{
	Connection conn;
	Statement st;
	ResultSet rs=null;
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
	{
		HttpSession s=request.getSession();
		String sap=s.getAttribute("sap").toString();
		float bal=Float.parseFloat(s.getAttribute("bal").toString());
		int ctr=Integer.parseInt(s.getAttribute("ctr").toString());
		ctr++;
		s.setAttribute("ctr",ctr);
		s.setAttribute("check","true");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Enumeration params=request.getParameterNames();
		//String[] t=new String[16];
		//int i=0;
		String t="",q="";
		//while(params.hasMoreElements())
		//{
			String p=params.nextElement().toString();
			if(p.indexOf("football")!=-1)
			{
			q=p.substring(p.lastIndexOf(" ")+1);
			t=p.substring(p.indexOf("football"),p.indexOf("football")+8);
			p=p.substring(0,p.indexOf("football")-1);
			//t[i]=request.getParameter(p);
			//t=request.getParameter(p).toString();
			//i++;
		//}
		s.setAttribute("player",p);
		s.setAttribute("column",t);
		s.setAttribute("plfoot",q);
		}
		else{
		t=p.substring(p.lastIndexOf(" ")+1);
		p=p.substring(0,p.lastIndexOf(" "));
		s.setAttribute("player",p);
		s.setAttribute("column",t);
		s.setAttribute("plfoot",q);
		}
		
//		out.print("<script>window.open(\"http://202.177.231.177:8080/irides/PFilter\")</script>");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/irides","root"," ");
			st=conn.createStatement();
			rs=st.executeQuery("select price from players where pname='"+p+"'"); 
			rs.next();
			s.setAttribute("price",rs.getObject("price").toString());
			response.sendRedirect("http://202.177.231.177:8080/irides/PFilter");
			/*bal+=Integer.parseInt(rs.getObject("price").toString());
			if(bal>price)
			{
				int res=st.executeUpdate("update team set "+p+"='"+t+"' where sap='"+sap+"'");
				bal-=price;
				int res2=st.executeUpdate("update participant set balance="+bal+" where sap='"+sap+"'");
				s.setAttribute("bal",bal);
			}
			else
			{
				s.setAttribute("check","false");
				st.close();
				conn.close();				
				response.sendRedirect("http://202.177.231.177:8080/irides/User");
			}*/
			rs.close();
			st.close();
			conn.close();
		}
		catch(Exception e)
		{
		out.println(e);
		e.printStackTrace();
		}
		
		//response.sendRedirect("http://202.177.231.177:8080/irides/User");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
	{
		doGet(request, response);
	}
}
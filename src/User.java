//User page

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class User extends HttpServlet{
public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
java.util.Date d=new java.util.Date();
java.util.Date r1,r2,r22,r3,r33,r4,r44;
Calendar cal=Calendar.getInstance();
cal.set(2013,0,24,23,59);//round 1
r1=cal.getTime();
cal.set(2013,1,6,23,59);//round 2
r2=cal.getTime();
cal.set(2013,1,7,23,59);
r22=cal.getTime();
cal.set(2013,1,12,23,59);//round 3
r3=cal.getTime();
cal.set(2013,1,13,23,59);
r33=cal.getTime();
cal.set(2013,1,15,23,59);//round 4
r4=cal.getTime();
cal.set(2013,1,16,23,59);
r44=cal.getTime();
PrintWriter out=res.getWriter();
Connection con;
Statement s2,s3,s4,s5;
ResultSet rs2=null,rs3=null,rs4=null,rs5=null;
boolean chk=true;

try{
Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/irides","root"," ");
s2=con.createStatement();
s3=con.createStatement();
s4=con.createStatement();
s5=con.createStatement();
try{
HttpSession ss=req.getSession();
String sap=ss.getAttribute("sap").toString();
int ctr=Integer.parseInt(ss.getAttribute("ctr").toString());
rs2=s2.executeQuery("select * from team where sap="+sap);
rs2.next();
//Points and balance
rs5=s5.executeQuery("select points,balance from participant where sap="+sap);
rs5.next();
ss.setAttribute("bal",rs5.getObject("balance").toString());
//Display Page
out.print("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><script type=\"text/javascript\" src=\"lightbox.js\"></script><link rel=\"stylesheet\" type=\"text/css\" href=\"fl.css\"/>");
out.print("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /><title>Iridescence.in/fantasyleague</title></head><body><div id=\"back\"><div id=\"page\"><div class=\"iridescence\"><a href=\"iridescence.html\">Home</a></div><div class=\"events\"><a href=\"events.html\">Events</a></div><div class=\"fl\"><a href=\"fl.html\">Iridescence Cup</a></div>");
out.print("<div class=\"media\"><a href=\"media.html\">Media</a></div><div class=\"about\"><a href=\"about.html\">About</a></div><div class=\"contact\"><a href=\"contact.html\">Contact Us</a></div></div><div class=\"puc1\"><table border=\"0\"><tr><th>Total Points</th><td width=\"30px\"></td><td>"+rs5.getObject("points").toString()+"</td>");
out.print("<td width=\"30px\"></td><td rowspan=\"2\"><form action=\"http://202.177.231.177:8080/irides/Logout\" method=POST><input type=submit value=\"Logout\"></form></td></tr><tr><th>Account Balance</th><td width=\"30px\"></td><td>"+rs5.getObject("balance").toString()+"</td><td width=\"30px\"></td></tr></table></div><div class=\"results\">");
if(Integer.parseInt(ss.getAttribute("ctr").toString())>0)
if(ss.getAttribute("check").toString()=="false")
out.print("<span style=\"color:red;\"><font size=\"4\" align=\"center\">You do not have enough balance to make this transfer.</font></span>");
out.print("<h1 align=center>My Fantasy League Team</h1><table border=\"0\" width=\"400px\"><tr align=\"Center\"><th >Player</th><th >Price</th><th>Points Fetched</th><th ></th></tr><tr align=\"left\"><th colspan=\"4\" align=\"center\"><br/>GoalKeeper</th></tr>");
rs4=s4.executeQuery("select pname,position,price,points,sport from players where pname='"+rs2.getObject("player1").toString()+"'");
rs4.next();
out.print("<tr align=\"center\"><td>"+rs4.getObject("pname").toString()+"</td><td>"+rs4.getObject("price").toString()+"</td><td>"+rs4.getObject("points").toString()+"</td>");
if(d.before(r1))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change1\" name=\""+rs2.getObject("player1").toString()+" football player1\">Change</button></form></td></tr>");
else if(d.before(r22)&&d.after(r2))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change1\" name=\""+rs2.getObject("player1").toString()+" football player1\">Change</button></form></td></tr>");
else if(d.before(r33)&&d.after(r3))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change1\" name=\""+rs2.getObject("player1").toString()+" football player1\">Change</button></form></td></tr>");
else if(d.before(r44)&&d.after(r4))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change1\" name=\""+rs2.getObject("player1").toString()+" football player1\">Change</button></form></td></tr>");
else{
chk=false;
out.print("</tr>");}
out.print("<tr align=\"left\"><th colspan=\"4\" align=\"center\"><br/>Defenders</th></tr>");
for(int i=2;i<=4;i++)
{
rs4=s4.executeQuery("select pname,position,price,points,sport from players where pname='"+rs2.getObject("player"+i).toString()+"'");
rs4.next();
out.print("<tr align=\"center\"><td>"+rs4.getObject("pname").toString()+"</td><td>"+rs4.getObject("price").toString()+"</td><td>"+rs4.getObject("points").toString()+"</td>");
if(d.before(r1))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change"+i+"\" name=\""+rs2.getObject("player"+i).toString()+" football player"+i+"\">Change</button></form></td></tr>");
else if(d.before(r22)&&d.after(r2))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change"+i+"\" name=\""+rs2.getObject("player"+i).toString()+" football player"+i+"\">Change</button></form></td></tr>");
else if(d.before(r33)&&d.after(r3))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change"+i+"\" name=\""+rs2.getObject("player"+i).toString()+" football player"+i+"\">Change</button></form></td></tr>");
else if(d.before(r44)&&d.after(r4))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change"+i+"\" name=\""+rs2.getObject("player"+i).toString()+" football player"+i+"\">Change</button></form></td></tr>");
else{
chk=false;
out.print("</tr>");}
}
out.print("<tr align=\"left\"><th colspan=\"4\" align=\"center\"><br/>Midfielders</th></tr>");
for(int i=5;i<=7;i++)
{
rs4=s4.executeQuery("select pname,position,price,points,sport from players where pname='"+rs2.getObject("player"+i).toString()+"'");
rs4.next();
out.print("<tr align=\"center\"><td>"+rs4.getObject("pname").toString()+"</td><td>"+rs4.getObject("price").toString()+"</td><td>"+rs4.getObject("points").toString()+"</td>");
if(d.before(r1))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change"+i+"\" name=\""+rs2.getObject("player"+i).toString()+" football player"+i+"\">Change</button></form></td></tr>");
else if(d.before(r22)&&d.after(r2))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change"+i+"\" name=\""+rs2.getObject("player"+i).toString()+" football player"+i+"\">Change</button></form></td></tr>");
else if(d.before(r33)&&d.after(r3))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change"+i+"\" name=\""+rs2.getObject("player"+i).toString()+" football player"+i+"\">Change</button></form></td></tr>");
else if(d.before(r44)&&d.after(r4))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change"+i+"\" name=\""+rs2.getObject("player"+i).toString()+" football player"+i+"\">Change</button></form></td></tr>");
else{
chk=false;
out.print("</tr>");}
}
out.print("<tr align=\"left\"><th colspan=\"4\" align=\"center\"><br/>Striker</th></tr>");
rs4=s4.executeQuery("select pname,position,price,points,sport from players where pname='"+rs2.getObject("player8").toString()+"'");
rs4.next();
out.print("<tr align=\"center\"><td>"+rs4.getObject("pname").toString()+"</td><td>"+rs4.getObject("price").toString()+"</td><td>"+rs4.getObject("points").toString()+"</td>");
if(d.before(r1))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change8\" name=\""+rs2.getObject("player8").toString()+" football player8\">Change</button></form></td></tr>");
else if(d.before(r22)&&d.after(r2))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change8\" name=\""+rs2.getObject("player8").toString()+" football player8\">Change</button></form></td></tr>");
else if(d.before(r33)&&d.after(r3))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change8\" name=\""+rs2.getObject("player8").toString()+" football player8\">Change</button></form></td></tr>");
else if(d.before(r44)&&d.after(r4))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change8\" name=\""+rs2.getObject("player8").toString()+" football player8\">Change</button></form></td></tr>");
else{
chk=false;
out.print("</tr>");}
out.print("<tr align=\"left\"><th colspan=\"4\" align=\"center\"><br/>Cricket Team</th></tr>");
rs4=s4.executeQuery("select dept,year,price,points,sport from players where sport='cricket' and dept='"+rs2.getObject("cdept")+"' and year='"+rs2.getObject("cyear")+"'");
rs4.next();
out.print("<tr align=\"center\"><td>"+rs4.getObject("year").toString()+" - "+rs4.getObject("dept").toString()+"</td><td>"+rs4.getObject("price").toString()+"</td><td>"+rs4.getObject("points").toString()+"</td>");
if(d.before(r1))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change9\" name=\""+rs2.getObject("cyear").toString()+"-"+rs2.getObject("cdept").toString()+" cricket\">Change</button></form></td></tr>");
else if(d.before(r22)&&d.after(r2))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change9\" name=\""+rs2.getObject("cyear").toString()+"-"+rs2.getObject("cdept").toString()+" cricket\">Change</button></form></td></tr>");
else if(d.before(r33)&&d.after(r3))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change9\" name=\""+rs2.getObject("cyear").toString()+"-"+rs2.getObject("cdept").toString()+" cricket\">Change</button></form></td></tr>");
else if(d.before(r44)&&d.after(r4))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change9\" name=\""+rs2.getObject("cyear").toString()+"-"+rs2.getObject("cdept").toString()+" cricket\">Change</button></form></td></tr>");
else{
chk=false;
out.print("</tr>");}
out.print("<tr align=\"left\"><th colspan=\"4\" align=\"center\"><br/>Chess Team</th></tr>");
rs4=s4.executeQuery("select dept,year,price,points,sport from players where sport='chess' and dept='"+rs2.getObject("chessdept")+"' and year='"+rs2.getObject("chessyear")+"'");
rs4.next();
out.print("<tr align=\"center\"><td>"+rs4.getObject("dept").toString()+"</td><td>"+rs4.getObject("price").toString()+"</td><td>"+rs4.getObject("points").toString()+"</td>");
if(d.before(r1))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change10\" name=\""+rs2.getObject("chessyear").toString()+"-"+rs2.getObject("chessdept").toString()+" chess\">Change</button></form></td></tr>");
else if(d.before(r22)&&d.after(r2))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change10\" name=\""+rs2.getObject("chessyear").toString()+"-"+rs2.getObject("chessdept").toString()+" chess\">Change</button></form></td></tr>");
else if(d.before(r33)&&d.after(r3))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change10\" name=\""+rs2.getObject("chessyear").toString()+"-"+rs2.getObject("chessdept").toString()+" chess\">Change</button></form></td></tr>");
else if(d.before(r44)&&d.after(r4))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change10\" name=\""+rs2.getObject("chessyear").toString()+"-"+rs2.getObject("chessdept").toString()+" chess\">Change</button></form></td></tr>");
else{
chk=false;
out.print("</tr>");}
rs4=s4.executeQuery("select dept,year,price,points,sport from players where sport='tt' and dept='"+rs2.getObject("ttdept")+"' and year='"+rs2.getObject("ttyear")+"'");
rs4.next();
out.print("<tr align=\"left\"><th colspan=\"4\" align=\"center\"><br/>Table Tennis Team</th></tr><tr align=\"center\"><td>"+rs4.getObject("year").toString()+" - "+rs4.getObject("dept").toString()+"</td><td>"+rs4.getObject("price").toString()+"</td><td>"+rs4.getObject("points").toString()+"</td>");
if(d.before(r1))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change11\" name=\""+rs2.getObject("ttyear").toString()+"-"+rs2.getObject("ttdept").toString()+" tt\">Change</button></form></td></tr>");
else if(d.before(r22)&&d.after(r2))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change11\" name=\""+rs2.getObject("ttyear").toString()+"-"+rs2.getObject("ttdept").toString()+" tt\">Change</button></form></td></tr>");
else if(d.before(r33)&&d.after(r3))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change11\" name=\""+rs2.getObject("ttyear").toString()+"-"+rs2.getObject("ttdept").toString()+" tt\">Change</button></form></td></tr>");
else if(d.before(r44)&&d.after(r4))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change11\" name=\""+rs2.getObject("ttyear").toString()+"-"+rs2.getObject("ttdept").toString()+" tt\">Change</button></form></td></tr>");
else{
chk=false;
out.print("</tr>");}
out.print("<tr align=\"left\"><th colspan=\"4\" align=\"center\"><br/>Throwball Team</th></tr>");
rs4=s4.executeQuery("select dept,year,price,points,sport from players where sport='throwball' and dept='"+rs2.getObject("tdept")+"' and year='"+rs2.getObject("tyear")+"'");
rs4.next();
out.print("<tr align=\"center\"><td>"+rs4.getObject("dept").toString()+"</td><td>"+rs4.getObject("price").toString()+"</td><td>"+rs4.getObject("points").toString()+"</td>");
if(d.before(r1))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change12\" name=\""+rs2.getObject("tyear").toString()+"-"+rs2.getObject("tdept").toString()+" throwball\">Change</button></form></td></tr>");
else if(d.before(r22)&&d.after(r2))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change12\" name=\""+rs2.getObject("tyear").toString()+"-"+rs2.getObject("tdept").toString()+" throwball\">Change</button></form></td></tr>");
else if(d.before(r33)&&d.after(r3))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change12\" name=\""+rs2.getObject("tyear").toString()+"-"+rs2.getObject("tdept").toString()+" throwball\">Change</button></form></td></tr>");
else if(d.before(r44)&&d.after(r4))
out.print("<td><form action=\"http://202.177.231.177:8080/irides/TeamServlet\" method=POST><button value=\"Change12\" name=\""+rs2.getObject("tyear").toString()+"-"+rs2.getObject("tdept").toString()+" throwball\">Change</button></form></td></tr>");
else{
chk=false;
out.print("</tr>");}
out.print("</table></div><div class=\"notice\"><h1 align=center>Notice</h1><marquee style=\"position: relative;\" behavior=\"scroll\" align=\"center\" direction=\"up\" scrollamount=\"2\" scrolldelay=\"5\" onmouseover=\"this.stop()\" onmouseout=\"this.start()\" height=\"225\"><center>You can only change your team on the following dates:<br><br>before "+r1.toString()+"<br><br>between "+r2.toString()+" and "+r22.toString()+"<br><br>between "+r3.toString()+" and "+r33.toString()+"<br><br>between "+r4.toString()+" and "+r44.toString()+"</center></marquee></div>");
out.print("<div class=\"leaderboard\"><h1 align=center>FL Leaderboard</h1><table border=\"0\" width=\"250px\" height=\"200px\"><tr align=\"center\"><th align=\"center\">Pos.</th><th>Name</th><th>Department</th><th>Points</th></tr>");
rs3=s3.executeQuery("select pname,dept,points from participant order by points desc limit 1,10");
rs3.next();
for(int i=1;i<=10;i++)
{
if(i==1)
out.print("<tr bgcolor=\"#99FF99\"><td align=\"center\">"+i+"</td><td>"+rs3.getObject("pname").toString()+"</td><td>"+rs3.getObject("dept").toString()+"</td><td>"+rs3.getObject("points").toString()+"</td></tr>");
else
out.print("<tr bgcolor=\"#FFFF99\"><td align=\"center\">"+i+"</td><td>"+rs3.getObject("pname").toString()+"</td><td>"+rs3.getObject("dept").toString()+"</td><td>"+rs3.getObject("points").toString()+"</td></tr>");
rs3.next();
}
out.print("</table></div><div class=\"draws\"><h1 align=center>Draws</h1><table border=\"0\" width=\"550px\" height=\"300px\"><tr><td width=\"110px\"></td><td width=\"110px\"><a href=\"fbdraw.jpg\" rel=\"lightbox\">1. Football</a></td><td width=\"110px\"><a href=\"cricketdraw.jpg\" rel=\"lightbox\">2. Cricket</a></td><td width=\"110px\"><a href=\"ttdraw.jpg\" rel=\"lightbox\">3. Table Tennis</a></td>");
out.print("<td width=\"110px\"></td></tr><tr><td width=\"110px\"></td><td width=\"110px\"><a href=\"badiboysdraw.jpg\" rel=\"lightbox\">4. Badminton (boys)</a></td><td width=\"110px\"><a href=\"badigirlsdraw.jpg\" rel=\"lightbox\">5. Badminton (girls)</a></td><td width=\"110px\"><a href=\"tbdraw.jpg\" rel=\"lightbox\">6. Throwball (girls)</a></td><td width=\"110px\"></td>");
out.print("</tr><tr><td width=\"110px\"></td><td width=\"110px\"><a href=\"vbdraw.jpg\" rel=\"lightbox\">7. Volleyball</a></td><td width=\"110px\"><a href=\"chessdraw.jpg\" rel=\"lightbox\">8. Chess (mix)</a></td><td width=\"110px\"><a href=\"carromdraw.jpg\" rel=\"lightbox\">9. Carrom (mix)</a></td><td width=\"110px\"></td></tr><tr>");
out.print("<td width=\"110px\"></td><td width=\"110px\"><a href=\"bbboysdraw.jpg\" rel=\"lightbox\">10. Basketball (boys)</a></td><td width=\"110px\"><a href=\"bbgirlsdraw.jpg\" rel=\"lightbox\">11. Basketball (girls)</a></td><td width=\"110px\"><a href=\"bcricketdraw.jpg\" rel=\"lightbox\">12. Box Cricket</a></td><td width=\"110px\"></td></tr></table>");
out.print("</div></div><div id=\"footer\"><a href=\"http://www.djscoe.org/\">Web Development by AD, SA and BD</a></div></body></html>");
rs2.close();
rs3.close();
rs4.close();
rs5.close();
s2.close();
s3.close();
s4.close();
s5.close();
con.close();
}catch(Exception e){out.println("INSIDE: "+e);}
finally{
rs2.close();
rs3.close();rs4.close();rs5.close();
s2.close();s3.close();s4.close();s5.close();
con.close();
}
}
catch(Exception e){
out.println("OUTSIDE: "+e);
}
}
public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
doGet(req,res);
}
}
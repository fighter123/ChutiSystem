package com.lhp.servlet;

import com.lhp.unit.Return;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
public class Search extends HttpServlet
{
	private static final long serialVersionUID = -7750830536397709752L;
	public Search()//构造函数
	{
		super();
	}

	public void doGet(HttpServletRequest request , HttpServletResponse response )
			throws ServletException , IOException
	{
	
		doPost(request, response);
	}
	
	
	public void doPost(HttpServletRequest request , HttpServletResponse response )
			throws ServletException , IOException
	{
	
		
		response.setCharacterEncoding("UTF-8");
		String tx=request.getParameter("tx");
		String zj=request.getParameter("zj");
		String id=request.getParameter("id");
		//System.out.println(tx+zj+id);
		int id2=Integer.parseInt(id);
		int id3=id2+1;
		System.out.println(id3);
		HttpSession session=request.getSession();
		System.out.println("XXXXXXXXX"+tx+zj);
		String sql="select *  from  "+tx+"  where deffnum="+zj+" and id='"+id3+"'  ";
		System.out.println(sql);
		String timu="暂无数据";
		com.lhp.unit.DBBean db = new com.lhp.unit.DBBean();
		ResultSet rs = db.executeQuery(sql);
		try 
		{
			if(rs.next())
			{
					timu=rs.getString("name");
					//session.setAttribute("id", rs.getInt("id"));
					
					
			}
			
		} 		
		catch (SQLException e) {
			e.printStackTrace();
		}
		/*StringBuilder  result =new StringBuilder();
		//拼接json字符串（其中"\"为转移字符）：
		result.append("{");
		result.append("\"timu\":\""+timu+"\"");
		result.append(",");
		result.append("\"id\":\""+id3+"\"");
		result.append("}");
		System.out.println(result.toString());*/
		
		Return result = new Return(timu, id3);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(result));
		
		response.setContentType("text/javascript");
		response.getWriter().print(mapper.writeValueAsString(result));
		db.close();
	}
}
	


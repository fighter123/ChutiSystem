package com.lhp.servlet;

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

import com.sun.org.apache.commons.digester.rss.Image;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;

public class Check extends HttpServlet
{

	private static final long serialVersionUID = 6662372955453450455L;

	public void doGet(HttpServletRequest request , HttpServletResponse response )
			throws ServletException , IOException
	{	

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String tx=request.getParameter("tx");
		String answer=request.getParameter("answer");
		String id=request.getParameter("id");
		System.out.println("XXXXXXXXX"+tx+answer);
		System.out.println("用户输入:"+answer);
		String sql="select *  from  "+tx+" where id="+id+"";
		System.out.println(sql);
		String code="没有题目，无法判断结果";
		String rightanswer=null;
		com.lhp.unit.DBBean db = new com.lhp.unit.DBBean();
		ResultSet rs = db.executeQuery(sql);
		try 
		{
			while(rs.next())
			{
					//code=rs.getString("name");
					rightanswer=rs.getString("answer");		
					System.out.println(rightanswer);
					if(answer.trim().equals(rightanswer.trim()))
					{
						code="恭喜你答对了！";
					}
					else 
					{
						code="很遗憾，答案错误!正确答案为"+rightanswer+",";
					}
			}
			
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().print(code);
		db.close();
	
	}
	
	public void doPost(HttpServletRequest request , HttpServletResponse response )
			throws ServletException , IOException
	{
		doGet(request, response);
	}
	
}

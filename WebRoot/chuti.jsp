<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<jsp:useBean id="database" class="com.lhp.unit.DBBean" scope="page" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%!String zhangjie=null;
   String tixing=null; 
   String BiaozhunAnswer=null;
   String userAnswer=null;
   String Timu=null;
   String id=null;
   
%>
<%
    	request.setCharacterEncoding("utf-8");
    	zhangjie=request.getParameter("zhangjie");
    	tixing=request.getParameter("tixing");

    	try{
    			//System.out.println(tixing);
    			String sql="select *  from  "+tixing+"  where  deffnum="+zhangjie+" and id=1";
        		ResultSet rs=database.executeQuery(sql);
        		if(rs.next())
        			{
        				Timu=rs.getString("name");
        				id=rs.getString("id");
        			}
        			else{Timu="题库里已经没有没做过的题目了，同学再试试别的类型的题目吧。";
        			}
        	}
        catch(Exception e)
        	{
        		System.out.println(e.getMessage());
        	}
    	
        
     %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>出题页</title>
   <link rel="stylesheet" type="text/css" href="button2.css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.7.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.js"></script>
	<script type="text/javascript">
		$(function(){
			 $('#next').click(function (){
			 	var zj=document.getElementById("zj").value;
				var tx=document.getElementById("tx").value;
				var id=document.getElementById("id").value;
				document.getElementById('answer').value =null;	
			 	var url="com.lhp.servlet.Search";
			 	var datas={"zj":zj,"tx":tx,"id":id};
			 	//alert(url);
			 	$.getJSON(url,datas,function(data){
			 		document.getElementById('textti').value =data.timu;		
			 		document.getElementById('id').value =data.id;	
			 		//alert(data.id);		 		 		
			 	});
			 	return false;
			 });
		})
	/* jQuery(function($){
	 var zj=document.getElementById("zj").value;
	 var tx=document.getElementById("tx").value;
	 var id=document.getElementById("id").value;
	$('#next').click(function ()
		{
			$.ajax({ 
	 			 type:"POST",
	   			 url:"com.lhp.servlet.Search",
	   			 async:false,
	   			 dataType: "text",
	   			 scriptCharset:'UTF-8',
	   			 data:{"zj":zj,"tx":tx,"id":id},
	   			 success:function(name)
	   				 { 
	    				 document.getElementById('textti').value =name;
	    			 } 
		 }
		); 
	   });
	}); */	 
	</script>
  <script type="text/javascript">
	jQuery(function($){
	 //var answer=document.getElementById("answer").value;
	 //$("#answer").text().val();
	$('#submit').click(function ()
		{	
			var tixing=document.getElementById("tx").value;
			var id=document.getElementById("id").value;
			var answer=document.getElementById("answer").value;
			if(answer=="")
			{
				alert("请输入答案后再进行提交！");
				return;
			}
			$.ajax({ 
	 			 type:"POST",
	   			 url:"com.lhp.servlet.Check",
	   			 async:false,
	   			 dataType: "text",
	   			 scriptCharset:'UTF-8',
	   			 data:{"answer":answer,"tx":tixing,"id":id},
	   			 success:function(result)
	   				 { 
	    				 //$("#box").text(result);
	    				 alert(result+"点击确认继续！");
	    			 } 
		 }
		); 
	   });
	});
	   
	</script>
  </head>
  <body>
  <input type="hidden" id="zj" value=<%=zhangjie%>>
  <input type="hidden" id="tx" value=<%=tixing%>>
  <input type="hidden" id="id" name="id" value=<%=id%>>
  <p><h3>题目</h3>
  <table width="95%" height="150"  border="1" align="center">
    <tr>
      <td height="150" width="100%"><div align="center"><textarea  id="textti" cols="50" rows="4"
       class="comments" style=height:150px readonly="readonly"><%=Timu%></textarea> </div></td>
    </tr>
    </table>
    <p> <h3>请您在该区域内作答：</h3>
    <table width="95%" height="150"  border="1" align="center">
    <tr>
      <td height="100" width="100%"><div align="center"><textarea  id="answer" name="answer" cols="50" rows="4"
       class="comments" style=height:150px></textarea> </div></td>
    </tr>
    <tr>
    <td>
    <button name="submit" id="submit" class="button button-raised  button-royal" >确认并提交</button>
     <button name="next" id="next" class="button button-raised" >下一道题</button>
   </td> </tr>
    
    </table>
   <div id="box">  
    </div>
  </body>
</html>

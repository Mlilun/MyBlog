<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'artile_view.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link media="all" rel="stylesheet" type="text/css" href="bootstrap/styles/bootstrap.css" />
	<link media="all" rel="stylesheet" type="text/css" href="bootstrap/styles/jumbotron-narrow.css" />
	<link media="all" rel="stylesheet" type="text/css" href="simditor-2.0.1/styles/simditor.css" />

	<!-- 语法高亮 -->
		<script type="text/javascript" src="syntaxhighlighter/scripts/shCore.js"></script>
		<script type="text/javascript" src="syntaxhighlighter/scripts/shBrushBash.js"></script>
		<script type="text/javascript" src="syntaxhighlighter/scripts/shBrushCss.js"></script>
		<script type="text/javascript" src="syntaxhighlighter/scripts/shBrushCSharp.js"></script>
		<script type="text/javascript" src="syntaxhighlighter/scripts/shBrushJScript.js"></script>
		<script type="text/javascript" src="syntaxhighlighter/scripts/shBrushPhp.js"></script>
		<script type="text/javascript" src="syntaxhighlighter/scripts/shBrushPlain.js"></script>
		<script type="text/javascript" src="syntaxhighlighter/scripts/shBrushPython.js"></script>
		<script type="text/javascript" src="syntaxhighlighter/scripts/shBrushJava.js"></script>
		<script type="text/javascript" src="syntaxhighlighter/scripts/shBrushScala.js"></script>
		<script type="text/javascript" src="syntaxhighlighter/scripts/shBrushSql.js"></script>
		<script type="text/javascript" src="syntaxhighlighter/scripts/shBrushXml.js"></script>
		<script type="text/javascript" src="syntaxhighlighter/scripts/shBrushPerl.js"></script>
		<link type="text/css" rel="stylesheet" href="syntaxhighlighter/styles/shCore.css" />
		<link type="text/css" rel="stylesheet" href="syntaxhighlighter/styles/shThemeDefault.css" />
		<script type="text/javascript">
				SyntaxHighlighter.defaults['toolbar'] = false;  //去掉右上角问号图标
				SyntaxHighlighter.config.tagName = 'pre';       //可以更改解析的默认Tag。
				SyntaxHighlighter.config.bloggerMode = true; 
				SyntaxHighlighter.config.stripBrs = true;  
				SyntaxHighlighter.all();
		</script>
  </head>
  
  <body>
	<div class="container">
		<div class="header">
			<h3 class="text-muted">${blog.title}</h3>
		</div>
			
		${blog.content}
	</div>
</body>
</html>

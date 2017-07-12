<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- 


Yashar Rahvar A00871798
COMP 4613 Assignment1
error pages (used with index.jsp). 
  
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<%@ page isErrorPage="true"%>
<TITLE>Error Query</TITLE>
<LINK REL=STYLESHEET HREF="mainstyle.css" TYPE="text/css">
</HEAD>

<BODY>
<h1>Opps...</h1>
<table width="100%" border="1">
<tr>
<td width="40%"><b>Error:</b></td>
<td>${exception}</td>
</tr>
</table>
</body>
</html>
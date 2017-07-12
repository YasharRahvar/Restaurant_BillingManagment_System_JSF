<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<LINK REL=STYLESHEET
      HREF="resources/css/styles.css"
      TYPE="text/css">
</HEAD>

<BODY>

<jsp:include page="/WEB-INF/jsp/header.jsp" flush="true" />

<div id="mainDoc">
	<h3 align="left">Please login to use the application.</h3>
  <form action="j_security_check" method="POST">
    <table style="width:25%" >
    

	  <tr>
        <td width="40%">Username: </td>
        <td width="40%">
          <input name="j_username" type="text" value=""></input>
        </td>
      </tr>

      <tr>
        <td >Password: </td>
        <td>
          <input name="j_password" type="password" value=""></input>
        </td>
      </tr>
    </table>
	<input name="login" type="submit" value="Login"></input>
  </form>

</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" flush="true" />
</BODY>
</HTML>

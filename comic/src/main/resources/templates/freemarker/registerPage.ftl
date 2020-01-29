<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>注册页面</title>
<style type="text/css">
<!--
body {
	background-image: url(static/image/zhuce.png);
	
}

}
.STYLE13 {font-weight: bold; font-size: xx-large;}

.STYLE21 {font-size:  large; font-weight: bold; color: #000000; }
.STYLE22 {color: #CCCCCC; 
          font-size: xx-large;
}
.STYLE30 {
	color: #000000;
	font-size: x-large;
}
.STYLE35 {font-size: x-large}



-->
</style>
 <body >
 
<table width="1309" border="0"  align="center" bgcolor="#990000"  >
  
<td width="208"   ><div align="center" class="STYLE13 STYLE14">
  <div align="left" class="STYLE22" >爱动漫</div>
</div></td>

</table>
<table width="725" height="517" border="0">
      <tr>
        <td width="704" height="513" align="center">
        <form id="form1" name="form1" method="post" action="/user/register">
          <table width="499" height="188" border="0">
            <tr>
              <td colspan="2" align="center"  class="STYLE30">用户注册 </td>
            </tr>
            <tr>
              <td width="138"><span class="STYLE35">用户名</span></td>
              <td align="center" width="428"><label for="textfield"></label>
                <input type="text" name="logname" class="STYLE21" id="textfield" /></td>
       </tr>
            <tr>
              <td width="138"><span class="STYLE35">密码</span></td>
              <td align="center" width="428"><label for="textfield"></label>
                <input type="text" name="password" class="STYLE21" id="textfield" /></td>
            </tr>
            <tr>
              <td width="138" height="41"><span class="STYLE35">确认密码</span></td>
              <td align="center" width="428"><label for="textfield"></label>
                <input type="text" name="comfirmPassword" class="STYLE21" id="textfield" /></td>
            </tr>
            <tr>
              <td height="50" colspan="2" align="center"><input name="button" type="submit" class="STYLE35" id="button" value="提交" /></td>
            </tr>
          </table>
        </form>
        </td>
      </tr>
      
      <h1>code: ${errorCode}</h1>
      <h1>msg: ${errorMsg}</h1>

 </table>
 
 </body>
</html>

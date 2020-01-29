<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<style type="text/css">
<!--
body {
	background-image: url(static/image/zhuce.png);
	
}


}
.STYLE13 {font-weight: bold; font-size: xx-large;}
.STYLE22 {color: #CCCCCC ;
          font-size: xx-large;
 }
.STYLE34 {font-size: x-large}
.STYLE21 {font-size: large; font-weight: bold; color: #000000; }
.STYLE37 {
	color: #999999;
	font-size: x-large;
}
.STYLE38 {color: #000000}


-->
</style>

</head>
<body>
<table width="1309" border="0"  align="center" bgcolor="#990000"  >
  
<td width="208"   ><div align="center" class="STYLE13 STYLE14">
  <div align="left" class="STYLE22" >爱动漫</div>
</div></td>

<h1>code: ${errorCode}</h1>
<h1>msg: ${errorMsg}</h1>
      
      
</table>
<table width="725" height="517" border="0">
  
      <tr>
        <td width="704" height="513" align="center">
        <form id="form1" name="form1" method="post" action="/user/login">
          <table width="431" height="188" border="0">
            <tr>
              <td colspan="2" class="STYLE37"><div align="right" class="STYLE38">
                <div align="right"></div>
              </div></td>
            </tr>
            <tr>
              <td width="96"><span class="STYLE34">用户名</span></td>
              <td align="center" width="319"><label for="textfield"></label>
              <input name="logname" type="text" class="STYLE21" id="textfield" /></td>
       </tr>
            <tr>
              <td width="96"><span class="STYLE34">密码</span></td>
              <td align="center" width="319"><label for="textfield"></label>
              <input name="password" type="text" class="STYLE21" id="textfield2" /></td>
            </tr>
            <tr>
              <td height="50" colspan="2" align="center"><input name="button" type="submit" class="STYLE34" id="button" value="登录" /></td>
            </tr>
          </table>
        </form>        </td>
      </tr>
</table>

</p>
</body>
</html>

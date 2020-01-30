<HTML><Body><center>
<style type="text/css">
<!--


}

.STYLE13 {
font-weight: bold;
font-size: xx-large;
border-top-width: 4px;
border-right-width: 4px;
border-bottom-width: 4px;
border-left-width: 4px;
}

.STYLE21 {
font-size: x-large;
color: #000;
}
.STYLE22 {color: #CCCCCC}
.STYLE23 {font-size: 12px}
.STYLE25 {
color: #000000;
font-weight: bold;
font-size: 24px;
}
.STYLE29 {
color: #990033;
font-size: xx-large;
font-weight: bold;
}
.STYLE30 {color: #000000; font-size: x-large;}
.STYLE31 {
font-size: 36px;
}
.STYLE32 {color: #000000; font-size: x-large; font-weight: bold; }
.STYLE31 .STYLE31 strong {
font-size: 34px;
}
.mystyle {
border-top-style: none;
border-right-style: none;
border-bottom-style: none;
border-left-style: none;
text-align: center;
position: absolute;
}
.STYLE34 {
font-size: 24px;
}
.t1 {
border: 3px solid #CCC;
font-size: large;
}
.STYLE33 {
color: #000;
font-size: large;
font-weight: bold;
}

.STYLE35 {
font-size: x-large;
color: #999;
}
.STYLE100 {
	font-size: 24px;
}
.STYLE101 {
	font-size: 32px;
}




-->
</style>
<table width="1309" border="0" align="center" cellpadding="0" cellspacing="0" >
<td width="223" >
<p><img src="/static/image/icartoon.png" width="203" height="99" /></p>
</td>
<td width="517"> <img src="/static/image/J4F1DA40Y(8S)A@_36H1L`Q.png" width="25" height="23" /><span class="STYLE22"><a href="/index" class="STYLE25">首页</a></span></td>
<td width="310" ></td>

</tr>
</table>




<table width=1024 height=375 border=0 align=center>
    
    <#list comicList as cl>
      <tr>
         
         <img src="/static/image/${cl.picture}" alt="$$$$" height="372" align="top" />
         <p> ${cl.name} </p>
         <p> ${cl.message} </p>
         <p><a href="/collection/add?comicId=${cl.id}&comicName=${cl.name}&comicPic=${cl.picture}">收藏</a></p>
         
      </tr>
    </#list>
    
</table>


</div>


</Center>
</BODY></HTML>

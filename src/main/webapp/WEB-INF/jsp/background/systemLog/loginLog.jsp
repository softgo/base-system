<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8" http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@include file="../../common/taglib.jsp" %>
	<%@include file="../../common/common-css.jsp" %>
	<%@include file="../../common/common-js.jsp" %>

<style type="text/css">
  input{font-size: 12px}
</style>
</head>
<body>

<form id="fenye" name="fenye" action="<%=request.getContextPath()%>/background/systemLog/loginLog.html" method="post">
<div style="overflow-y:scroll;overflow-x:scroll; height:75%;">
<table width="100%" cellpadding="0" cellspacing="0" >
  <!-- 搜索页面展示 -->
  <tr>
     <td align="left" >
       <div id="search_bar" class="mt10">
       <div class="box">
          <div class="box_border">
          
          	<!-- 表头显示 -->
            <div class="box_top">
                <b>&nbsp;&nbsp;日志管理——>登录信息 </b>
            </div>
            <div class="box_center pt10 pb10">
              <!-- 查询条件添加 -->
              <table cellpadding="0" cellspacing="0">
                <tr>
                  <td>&nbsp;&nbsp;用户名</td>
                  <td>
                  	<input type="text" name="sysUserName" value="${param.userName}" class="input-text lh25"/>
                  </td>
                  <td>&nbsp;&nbsp;用户ID</td>
                  <td>
                  	<input type="text" name="sysUserId" value="${param.id}" class="input-text lh25"/>
                  </td>
                    <td>
	                    <div class="box_bottom pb5 pt5 pr10" style="border-top:0px solid #dadada;">
			              <div class="search_bar_btn" style="text-align:right;">
			                 <input type="submit" name="button" class="btn btn82 btn_search" value="查询">   
	                         <input type="button" name="button" class="btn btn82 btn_res" value="重置" onclick="clearText()">
			              </div>
			            </div>
                    </td>
                </tr>
              </table>
            </div>
          </div>
        </div>
        </div>
     </td>
  </tr>
  
  <!-- 主页面展示 -->
  <tr>
    <td>
        <div id="table" class="mt10">
            <div class="box span10 oh">
              <table class="list_table" width="100%" cellspacing="1" onmouseover="changeto()"  onmouseout="changeback()">
			          <tr>
			            <th >登陆ID</th>
			            <th >用户ID</th>
			            <th >用户名</th>
			            <th >登陆时间</th>
			            <th >登陆IP</th>
			          </tr>
			          
			          <c:forEach var="login" items="${pageView.data}">
				          <tr class="tr">
				            <td align="center" >${login.id}</td>
				            <td align="center" >${login.userId}</td>
				            <td align="center" >${login.userName}</td>
				            <td align="center" ><fmt:formatDate value="${login.createTime}" pattern="yyyy-MM-dd HH:MM:ss"/></td>
							<td align="center" >${login.fromIp}</td>
						 </tr>
			          </c:forEach>
              </table>
        </div>
      </div>
    </td>
  </tr>
   <!-- 分页 -->
   <tr>
    <td>
        <div id="table" class="mt10">
            <div class="box span10 oh">
              <%@include file="../../common/webfenye.jsp" %>
            </div>
      </div>
    </td>
  </tr>
</table>
</div>
</form>
</body>
</html>
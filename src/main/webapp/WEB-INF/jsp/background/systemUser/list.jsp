<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8" http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <%@include file="../../common/taglib.jsp" %>
    <%@include file="../../common/common-css.jsp" %>
    <%@include file="../../common/common-js.jsp" %>

<script type="text/javascript">

function userRole(userId){
     var url = "<%=request.getContextPath()%>/background/systemUser/userRole.html?userId="+userId;
     location.href=url;
}

</script>
</head>

<body>
<form id="fenye" name="fenye" action="<%=request.getContextPath()%>/background/systemUser/find.html" method="post">
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
                <b>&nbsp;&nbsp;系统管理——>用户列表 </b>
            </div>
            <div class="box_center pt10 pb10">
              <!-- 查询条件添加 -->
              <table cellpadding="0" cellspacing="0">
                <tr>
                  <td>&nbsp;&nbsp;用户名</td>
                  <td><input type="text" name="userName" value="${param.userName}" class="input-text lh25"/></td>
                    <td>
                        <div class="box_bottom pb5 pt5 pr10" style="border-top:0px solid #dadada;">
                          <div class="search_bar_btn" style="text-align:right;">
                             <input type="submit" name="button" class="btn btn82 btn_search" value="查询">  
                             <!-- 新加 -->
                             <sec:authorize ifAnyGranted="ROLE_system_user_add">
                                <input type="button" name="button" class="btn btn82 btn_add" value="新增" onclick="GOTO('<%=request.getContextPath()%>/background/systemUser/addUI.html')">
                             </sec:authorize>
                             <!-- 删除 -->
                             <sec:authorize ifAnyGranted="ROLE_system_user_delete">
                                <input type="button" name="button" class="btn btn82 btn_del" value="删除" onclick="return deleteAll()">
                             </sec:authorize>
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
                        <th >
                          <input id="chose" type="checkbox" name="checkbox" onclick="selectAllCheckBox()" />
                        </th>
                        <th >用户名</th>
                        <th >所属角色</th>
                        <th >手机号码</th>
                        <th >邮箱</th>
                        <th >QQ</th>
                        <th >基本操作</th>
                      </tr>
                      
                      <c:forEach var="key" items="${pageView.data}">
                           <tr class="tr">
                              <td align="center" hight="30px">
                                <input type="checkbox" name="check" value="${key.id}" />
                              </td>
                              <td align="center" ><a href="<%=request.getContextPath()%>/background/systemUser/findById.html?userId=${key.id}&&type=0">${key.userName}</a></td>
                              <td align="center" ><font color="blue">${key.roleName}</font></td>
                              <td align="center" >${key.userPhone}</td>
                              <td align="center" >${key.userMail}</td>
                              <td align="center" >${key.userQq}</td>
                              <td align="center" >
                              <!-- 显示判断 -->
                               <c:choose>
                                   <c:when test="${userSession.userName eq 'admin' && key.userName eq 'admin'}">
                                     <sec:authorize access="hasRole('ROLE_system_user_info')">
                                      <img src="<%=request.getContextPath()%>/images/admin.gif" width="10" height="10" />
                                      <a href="<%=request.getContextPath()%>/background/systemUser/findById.html?userId=${key.id}&type=0">详细信息</a>
                                     </sec:authorize>
                                     <sec:authorize access="hasRole('ROLE_system_user_edit')">
                                          <img src="<%=request.getContextPath()%>/images/edt.gif" width="12" height="12" />
                                          <a href="<%=request.getContextPath()%>/background/systemUser/findById.html?userId=${key.id}&type=1">编辑</a>
                                     </sec:authorize>
                                   </c:when>
                                   <c:otherwise>
                                     <c:if test="${userSession.parentId ne key.parentId }">
                                       <sec:authorize access="hasRole('ROLE_sytemuser_arrange_role')">
                                       <img src="<%=request.getContextPath()%>/images/role.png" width="12" height="12" />
                                       <a href="javascript:void(0);" onclick="userRole('${key.id}')">分配角色</a>
                                       </sec:authorize>
                                      <sec:authorize access="hasRole('ROLE_system_user_info')">
                                      <img src="<%=request.getContextPath()%>/images/admin.gif" width="10" height="10" />
                                      <a href="<%=request.getContextPath()%>/background/systemUser/findById.html?userId=${key.id}&type=0">详细信息</a>
                                     </sec:authorize>
                                      <sec:authorize access="hasRole('ROLE_system_user_edit')">
                                      <img src="<%=request.getContextPath()%>/images/edt.gif" width="12" height="12" />
                                      <a href="<%=request.getContextPath()%>/background/systemUser/findById.html?userId=${key.id}&type=1">编辑</a>
                                     </sec:authorize>
                                     <sec:authorize access="hasRole('ROLE_system_user_delete')">
                                      <img src="<%=request.getContextPath()%>/images/del.gif" width="16" height="16" />
                                      <a href="javascript:void(0);" onclick="deleteId('<%=request.getContextPath()%>/background/systemUser/deleteById.html','${key.id}');">删除</a>
                                      </sec:authorize>
                                    </c:if>
                                    <c:if test="${userSession.roleName eq key.roleName }">
                                      <sec:authorize access="hasRole('ROLE_system_user_info')">
                                      <img src="<%=request.getContextPath()%>/images/admin.gif" width="10" height="10" />
                                      <a href="<%=request.getContextPath()%>/background/systemUser/findById.html?userId=${key.id}&type=0">详细信息</a>
                                     </sec:authorize>
                                      <sec:authorize access="hasRole('ROLE_system_user_edit')">
                                      <img src="<%=request.getContextPath()%>/images/edt.gif" width="12" height="12" />
                                      <a href="<%=request.getContextPath()%>/background/systemUser/findById.html?userId=${key.id}&type=1">编辑</a>
                                     </sec:authorize>
                                    </c:if>
                                    <!-- 
                                    <c:if test="${userSession.roleName ne key.roleName && userSession.parentId eq key.parentId }">
                                      <sec:authorize access="hasRole('ROLE_system_user_info')">
                                      <img src="<%=request.getContextPath()%>/images/admin.gif" width="10" height="10" />
                                      <a href="<%=request.getContextPath()%>/background/systemUser/findById.html?userId=${key.id}&type=0">详细信息</a>
                                     </sec:authorize>
                                    </c:if>
                                     -->
                                   </c:otherwise>
                                </c:choose>
                             </td>
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
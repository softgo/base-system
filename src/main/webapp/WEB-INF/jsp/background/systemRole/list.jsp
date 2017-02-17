<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8" http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <%@include file="../../common/taglib.jsp" %>
    <%@include file="../../common/common-css.jsp" %>
    <%@include file="../../common/common-js.jsp" %>

<script type="text/javascript">

    function permissio(roleId){
         var url = "<%=request.getContextPath()%>/background/systemSource/permissioRole.html?roleId="+roleId;
         location.href=url;
    }
    
</script>
    <style type="text/css">
      input{font-size: 12px}
    </style>
</head>
<body>

<form id="fenye" name="fenye" action="<%=request.getContextPath()%>/background/systemRole/find.html" method="post">
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
                <b>&nbsp;&nbsp;角色管理——>角色列表 </b>
            </div>
            
            <div class="box_center pt10 pb10">
              <!-- 查询条件添加 -->
              <table cellpadding="0" cellspacing="0">
                <tr>
                  <td>&nbsp;&nbsp;角色名</td>
                  <td><input type="text" name="roleName" value="${param.roleName}"  class="input-text lh25"/></td>
                    <td>
                        <div class="box_bottom pb5 pt5 pr10" style="border-top:0px solid #dadada;">
                          <div class="search_bar_btn" style="text-align:right;">
                          
                             <input type="submit" name="button" class="btn btn82 btn_search" value="查询">   
                             
                             <sec:authorize ifAnyGranted="ROLE_system_role_add">
                                <input type="button" name="button" class="btn btn82 btn_add" value="新增" onclick="GOTO('<%=request.getContextPath()%>/background/systemRole/addUI.html')">
                             </sec:authorize>
                             
                             <sec:authorize ifAnyGranted="ROLE_system_role_delete">
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
                        <th >角色名</th>
                        <th >角色KEY</th>
                        <th >是否禁用</th>
                        <th >描述</th>
                        <th >基本操作</th>
                      </tr>
                      
                       <c:forEach var="role" items="${pageView.data}">
                              <tr class="tr">
                                 <td align="center" hight="30px" >
                                   <input type="checkbox" name="check" value="${role.id}" />
                                 </td>
                                 <td  align="center" >${role.roleName}</td>
                                 <td  align="center" >${role.roleKey}</td>
                                 <td  align="center" >
                                  <c:if test="${role.status eq '0'}">
                                 <font color="red">禁用</font>
                                 </c:if>
                                 <c:if test="${role.status eq '1'}">
                                 <font color="blue">正常</font>
                                 </c:if></td>
                                 <td  align="center" >${role.description}</td>
                                 <td  align="center" >
                                  <c:choose>
                                     <c:when test="${userSession.userName eq 'admin' && role.roleName eq 'admin'}">
                                         <sec:authorize ifAnyGranted="ROLE_system_role_permissions">
                                             <img src="<%=request.getContextPath()%>/images/resc.png" width="12" height="12" />
                                             <a href="javascript:void(0);" onclick="permissio('${role.id}')">分配权限</a>
                                         </sec:authorize>
                                         <sec:authorize ifAnyGranted="ROLE_system_role_info">
                                             <img src="<%=request.getContextPath()%>/images/admin.gif" width="10" height="10" />
                                             <a href="<%=request.getContextPath()%>/background/systemRole/findById.html?roleId=${role.id}&type=0">详细信息</a>
                                         </sec:authorize>
                                         <!-- 
                                         <sec:authorize ifAnyGranted="ROLE_system_role_edit">
                                             <img src="<%=request.getContextPath()%>/images/edt.gif" width="12" height="12" />
                                             <a href="<%=request.getContextPath()%>/background/systemRole/findById.html?roleId=${role.id}&type=1">编辑</a>
                                         </sec:authorize>
                                          -->
                                     </c:when>
                                     
                                     <c:otherwise>
                                         <c:if test="${userSession.roleName eq role.roleName }">
                                             <c:set var="parentId" value="${role.parentId }"/>
                                             <sec:authorize ifAnyGranted="ROLE_system_role_info">
                                                 <img src="<%=request.getContextPath()%>/images/admin.gif" width="10" height="10" />
                                                 <a href="<%=request.getContextPath()%>/background/systemRole/findById.html?roleId=${role.id}&type=0">详细信息</a>
                                             </sec:authorize>
                                             <sec:authorize ifAnyGranted="ROLE_system_role_edit">
                                                 <img src="<%=request.getContextPath()%>/images/edt.gif" width="12" height="12" />
                                                 <a href="<%=request.getContextPath()%>/background/systemRole/findById.html?roleId=${role.id}&type=1">编辑</a>
                                             </sec:authorize>
                                         </c:if>
                                         
                                         <c:if test="${userSession.roleName ne role.roleName }">
                                             <c:choose>
                                                <c:when test="${ loginRole.parentId eq role.parentId }">
                                                    <!-- 
                                                    <sec:authorize ifAnyGranted="ROLE_system_role_info">
                                                         <img src="<%=request.getContextPath()%>/images/admin.gif" width="10" height="10" />
                                                         <a href="<%=request.getContextPath()%>/background/systemRole/findById.html?roleId=${role.id}&type=0">详细信息</a>
                                                     </sec:authorize>
                                                     -->
                                                </c:when>
                                                <c:otherwise>
                                                    <sec:authorize ifAnyGranted="ROLE_system_role_permissions">
                                                         <img src="<%=request.getContextPath()%>/images/resc.png" width="12" height="12" />
                                                         <a href="javascript:void(0);" onclick="permissio('${role.id}')">分配权限</a>
                                                     </sec:authorize>
                                                     <sec:authorize ifAnyGranted="ROLE_system_role_info">
                                                         <img src="<%=request.getContextPath()%>/images/admin.gif" width="10" height="10" />
                                                         <a href="<%=request.getContextPath()%>/background/systemRole/findById.html?roleId=${role.id}&type=0">详细信息</a>
                                                     </sec:authorize>
                                                     <sec:authorize ifAnyGranted="ROLE_system_role_edit">
                                                         <img src="<%=request.getContextPath()%>/images/edt.gif" width="12" height="12" />
                                                         <a href="<%=request.getContextPath()%>/background/systemRole/findById.html?roleId=${role.id}&type=1">编辑</a>
                                                     </sec:authorize>
                                                     <sec:authorize ifAnyGranted="ROLE_system_role_delete">
                                                         <img src="<%=request.getContextPath()%>/images/del.gif" width="12" height="12" />
                                                         <a href="javascript:void(0);" onclick="deleteId('<%=request.getContextPath()%>/background/systemRole/deleteById.html','${role.id}')">删除</a>
                                                     </sec:authorize>
                                                </c:otherwise>
                                             </c:choose>
                                         </c:if>
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
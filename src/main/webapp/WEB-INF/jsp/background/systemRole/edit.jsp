<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8" http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="../../common/taglib.jsp"%>
<%@include file="../../common/common-css.jsp"%>
<%@include file="../../common/common-js.jsp"%>
</head>
   

 <script type="text/javascript">
	  
	  function saveData(){
		 var roleName = $("#roleName").val();
    	 var roleKey = $("#roleKey").val();		  
    	 if(roleName=="" || roleName==null){
    	 	$("#roleNameTag").html("请填入角色名称!");
    	 	return false;
    	 }else{
    	 	$("#roleNameTag").html("");
    	 }
    	 if( roleKey=="" || roleKey==null ){
    	 	$("#roleKeyTag").html("请填入角色KEY!");
    	 	return false;
    	 }else{
    	 	$("#roleKeyTag").html("");
    	 }
    	 //提交.
    	  document.roleFrom.submit();
   	  	  return true;
	  }
	  
	  //查找是否已经存在.
	  $(function (){
	  		$("#roleName").change(function(){
				var roleName = $("#roleName").val();
				var tempName = $("#tempName").val();
				if(tempName==roleName){
				    return;
				}
				if(roleName != null && roleName != ''){
					$.ajax({
						url:'<%=request.getContextPath()%>/background/systemRole/findByName.html',
						data:{"objName":roleName},
						type : "POST",
						success:function(msg){
							var obj = eval(msg);
							if (obj.msg == "Y"){
								$("#roleNameTag").html(obj.content);
								$("#roleName").val("");
							}else{
								$("#roleNameTag").html("");
							}
							return ;
						}
					});
				}
	  		});
	  		
	  		$("#roleKey").change(function(){
				var roleKey = $("#roleKey").val();
				var tempKey = $("#tempKey").val();
				if(roleKey==tempKey){
				    return;
				}
				if(roleKey != null && roleKey != ''){
					$.ajax({
						url:'<%=request.getContextPath()%>/background/systemRole/findByProps.html',
						data:{"roleKey":roleKey},
						type : "POST",
						success:function(msg){
							var obj = eval(msg);
							if (obj.msg == "Y"){
								$("#roleKeyTag").html(obj.content);
								$("#roleKey").val("");
							}else{
								$("#roleKeyTag").html("");
							}
							return ;
						}
					});
				}
	  		});
	  });
	  
  </script>
<body>

<div style="height: 100%;overflow-y: auto;">
<div id="forms" class="mt10">
   <div class="box">
     <div class="box_border">
       <div class="box_top" ><b class="pl15" > 角色管理——>修改角色</b></div>
       <div class="box_center" style="overflow-y:scroll;align:center; height:75%;">
		<form action="<%=request.getContextPath()%>/background/systemRole/updateById.html" method="post" name="roleFrom" id="roleFrom">
			<input type="hidden" name="id" value="${role.id}">
			<input type="hidden" name="parentId" value="${role.parentId}">
			
			<input type="hidden" name="tempName" id="tempName" value="${role.roleName}">
            <input type="hidden" name="tempKey" id="tempKey" value="${role.roleKey}">
            
			<table class="form_table pt15 pb15 list_table" width="100%" border="1" cellpadding="0" cellspacing="0">
                <tr>
                    <td class="td_right">
                                                            角色名 : 
                    </td>
                    <td class="td_left">
                        <input  class="input-text lh30" size="40" name="roleName" id="roleName" value="${role.roleName}" />
                        <font color="red"> <span id="roleNameTag"/></font>
                     </td>
                   </tr>
                   
                   <tr>
                    <td class="td_right">
                                                            角色roleKey : 
                    </td>
                    <td class="td_left">
                      <input  class="input-text lh30" size="40"  name="roleKey" id="roleKey" value="${role.roleKey}"/>
                      <font color="red"><span id="roleKeyTag"/></font>
                     </td>
                 </tr>
                 <tr>
                    <td class="td_right">
                                                        是否禁用 : 
                    </td>
                    <td class="td_left">
                        <input name="status" type="radio" <c:if test="${role.status eq '0'}">checked="checked"</c:if> value="0"/>禁用　　
						<input name="status" type="radio" <c:if test="${role.status eq '1'}">checked="checked"</c:if> value="1"/>正常
                    </td>
                 </tr>
                 <tr>                        
                    <td class="td_right">
                                                        描述 : 
                    </td>
                    <td class="td_left">
                         <input class="input-text lh30" size="40" name="description" id="description" value="${role.description}"/>
                         <font color="red"><span id="descriptionTag"/></font>
                    </td>
                </tr>
               
				<tr>
					<td class="td_right">
                       
                    </td>
                    <td class="td_left">
                       <sec:authorize ifAnyGranted="ROLE_system_role_edit">
								<input type="button" name="button" class="btn btn82 btn_save2" value="保存" onclick="return saveData()" > 
				       </sec:authorize>
                      <input type="button" name="button" class="btn btn82 btn_res" value="重置" onclick="clearText()">
                      <input type="button"  class="btn btn82 btn_nochecked" value="返回" onclick="location.href='javascript:history.go(-1)'">
                    </td>
				</tr>
			</table>
		  </form>
		  </div>
          </div>
        </div>
     </div>	
</div>
	
  </body>
</html>

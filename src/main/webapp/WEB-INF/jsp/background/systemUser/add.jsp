<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8" http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="../../common/taglib.jsp"%>
<%@include file="../../common/common-css.jsp"%>
<%@include file="../../common/common-js.jsp"%>
</head>

 <script type="text/javascript">	
      //防止重复提交.  
	  function saveData(){
		 var userName = $("#userName").val();
    	 var userPassword = $("#userPass").val();		  
    	 if(userName=="" || userName==null){
    	 	$("#userNameTag").html("请输入用户名称!");
    	 	return false;
    	 }else{
    	 	$("#userNameTag").html("");
    	 }
    	 if( userPassword=="" || userPassword==null ){
    	 	$("#userPassTag").html("请输入密码！");
    	 	return false;
    	 }else{
    	 	$("#userPassTag").html("");
    	 }
    	 
    	 var userPhone = $("#userPhone").val(); 
         var userMail = $("#userMail").val(); 
         var userQq =  $("#userQq").val();
         if(userPhone!=null && userPhone!=""){
            if( !isPhone(userPhone) ||userPhone.length !=11 ){
                $("#userPhoneTag").html("请输入有效的手机号码！");
                return false;
            }else{
                $("#userPhoneTag").html("");
            }
         }
         if(userMail!=null && userMail!=""){
            if( !isEmail(userMail)){
                $("#userMailTag").html("请输入有效的邮箱！");
                return false;
            }else{
                $("#userMailTag").html("");
            }
         }
         if(userQq!=null && userQq!=""){
            if( !isNum(userQq)){
                $("#userQqTag").html("请输入有效的 QQ号！");
                return false;
            }else{
                $("#userQqTag").html("");
            }
         }
         
    	 //提交.
    	 document.userForm.submit();
   	  	 return true;
	  }
	  
	  //查找是否已经存在.
	  $(function (){
	  		$("#userName").change(function(){
				var userName = $("#userName").val();
				if(userName != null && userName != ''){
					$.ajax({
						url:'<%=request.getContextPath()%>/background/systemUser/findByName.html',
						data:{"objName":userName},
						type : "POST",
						success:function(msg){
							var obj = eval(msg);
							if (obj.msg == "Y"){
								$("#userNameTag").html(obj.content);
								$("#userName").val("");
							}else{
								$("#userNameTag").html("");
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
       <div class="box_top" ><b class="pl15" >系统管理——>添加用户</b></div>
       <div class="box_center" style="overflow-y:scroll;align:center; height:75%;">
		<form action="<%=request.getContextPath()%>/background/systemUser/addOne.html" method="post" name="userForm" id="userForm">
			<table class="form_table pt15 pb15 list_table" width="100%" border="1" cellpadding="0" cellspacing="0">
                <tr>
                    <td class="td_right">
                                                            用户名 : 
                    </td>
                    <td class="td_left">
                        <input  class="input-text lh30" size="40" name="userName" id="userName" />
                        <font color="red"> <span id="userNameTag"/></font>
                     </td>
                   </tr>
                   
                   <tr>
                    <td class="td_right">
                                                            用户密码 : 
                    </td>
                    <td class="td_left">
                      <input  class="input-text lh30" size="40"  name="userPass" id="userPass" type="password" />
                      <font color="red"><span id="userPassTag"/></font>
                     </td>
                 </tr>
                 <tr>
                    <td class="td_right">
                                                        用户性别 : 
                    </td>
                    <td class="td_left">
                           <input type="radio" name="userSex" value="1" checked="checked"/>男
                           <input type="radio" name="userSex" value="0"/>女
                    </td>
                 </tr>
                 <tr>                        
                    <td class="td_right">
                                                        用户地址 : 
                    </td>
                    <td class="td_left">
                         <input class="input-text lh30" size="40" name="userAddress" id="userAddress"/>
                         <font color="red"><span id="userAddressTag"/></font>
                    </td>
                </tr>
                
                <tr>
                    <td class="td_right">
                                                        联系电话 : 
                     </td>
                    <td class="td_left">
                       <input class="input-text lh30" size="40"  maxlength="11" name="userPhone"  id="userPhone"/>
                       <font color="red"><span id="userPhoneTag"/></font>
                    </td>
                 </tr>
                 <tr>
                    <td class="td_right">
                                                        邮箱地址 : 
                     </td>
                    <td class="td_left">
                      <input class="input-text lh30" size="40" name="userMail"  id="userMail"/>
                      <font color="red"><span id="userMailTag"/></font>
                    </td>
                </tr>
                
                <tr>
                    <td class="td_right">
                        QQ : 
                    </td>
                    <td class="td_left">
                      <input class="input-text lh30" size="40" name="userQq"  id="userQq"/>
                      <font color="red"><span id="userQqTag"/></font>
                    </td>
                </tr>
				<tr>
					<td class="td_right">
                       
                    </td>
                    <td class="td_left">
                       <sec:authorize ifAnyGranted="ROLE_system_user_add">
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

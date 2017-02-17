<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4">&nbsp;&nbsp;
	              总共&nbsp;${pageView.rowCount}&nbsp;条&nbsp;&nbsp;
	                         分&nbsp;${pageView.pageCount}&nbsp;页显示&nbsp;&nbsp;
	             <!-- 每页显示数据 -->            
	              每页显示&nbsp;
	              <span>
					<select name="pageSizeChoose" id="pageSizeChoose" onchange="ChoosePageSize('${pageView.rowCount}')" >
					    <option value="2"  <c:if test="${pageView.pageSize==2}">selected</c:if>>2</option>
					    <option value="5"  <c:if test="${pageView.pageSize==5}">selected</c:if>>5</option>
					    <option value="10" <c:if test="${pageView.pageSize==10}">selected</c:if>>10</option>
					    <option value="15" <c:if test="${pageView.pageSize==15}">selected</c:if>>15</option>
					</select>
	              </span>
	              &nbsp;
    	                     条&nbsp;&nbsp;
	                               当前为&nbsp;${pageView.pageNo}&nbsp;页
              </td>
            <td>
            <table border="0" align="right" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="40" class="STYLE4">
                  <a href="#" onclick="pageNow('1');">
                  	首页
                  </a>
                  </td>
                  <td width="45" class="STYLE4">
                  <a href="#" onclick="return pageNow('${pageView.pageNo - 1}')">
                  	上一页
                  </a>
                  </td>
                  <td align="center">
                  <c:forEach begin="${pageView.pageIndex.startIndex}" end="${pageView.pageIndex.endIndex}" var="key">
						<c:if test="${pageView.pageNo==key}">
							&nbsp;<span class="current" style="color: red;font-size: 20px;"> ${key}</span>
						</c:if>
						<c:if test="${pageView.pageNo!=key}">
							&nbsp;
							<span style="font-size: 16px;">
								<a href="#" onclick="pageNow('${key}')">${key}</a>
							  </span>
						</c:if>
					</c:forEach>&nbsp;
                  </td>
                  <td width="45" class="STYLE4">
                  <a href="#" onclick="pageNow('${pageView.pageNo + 1}')">
                  	下一页
                  </a>
                  </td>
                  <td width="40" class="STYLE4">
                  <a href="#" onclick="pageNow('${pageView.pageCount}')">
                 	尾页
                  </a>
                  </td>
                  <td>
                  	<c:if test="${pageView.pageCount > 0}">
						  <span class="text">
						  	<input type="text" size="1" id="pageNowCount" name="pageNowCount" value="${pageView.pageNo}" onkeypress="if(event.keyCode==13) return false;" >
						    <span style="cursor:hand;COLOR: #31659c"><a style="cursor: pointer;text-decoration: none;margin: 0px;padding: 0px;" onclick="GOPage()" >[GO]</a></span>	
						  </span>
					</c:if>
                  </td>
                </tr>
            </table>
            </td>
          </tr>
        </table>
        </td>
      </tr>
    </table>

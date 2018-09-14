
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>



<s:if test="subjectList.size() > 0">

<table>
<s:iterator value="subjectList" status="status">
			<tr>
				
				<td><s:property value="userId" /></td>
				<td><s:property value="userName" /></td>
			
			
			
			</tr>
		</s:iterator>


</table>


</s:if>
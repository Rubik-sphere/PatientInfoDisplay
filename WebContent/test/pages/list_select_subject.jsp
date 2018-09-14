<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="struts2, twitter, bootstrap, plugin, showcase" />
    <meta name="description" content="A Showcase for the Struts2 Bootstrap Plugin" />
    <title>Struts2 Bootstrap Plugin Showcase - <s:text name="showcase.version"/></title>

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <sb:head includeScripts="false" includeScriptsValidation="false"/>
    <style type="text/css">
        body {
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>
</head>

<body>


<div class="container">
 <h3>View Summary Subject Statistics</h3>
 
  <s:form action="GetSelectSubject.action" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal well" id="form1">
 
   
            <s:checkboxlist
                        list="subjectDropdownList"
                        labelposition="inline"
                        label="Subject(s):"
                        listValue="%{userName}" listKey="%{userId}"
                        name="selectId"
                      
                        
              />   
 
   
      <div style="text-align:right">
       <s:submit cssClass="btn btn-primary" id="btn_submit"/>
      </div>
 
 </s:form>
 
 
 <table>

<s:if test="subjectList!=null&&subjectList.size() > 0">
<s:iterator value="subjectList" status="status">
			<tr>
				
				<td><s:property value="userId" /> </td>
				<td><s:property value="userName" /> </td>
			
			
			
			</tr>
		</s:iterator>

</s:if> 

</table>



 </div>
 
 </body>
 </html>

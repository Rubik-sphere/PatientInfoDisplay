<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html lang="en">

<s:url var="list_all_subject_url" action="ListAllSubject.action" namespace="/test/pages"/>
<s:a href="%{list_all_subject_url}">List All Subject</s:a>
<br>

<s:url var="list_sel_subject_url" action="GetSelectSubject.action" namespace="/test/pages"/>
<s:a href="%{list_sel_subject_url}">List Select Subject</s:a>



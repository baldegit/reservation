<%@ taglib prefix="x" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true" %>
<!--
	dans les vues ils faut utiliser com/jstl/core à la place de com/jsp/jstl/core
 -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<x:admin title="Administration">
	<jsp:attribute name="body_area">
	
	
	<c:choose>	
		<c:when test="${artistes.id > 0}">
			<h1>Update Artiste</h1>
		</c:when>
		<c:when test="${message}">
			<div class="alert alert-danger">
			  <strong>Danger!</strong> ${message}.
			</div>
		</c:when>
		<c:otherwise>
			<h1>Add Artiste</h1>
		</c:otherwise>
	</c:choose>
	
	

<%-- 	<form:form action="saveOrUpdateArtiste" method="post" modelAttribute="artiste"> --%>
<!-- 		<table> -->
<!-- 			<tr> -->
<%--                 <td><form:label path="firstName">Non</form:label></td> --%>
<!--             	<td> -->
<%--             		<form:input path="firstName" type="text" placeholder="Non"  class="form-control" value="" /> --%>
<%--             		<c:out value='${artistes.firstName}' ></c:out> --%>
<!--             	</td> -->
            	
<%--             	<td><form:errors path="firstName"/></td> --%>
<!--             </tr> -->
<!--             <tr> -->
<%--                 <td><form:label path="lastName">Prenom</form:label></td> --%>
<!--             	<td> -->
<%--             		<form:input path="lastName" type="text" placeholder="prenom"  class="form-control" /> --%>
<%--             		<c:out value='${artistes.lastName}' ></c:out>	 --%>
<!--             	</td> -->
            	
<%--             	<td><form:errors path="lastName"/> --%>
<!--             </tr> -->
            
<!--             <tr> -->
<!--                 <td><input type="submit" value="Submit"/></td> -->
<!--             </tr> -->
<!-- 		</table> -->

<%-- 	</form:form> --%>
	
	<c:url value="/admin/saveOrUpdateArtiste" var="saveOrUpdateArtiste"></c:url>

	<form class="form-horizontal" method="post" action='<c:out value="${saveOrUpdateArtiste}"></c:out> '>
		  <div class="form-group">
			    <label class="control-label col-sm-2" for="email">Nom</label>
			    <div class="col-sm-10">
			      <input  type="text" class="form-control" name ="firstName" placeholder="Enter nom" value='<c:out value="${artistes.firstName}"/>'>
			    </div>
		  </div>
		  <div class="form-group">
			    <label class="control-label col-sm-2" for="pwd">Prenom</label>
			    <div class="col-sm-10"> 
			      <input  type="text" class="form-control" name ="lastName" placeholder="Enter prenom" value='<c:out value="${artistes.lastName}"/>'>
			    </div>
		  </div>
		  <div class="form-group"> 
			    <input type="hidden" name ="id"  value="<c:out value="${artistes.id}" />">
		  </div>
		  <div class="form-group"> 
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">Submit</button>
		    </div>
		  </div>
	</form>
	  

	</jsp:attribute>
</x:admin>
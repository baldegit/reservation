<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="x" tagdir="/WEB-INF/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>

<!--
	dans les vues ils faut utiliser com/jstl/core à la place de com/jsp/jstl/core
 -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jstl/core" %>
<x:admin title="Artiste">
	<jsp:attribute name="body_area">
		<div class="container">
			<c:if test="${not empty message}">
				<div class="alert alert-success">
				  <strong>Success!</strong> <c:out value="${message}"> </c:out>
				</div>
			</c:if>
		</div>
	   <div class="row">
	   
	   	<div class="col-md-10">
	   		<div class="panel panel-default">
			  <div class="panel-heading">
			  	Liste des roles [A partir de mon API -> http://localhost:8080/ReservationSpectacle/roles]
			  </div>
			  <div class="panel-body">
			  	<table class="table table-hover">
			  		<thead class="table-dark">
			  			<th>id</th>
			  			<th>Roles</th>
			  			
			  		</thead>
			  		<tbody>
			  				<c:forEach items="${roles}" var="role">
				  			<tr>
				  				<td><c:out value="${role.id}"/></td>
				  				<td><c:out value="${role.role}"/></td>
				  				
					  			<td>
					  				<c:url value="/admin/deleteRole?id=" var="deleteRoles" />
<%-- 					  				<a href="<c:out value="${deleteRoles}${role.id}" />"> --%>
									<a href="#">
										<span class="glyphicon glyphicon-trash" style="font-size:1em;color:red;"></span>
									</a>
					  			</td>
					  			
					  			
					  		</tr>	
			  			</c:forEach>
			  		
			  			
			  		</tbody>
			  	</table>
	
			  </div>
			</div>
	   	</div>
<!-- 	   	<div class="col-md-2"> -->
	   		
<%-- 	   		<c:url value="/admin/addShow" var="addShow"></c:url> --%>
<%-- 	   		<a href='<c:out value="${addShow}" />'> --%>
<!-- 	   			<button type="button" class="btn btn-info pull-right">Add A show from API</button> -->
<!-- 	   		</a> -->
	   		
<!-- 	   	</div> -->
	   	
	   </div>
	   
	</jsp:attribute>
</x:admin>
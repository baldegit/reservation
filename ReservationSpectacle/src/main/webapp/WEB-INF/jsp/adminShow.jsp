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
				  <strong>Success!</strong> <c:out value="${message}"> yep</c:out>
				</div>
			</c:if>
		</div>
	   <div class="row">
	   
	   	<div class="col-md-10">
	   		<div class="panel panel-default">
			  <div class="panel-heading">
			  	Liste des Artites
			  </div>
			  <div class="panel-body">
			  	<table class="table table-hover">
			  		<thead class="table-dark">
			  			<th>id</th>
			  			<th>Titre</th>
			  			<th>Places</th>
			  			<th>Prix</th>
			  			<th>Localité</th>
			  			<th>Supprimer</th>
			  			
			  		</thead>
			  		<tbody>
			  			<c:if test="${not empty shows}">
			  				<c:forEach items="${shows}" var="show">
				  			<tr>
				  				<td><c:out value="${show.id}"/></td>
				  				<td><c:out value="${show.title}"/></td>
				  				<td><c:out value="${show.bookable}"/></td>
				  				<td><c:out value="${show.price}"/></td>
				  				<td><c:out value="
				  				${show.location.designation} ,
				  				${show.location.address}, 
				  				${show.location.locality.postalCode}
				  				"/></td>
					  			
					  			<td>
					  				<c:url value="/admin/deleteShow?id=" var="deleteShow" />
					  				<a href="<c:out value="${deleteShow}${show.id}" />">
										<span class="glyphicon glyphicon-trash" style="font-size:1em;color:red;"></span>
									</a>
					  			</td>
					  			
					  			
					  		</tr>	
			  			</c:forEach>
			  			</c:if>
			  			
			  		</tbody>
			  	</table>
			  	<div class="container">
			  		<ul class="pagination">
			  			<c:url value="/admin/shows?page=" var="show" />
			  			<c:if test="${not empty pages}">
				  			<c:forEach begin="0" end="${pages-1}" step="1" var="page">
				  				<li>
				  					<a href="<c:out value="${show}${page}" >  </c:out>" > <c:out value="${page+1}" /></a>
				  				</li>
				  			</c:forEach>
			  			</c:if>
			  		</ul>
			  	</div>
			  </div>
			</div>
	   	</div>
	   	<div class="col-md-2">
	   		
	   		<c:url value="/admin/addShow" var="addShow"></c:url>
	   		<a href='<c:out value="${addShow}" />'>
	   			<button type="button" class="btn btn-info pull-right">Add A show from API</button>
	   		</a>
	   		
	   	</div>
	   	
	   </div>
	   
	</jsp:attribute>
</x:admin>
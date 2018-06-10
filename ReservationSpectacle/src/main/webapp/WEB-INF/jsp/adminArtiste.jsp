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
			  			<th>Nom</th>
			  			<th>Prenom</th>
			  			<th>Type</th>
			  			<th>Supprimer</th>
			  			<th>Modifier</th>
			  		</thead>
			  		<tbody>
			  			<c:if test="${not empty artistes}">
			  				<c:forEach items="${artistes}" var="artiste">
				  			<tr>
				  				<td><c:out value="${artiste.id}"/></td>
				  				<td><c:out value="${artiste.firstName}"/></td>
					  			<td><c:out value="${artiste.lastName}"/></td>

					  			
					  			<td>
					  				<c:forEach items="${artistesTypes}" var="artistTypes">
					  					<c:if test="${artiste.id == artistTypes.artist.id}">
					  						<c:out value="${artistTypes.type.type}"/>,
					  					</c:if>
					  				</c:forEach>
					  			</td>
					  			
					  			<td>
					  				<c:url value="/admin/deleteArtiste?id=" var="deleteArtiste" />
					  				<a href="<c:out value="${deleteArtiste}${artiste.id}" />">
										<span class="glyphicon glyphicon-trash" style="font-size:1em;color:red;"></span>
									</a>
					  			</td>
					  			
					  			<td>
					  				<c:url value="/admin/updateArtiste?id=" var="updateArtiste" />
					  				<a href="<c:out value="${updateArtiste}${artiste.id}" />">
					  					<span class="glyphicon glyphicon-pencil" style="font-size:1em;color:blue;"></span>
					  				</a>
					  			</td>
					  			
					  		</tr>	
			  			</c:forEach>
			  			</c:if>
			  			
			  		</tbody>
			  	</table>
			  	<div class="container">
			  		<ul class="pagination">
			  			<c:url value="/admin/artiste?page=" var="artiste" />
			  			
			  			<c:forEach begin="0" end="${pages-1}" step="1" var="page">
			  				<li>
			  					<a href="<c:out value="${artiste}${page}" >  </c:out>" > <c:out value="${page+1}" /></a>
			  				</li>
			  			</c:forEach>
			  		</ul>
			  	</div>
			  </div>
			</div>
	   	</div>
	   	<div class="col-md-2">
	   		
	   		<c:url value="/admin/addArtiste" var="addArtiste"></c:url>
	   		<a href='<c:out value="${addArtiste}" />'>
	   			<button type="button" class="btn btn-info pull-right">Add New Artite</button>
	   		</a>
	   		
	   	</div>
	   	
	   </div>
	   
	</jsp:attribute>
</x:admin>
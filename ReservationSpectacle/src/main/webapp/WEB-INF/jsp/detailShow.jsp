<%@ taglib prefix="x" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<!--
	dans les vues ils faut utiliser com/jstl/core à la place de com/jsp/jstl/core
 -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jstl/core" %>
<x:user title="Home">
	<jsp:attribute name="body_area">
	
	<div class="container">
	
		<div class="row">
			<div class = "col-md-9">
				<img class="img-fluid rounded" src="http://placehold.it/800x300" alt="">
				<br>
				
				<h2>
					<span class="glyphicon glyphicon-info-sign"></span>
					<c:out value="${shows.title}"> </c:out>
				</h2>
				
				
				<h3>
					<span class="glyphicon glyphicon-map-marker"></span>
					<c:out value="${shows.location.designation}"> </c:out>
				</h3>
				<p>
					<em><c:out value="${shows.description}" /></em>
				</p>
				<br/><br/>
			</div>
			
			<div class = "col-md-3">
				
				<div class="card" >
			  		<div class="card-header">
			    		Featured
			  		</div>
				  	<ul class="list-group list-group-flush">
				    	<li class="list-group-item">
				    		<b>Places disponible</b>
				    		<span class="badge"><c:out value="${shows.bookable}"> </c:out></span>				    		
				    	</li>
				    	<li class="list-group-item">
				    		<b>Prix</b>
				    		<span class="badge"><c:out value="${shows.price}"> </c:out></span>				    		
				    	</li>
				    	<li class="list-group-item">
				    		<span class="glyphicon glyphicon-hand-right"></span>
				    		<c:out value="${shows.location.designation}"> </c:out>
				    	</li>
				    	<li class="list-group-item">
				    		<span class="glyphicon glyphicon-map-marker"></span>
				    		<c:out value="${shows.location.address}"> </c:out>
				    	</li>
				    	<li class="list-group-item">
				    		<b>Code postal</b>
				    		<span class="badge">
				    			<c:out value="${shows.location.locality.postalCode}"> </c:out>
				    			<c:out value="${shows.location.locality.locality}"> </c:out>
				    		</span>				    		
				    	</li>
				    	<li class="list-group-item">
				    		<c:url value="/reservationShow?id=" var="reservationShow" />
							<a href='<c:out value="${reservationShow}${shows.id}" />'>
								<button type="button" class="btn btn-danger btn-block">RESERVER</button>
							</a>
				    	</li>
				    	
				  	</ul>
				</div>
				
			</div>
		</div>
		
	</div>
		   
	</jsp:attribute>
</x:user>
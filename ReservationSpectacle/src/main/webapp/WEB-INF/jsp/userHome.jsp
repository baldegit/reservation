<%@ taglib prefix="x" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<!--
	dans les vues ils faut utiliser com/jstl/core à la place de com/jsp/jstl/core
 -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jstl/core" %>
<x:user title="Home">
	<jsp:attribute name="body_area">
	
<!-- Page Content -->
<!-- 	<div class="container"> -->
	
		<div class="container">
			<c:if test="${message}">
				<div class="alert alert-danger">
				  <strong>Success!</strong> <c:out value="${message}"> </c:out>
				</div>
			</c:if>
		</div>
		<div class="container">
		<div class="row">
			
			<c:if test="${not empty shows}">
				<c:forEach items="${shows}" var="show">
					
					<div class="col-md-4 ">
			          <div class="card">
			            <a href="#">
			            	<img class="card-img-top" src="http://placehold.it/350x200" alt="">
<!-- 			            	<img src="" class="img-circle" alt="Cinque Terre"> -->
			            </a>
			            <div class="card-body">
			            
			            	
			            		<h5 class="card-title">
				                	<a href="#"><c:out value="${show.title}"/></a>
				              	</h5>
				          
			            	<div class="row">
				             	<div class="col-md-6">
					             	<c:url value="/detailShow?id=" var="detailShow" />
							   		<a href='<c:out value="${detailShow}${show.id}" />'>
							   			<button type="button" class="btn btn-info btn-block">En savoir plus</button>
							   		</a>				              		
				              	</div>
				              	<div class="col-md-6">
				              		<c:url value="/reservationShow?id=" var="reservationShow" />
							   		<a href='<c:out value="${reservationShow}${show.id}" />'>
							   			<button type="button" class="btn btn-danger btn-block">Reservez</button>
							   		</a>
				              	</div>
				            </div>
			            	<br/>
			            </div>
			          </div>
			        </div>
					
				</c:forEach>
			</c:if>
	        

		</div>
		<div class="container">
			  		<ul class="pagination">
			  			<c:url value="/?page=" var="show" />
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

<!-- 	</div> -->
	  
	   
	</jsp:attribute>
</x:user>
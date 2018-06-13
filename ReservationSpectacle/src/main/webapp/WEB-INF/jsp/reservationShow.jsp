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
		
			<div class = "row">
				<div class="col-md-8">
		          <img class="img-fluid" src="http://placehold.it/750x500" alt="">
		        </div>
		        <div class="col-md-4">
		        	<h3><c:out value="${shows.title}"/></a></h3>
		        	<p>
			          	<b>Places disponible</b>
					    <span class="badge"><c:out value="${shows.bookable}"> </c:out></span>	
				    </p>
				    <p>
					    <b>Prix</b>
					    <span class="badge"><c:out value="${shows.price}"> </c:out></span>
				    </p>
				    <p>
					    <span class="glyphicon glyphicon-hand-right"></span>
					    <c:out value="${shows.location.designation}"> </c:out>
				    </p>
				    <p>
					    <span class="glyphicon glyphicon-map-marker"></span>
					    <c:out value="${shows.location.address}"> </c:out>
					</p>
					<p>
				    <b>Code postal</b>
					    <span class="badge">
						    <c:out value="${shows.location.locality.postalCode}"> </c:out>
						    <c:out value="${shows.location.locality.locality}"> </c:out>
					    </span>	
				    </p>
				    <hr>
				    <p>
				    	<c:url value="/stripePayments" var="stripePayments"></c:url>
		
						<form action='<c:out value="${stripePayments}"></c:out>' method="POST">
						  <script
						    src="https://checkout.stripe.com/checkout.js" class="stripe-button"
						    data-key="pk_test_g6do5S237ekq10r65BnxO6S0"
						    data-amount=<c:out value="${shows.price}" />
						    data-name="Stripe.com"
						    data-description="Example charge"
						    data-image="https://stripe.com/img/documentation/checkout/marketplace.png"
						    data-locale="auto"
						    data-zip-code="true">
						  </script>
						  <input type="hidden" name="id" value="<c:out value="${shows.id}" />">
					</form>
				    </p>
		        </div>
			</div>
			<br> 
			
			
		</div>
		
				
	</jsp:attribute>
</x:user>
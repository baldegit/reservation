<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ tag language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<!-- titre de la page -->
<%@ attribute name="title"%>
<!-- name sera utiliser dans les .jsp -->
<%@ attribute name="body_area" fragment="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
			integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" 
			integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
			integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>	
		
		<!-- Affichage du titre -->
		<title>${title}</title>
	</head>
	
	<body>
		<div class="container-fluid">
		
			<nav class="navbar navbar-inverse">
			  <div class="container-fluid">
			    <div class="navbar-header">
			      <a class="navbar-brand" href="#">Theatre de bruxelle</a>
			    </div>
			    <ul class="nav navbar-nav">
			      <li class="active"><a href="#">Home</a></li>
			    </ul>
			    <ul class="nav navbar-nav navbar-right">
			      <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			    </ul>
			  </div>
			</nav>
			
			<div class = "row">
			<!-- Menu de gauche -->
				<div class="col-md-3">
					<div class="panel panel-default">
					  <div class="panel-body">
					  	
					  	<div class="panel panel-default">
						  <div class="panel-heading">CATALOGUE</div>
						  <div class="panel-body">
						  	<ul> 
						  		<li>
						  			<c:url value="/admin/artiste" var="artiste" />
						  			<a href="${artiste}"> Gestion des artistes</a>
						  		</li>
						  		<li>
						  			<c:url value="/admin/shows" var="show" />
						  			<a href="${show}"> Gestion des Shows</a>
						  		</li>
						  		<li>
						  			<c:url value="/admin/representation" var="representation" />
						  			<a href="#"> Gestion des Representations</a>
						  		</li>
						  		<li>
						  			<c:url value="/admin/roles" var="role" />
						  			<a href="${role}"> Gestion des Roles</a>
						  		</li>
						  	</ul>
						  </div>
						</div>
						
						<div class="panel panel-default">
						  <div class="panel-heading">Utilisateurs</div>
						  	<ul>
						  		<li><a href="#"> Gestion des Utilsateurs</a></li>
						  	</ul>
						</div>
						
					  </div>
					</div>
				</div>
			<!-- affichage du body -->
				<div class="col-md-9">
					<div class="panel panel-default">
					  <div class="panel-body">
					  	<!--
						cette partie est dinamique, c'est ici que saffiche toute les
						pages-->
						<jsp:invoke fragment="body_area" /><br>
					  </div>
					</div>
				</div>
			</div>
			
			
			
			<!-- definri avec bootstrap le footer-->
			<nav class="navbar navbar-inverse navbar-fixed-bottom">
						  <ul class="nav navbar-nav">
						    <li><a href="#">Link</a></li>
						    <li><a href="#">Link</a></li>
						  </ul>
						  <p class="navbar-text">Some text</p>
					</nav> 
		</div>
	</body>
</html>
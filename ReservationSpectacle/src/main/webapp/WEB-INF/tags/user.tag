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
		
		<meta charset="utf-8">
	
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
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<!-- Affichage du titre -->
		<title>${title}</title>
	</head>
	
	<body>
		
		<nav class="navbar navbar-inverse ">
			<div class="container-fluid ">
			    <div class="navbar-header">
			      <a class="navbar-brand" href="#">WebSiteName</a>
			    </div>
			    <ul class="nav navbar-nav">
			      <li class="active"><a href="#">Home</a></li>
			      <li><a href="#">Page 1</a></li>
			      <li><a href="#">Page 2</a></li>
			      
			    </ul>
			    <ul class="nav navbar-nav navbar-right">
			    	<li class="dropdown">
				      	<a class="dropdown-toggle" data-toggle="dropdown" href="#"> Dropdown  <span class="caret"></span></button></a>
				      	<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
						    <li><a href="#">HTML</a></li>
						    <li><a href="#">CSS</a></li>
						    <li><a href="#">JavaScript</a></li>
					  	</ul>
			      </li>
			      <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			    </ul>
		  	</div>
		</nav>
		
	
		
		<!-- affichage du body -->
<!-- 		<div class="panel panel-default"> -->
			<div class="panel-body">
				<!--
				cette partie est dinamique, c'est ici que saffiche toute les
				pages-->
				<jsp:invoke fragment="body_area" /><br>
			</div>
<!-- 		</div> -->
		
    
     <!-- Footer -->
    <footer class="navbar navbar-inverse navbar-fixed-bottom">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2018</p>
      </div>
      <!-- /.container -->
    </footer>
	
	
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		
	</body>
</html>
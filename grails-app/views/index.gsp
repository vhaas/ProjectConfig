<!DOCTYPE HTML>
<html lang="de">
<head>
	<meta charset="utf-8"/>	
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen"></link>
	<link rel="stylesheet/less" type="text/css" href="css/app.less"></link>
	<title>Project Configurer</title>
    <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
    </style>
    
	
	<g:render template="/handlebars/form-elements"></g:render>
	<g:render template="/handlebars/epic-views"></g:render>
    
	<script type="text/x-handlebars" data-template-name="application">
		<div class="navbar navbar-inverse navbar-fixed-top">
		  <div class="navbar-inner">
			<div class="container">
			  <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			  </button>
			  <a class="brand" href="#">Project Configurer</a>
			  <div class="nav-collapse collapse">
				<ul class="nav">
				  <li class="active"><a href="#">Home</a></li>
				  <li><a href="#about">About</a></li>
				  <li><a href="#contact">Contact</a></li>
				</ul>
			  </div><!--/.nav-collapse -->
			</div>
		  </div>
		</div>
		
		<div class="container-fluid">
			{{outlet}}
		</div>
	</script>
	
	

</head>
<body>
	<script src="js/handlebars.js"></script>
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/ember.js"></script>
	<script src="js/less-1.3.3.min.js"></script>
	<script src="js/ember-data.js"></script>
	<script src="js/app/main.js"></script>
	

</body>
</html>
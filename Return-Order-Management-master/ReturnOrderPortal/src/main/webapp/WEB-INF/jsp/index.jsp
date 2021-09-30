<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css" />
<title>Login</title>
<script type="text/javascript" src="js/app.js">
	
</script>
</head>

<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-light">
			<div class="container-fluid">
				<h2 id="header">Return Order Management</h2>
			</div>
		</nav>
	</header>
	<div class="container">
		<div class="row" id="main">
			<div class="col-sm">
				<h1 id="heading">Login Page</h1>
			</div>

		</div>
		<div class="row">
			<div class="col-md">
				<img src="images/login.svg" class="img-fluid" alt="login"
					id="login-image">
			</div> 
			<div class="col-md-4" id="login-form">
				<form action="index" method="POST" class="needs-validation"
					novalidate>
					<div class="form-group">
						<label for="username" class="sr-only">Username</label> <input
							type="text" name="username" id="username" class="form-control"
							placeholder="Enter username" pattern="[A-Za-z]+" required>
						<div class="invalid-feedback">Please enter Username.</div>
					</div>
					<div class="form-group sm">
						<label for="password" class="sr-only">Password</label> <input
							type="password" name="password" id="password"
							class="form-control" placeholder="Enter password" required>
						<div class="invalid-feedback">Please enter password.</div>
						</div>
						<button type="submit" name="submit" value="submit"
							class="btn btn-outline-success login-btn btn-block">Login</button>
						<p class="text-danger" id="error">${invalidlogin}
						<p>
				</form>
			</div>
		</div>
	</div>




	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script src="js/app.js"></script>
</body>

</html>
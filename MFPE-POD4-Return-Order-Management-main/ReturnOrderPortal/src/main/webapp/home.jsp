<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<meta charset="ISO-8859-1">
<title>Enter Component Details Here</title>
</head>
<body>



	<div class="container">
		<div class="row">

			<div class="col-md-8 col-md-offset-2">
				<form role="form" method="POST" action="addprocessRequest">

					<legend class="text-center">Return Order</legend>

					<fieldset>
						<legend>Return Order Details</legend>

						<div class="form-group col-md-6">
							<label for="username">Name</label> <input type="text"
								class="form-control" name="username" id="username"
								placeholder="Enter the User Name" required>
						</div>

						<div class="form-group col-md-12">
							<label for="">Contact Number</label> <input type="tel"
								class="form-control" name="contactNumber"
								id="contactNumber"
								placeholder="Enter User's Contact Number" required>
						</div>

						<div class="form-group col-md-6">
							<label for="cardNumber">Credit Card Number</label> <input
								type="tel" pattern="\d*" length="12" class="form-control"
								name="creditCardNumber" id="creditCardNumber"
								placeholder="Enter Credit Card Number" required>
						</div>


					</fieldset>

					<fieldset>
						<legend>Defective Component Detail</legend>

						<div class="form-group col-md-6">
							<label for="ComponentType">Component Type</label> <select
								class="form-control" name="componentType" id="componentType">
								<option value="integral">Integral Component</option>
								<option value="accessory">Accessory Component</option>
							</select>
						</div>
						<div class="form-group col-md-6">
							<label for="componentName">Component Name</label> <input
								type="text" class="form-control" name="componentName"
								id="componentName" placeholder="Enter the Component Name" required>
						</div>

						<div class="form-group col-md-6">
							<label for="componentName">Quantity</label> <input type="text"
								class="form-control" name="quantityOfDefective" id="quantityOfDefective"
								placeholder="Enter the Component's Quantity" required>
						</div>

						
					</fieldset>

					<div class="form-group">
						<div class="col-md-12">
							<div class="checkbox">
								<label> 
									Order is of high Priority: 
									<input type="radio" value="true" id="isPriorityRequest" name="isPriorityRequest" required> Yes
									<input type="radio" value="false" id="isPriorityRequest" name="isPriorityRequest"> No
								</label>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-12">
							<button type="submit" class="btn btn-primary">Return
								Confirm</button>
							
						</div>
					</div>
					
					
					
				</form>
			</div>

		</div>
	</div>


</body>
</html>
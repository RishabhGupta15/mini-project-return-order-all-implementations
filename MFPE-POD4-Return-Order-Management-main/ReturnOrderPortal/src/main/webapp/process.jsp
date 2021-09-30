<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>


<meta charset="ISO-8859-1">
<title>Processing Details Page</title>
</head>
<style type="text/css">
body, table, td, a {
	-webkit-text-size-adjust: 100%;
	-ms-text-size-adjust: 100%;
}

table, td {
	mso-table-lspace: 0pt;
	mso-table-rspace: 0pt;
}

img {
	-ms-interpolation-mode: bicubic;
}

img {
	border: 0;
	height: auto;
	line-height: 100%;
	outline: none;
	text-decoration: none;
}

table {
	border-collapse: collapse !important;
}

body {
	height: 100% !important;
	margin: 0 !important;
	padding: 0 !important;
	width: 100% !important;
    text-align: center;

}

a[x-apple-data-detectors] {
	color: inherit !important;
	text-decoration: none !important;
	font-size: inherit !important;
	font-family: inherit !important;
	font-weight: inherit !important;
	line-height: inherit !important;
}

@media screen and (max-width: 480px) {
	.mobile-hide {
		display: none !important;
	}
	.mobile-center {
		text-align: center !important;
	}
}

div[style*="margin: 16px 0;"] {
	margin: 0 !important;
}
</style>
<body
	style="margin: 0 !important; padding: 0 !important; background-color: #eeeeee;"
	bgcolor="#eeeeee">

	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td align="center" style="background-color: #eeeeee;"
				bgcolor="#eeeeee">
				<table align="center" border="0" cellpadding="0" cellspacing="0"
					width="100%" style="max-width: 600px;">
					<tr>
						<td align="center" valign="top"
							style="font-size: 0; padding: 35px;" bgcolor="#F44336">
							<div
								style="display: inline-block; max-width: 50%; min-width: 100px; vertical-align: top; width: 100%;">
								<table align="left" border="0" cellpadding="0" cellspacing="0"
									width="100%" style="max-width: 300px;">
									<tr>
										<td align="left" valign="top"
											style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 36px; font-weight: 800; line-height: 48px;"
											class="mobile-center">
											<h5
												style="font-size: 36px; font-weight: 800; margin: 0; color: #ffffff;">Processing
												Details</h5>
										</td>
									</tr>
								</table>
							</div>
							<div
								style="display: inline-block; max-width: 50%; min-width: 100px; vertical-align: top; width: 100%;"
								class="mobile-hide">
								<table align="left" border="0" cellpadding="0" cellspacing="0"
									width="100%" style="max-width: 300px;">
									<tr>

									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center"
							style="padding: 35px 35px 20px 35px; background-color: #ffffff;"
							bgcolor="#ffffff">
							<table align="center" border="0" cellpadding="0" cellspacing="0"
								width="100%" style="max-width: 600px;">
								<tr>
									<td align="center"
										style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 25px;">
										<img
										src="https://img.icons8.com/carbon-copy/100/000000/checked-checkbox.png"
										width="125" height="120" style="display: block; border: 0px;" /><br>
										<h2
											style="font-size: 30px; font-weight: 800; line-height: 36px; color: #333333; margin: 0;">
											Confirm Your Order Return!</h2>
									</td>
								</tr>
								<tr>
									<td align="left"
										style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 10px;">
										<p
											style="font-size: 16px; font-weight: 400; line-height: 24px; color: #777777;">
											The Processing Details of Your Return Order are as follows.</p>
									</td>
								</tr>
								<tr>
									<td align="left" style="padding-top: 20px;">
										<table cellspacing="0" cellpadding="0" border="0" width="100%">
											<tr>
												<td width="75%" align="left" bgcolor="#eeeeee"
													style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;">
													Order Request ID#</td>
												<td width="25%" align="left" bgcolor="#eeeeee"
													style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;">
													${requestId}</td>
											</tr>
											<tr>
												<td width="75%" align="left"
													style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;">User
													ID#</td>
												<td width="25%" align="left"
													style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;">
													${userID}</td>
											</tr>
											<tr>
												<td width="75%" align="left"
													style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
													Packaging and Delivery Charge</td>
												<td width="25%" align="left"
													style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
													${packagingAndDeliveryCharge}</td>
											</tr>

										</table>
									</td>
								</tr>
								<!-- 								<div> -->
								<!-- 									<a href="/payment">Confirm Payment?</a> -->
								<!-- 								<form role="form" method="POST" action="addprocessRequest">	 -->
								<!-- 									<label>  -->
								<!-- 									Do you want to proceed with payment: -->
								<!-- 									<input type="radio" value="true" id="confirmation" name="confirmation"> Yes -->
								<!-- 									<input type="radio" value="false" id="confirmation" name="confirmation"> No -->
								<!-- 								</label> -->
								<!-- 								<a href="/payment" class="btn btn-success">Confirm Payment?</a> -->
								<!-- 								</form> -->
								<!-- 								</div> -->


								<tr>
									<td align="left" style="padding-top: 20px;">
										<table cellspacing="0" cellpadding="0" border="0" width="100%">
											<tr>
												<td width="75%" align="left"
													style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;">
													Processing charge</td>
												<td width="25%" align="left"
													style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;">
													${processingCharge}</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td align="center" height="100%" valign="top" width="100%"
							style="padding: 0 35px 35px 35px; background-color: #ffffff;"
							bgcolor="#ffffff">
							<table align="center" border="0" cellpadding="0" cellspacing="0"
								width="100%" style="max-width: 660px;">
								<tr>
									<td align="center" valign="top" style="font-size: 0;">
										<div
											style="display: inline-block; max-width: 50%; min-width: 240px; vertical-align: top; width: 100%;">
											<table align="left" border="0" cellpadding="0"
												cellspacing="0" width="100%" style="max-width: 300px;">
												<tr>
													<td align="left" valign="top"
														style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px;">
														<p style="font-weight: 800;">Estimated Delivery Date</p>
														<p>${dateOfDelivery}</p>
													</td>
												</tr>
											</table>
										</div>
										<div
											style="display: inline-block; max-width: 50%; min-width: 240px; vertical-align: top; width: 100%;">

										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>


				</table>
			</td>
		</tr>
	</table>
	<form role="form" method="POST" action="payment">
		<div class="form-group">
			<div class="col-md-12">
				<div class="checkbox">
					<label> Do you want to proceed with payment: 
					<input type="radio" value="true" id="confirmation" name="confirmation">Yes 
					<input type="radio" value="false" id="confirmation" name="confirmation" required> No
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

</body>
</html>




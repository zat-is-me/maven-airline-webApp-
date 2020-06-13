<%@ page import="java.util.*, com.airline.models.*" %>
<!DOCTYPE html>
<html>
<head>
	<style>
		body {
			font-family: arial, sans-serif;
			font-size: 14px;
		}
	</style>
</head>
<body>
	<h1>Adding Passenger</h1>
	
	<form method="POST" action="AddPassenger">

		First name:   

		<input name="first_name" type="text"></input>

		<br /><br />

		Last name:  

		<input name="last_name" type="text"></input>

		<br /><br />

		Date of birth:   

		<input name="dob" type="text"></input>

		<br /><br />

		Gender:  

		<select name="gender">
			<option value="Female">Female</option>
			<option value="Male">Male</option>
		</select>

		<hr />
		
		<button type="submit">Add passenger</button>

		<hr />

	</form>

		<h1>Add passenger to flight</h1>

	<form method="POST" action="AddPassengerToFlight">

		Add a passenger with an id of 

		<input name="pid" type="text"></input>

		to a flight with an id of 

		<input name="fid" type="text"></input>		

		<hr />
		<button type="submit">Add passenger to flight</button>
				
		<hr />			
	</form>

</body>

</html>
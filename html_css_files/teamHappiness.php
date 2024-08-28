<!DOCTYPE html>
<!-- saved from url=(0030)http://localhost:8585/#landing -->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="assets.css">
		<link rel="icon" href="Ericsson_Logo_Blue.png">
		<title>Polling tool</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	</head>
	
	<body>
		<link rel="stylesheet" type="text/css" href="thankYouPage.css">
		
		<div class="eaContainer-SystemBarHolder">
			<div class="eaContainer-SystemBar">
				<div class="eaContainer-SystemBar-left">
					<a tabindex="-1" class="eaContainer-SystemBar-logoAnchor"><div class="eaContainer-SystemBar-logo"></div></a>
					<div class="eaContainer-SystemBar-name"><a tabindex="-1" class="eaContainer-SystemBar-nameValue">Polling tool</a></div>
				</div>
			</div>
		</div>

		<div>
			<?php
				$link = mysqli_connect("localhost", "root", "SHroot@123", "surveytest");

				// Check connection
				if($link === false)
				{
					die("ERROR: Could not connect. " . mysqli_connect_error());
				}

				$team = $_POST['team'];
				$mission = $_POST['mission'];
				$missionC = $_POST['missionC']; 
				$mastery = $_POST['mastery'];
				$masteryC = $_POST['masteryC'];
				$organization = $_POST['organization'];
				$organizationC = $_POST['organizationC'];
				$empowered = $_POST['empowered'];
				$empoweredC = $_POST['empoweredC'];

				// attempt insert query execution
				$sql = "INSERT INTO team_happiness (Date, team, mission, mission_comment, mastery, mastery_comment, organization, organization_comment, empowered, empowered_comment) VALUES (now(), '$team', '$mission', '$missionC', '$mastery', '$masteryC', '$organization', '$organizationC', '$empowered', '$empoweredC')";
				if(mysqli_query($link, $sql))
				{
					#echo "Records added successfully.";
				} 
				else
				{
					#echo "ERROR: Could not able to execute $sql. " . mysqli_error($link);
				}
				
				// close connection
				mysqli_close($link);
			?>
											
			<body>
				<div class="thanks">
					<p align="center"><img src="Check.png" alt="saved" style="width:200px;height:200px;" align="center"></p>
					<p id="ty" align="center">Thank You!</p>
					<p id="sub" align="center">Your Submission has been Received</p>
					
					<meta http-equiv="refresh" content="3;url=http://atclvm1381.athtem.eei.ericsson.se:3032/home" />
					
					<div style="clear: both;"></div>
				</div>
			</body>
		</div>
	</body>
</html>
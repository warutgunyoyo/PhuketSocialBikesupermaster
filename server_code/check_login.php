<?php

header("content-type:text/html;charset=utf-8");


$con = mysqli_connect ( "localhost", "root", "", "testgun" );

//ทำให้เป็นภาษาไทย
 mysqli_query($con,"SET NAMES UTF8");


$strUsername = $_GET["strUser"];
$strPassword = $_GET["strPass"];
	
$query_code="SELECT * FROM login WHERE 1 
		AND username = '".$strUsername."'  
		AND userpassword = '".$strPassword."'  
		";


$result = mysqli_query($con,$query_code);



$row = array();

while($r = mysqli_fetch_assoc($result)){
	$row[] = $r;
}

$json= json_encode($row);
echo $json;




mysqli_close ( $con );


?>
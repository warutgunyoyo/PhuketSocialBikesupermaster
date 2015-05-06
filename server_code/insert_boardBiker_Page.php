<?php
header("content-type:text/html;charset=utf-8");

$con = mysqli_connect ( "mysql.hostinger.in.th", "u939235216_1234", "1100501032026", "u939235216_dbpk" );

//ทำให้เป็นภาษาไทย
 mysqli_query($con,"SET NAMES UTF8");

$text_evant_news = $_GET ['board_editText'];

$sql = "INSERT INTO `login`(`text`)
VALUES ('".$text_evant_news."')";

if (! mysqli_query ( $con, $sql )) {
	$result = mysqli_error ( $con );
	echo $result;
} else {
	$result = "1 record added";
	
	echo "Success";
}

mysqli_close ( $con );


>
<?php

if(isset($_POST) && !empty($_POST)){
	$username = $_POST['user'];
	$password = $_POST['pin'];
	$mysqli = new mysqli('localhost', 'alexbamm_root', 'nissan123', 'alexbamm_unitbv');
	$q = "SELECT `id`, `fullname`, `age`, `gender`, `email`, `address`, `registered`, `CNP` FROM `bambank_users` WHERE `email`='" . $mysqli->real_escape_string($username) . "' AND `password`='" . $mysqli->real_escape_string($password) . "' LIMIT 1;";
	$query = $mysqli->query($q);
	if($query->num_rows > 0){
		die(json_encode(array('result' => 'OK', 'details' => $query->fetch_assoc())));
	}else{
		die(json_encode(array('result' => 'NOT_OK', 'error_msg' => 'Username or password incorrect.')));	
	}
}else{
	die(json_encode(array('result' => 'NOT_OK', 'error_msg' => 'INCORRECT POST REQUEST')));
}


?>
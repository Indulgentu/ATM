<?php

	$mysqli = new mysqli('localhost', 'username', 'pass', 'alexbamm_unitbv');
	$q = "SELECT * FROM `bambank_accounts` WHERE `userid`='" . $mysqli->real_escape_string($_GET['userid']) . "'";
	if(!$query = $mysqli->query($q)) { die(json_encode(array("result" => "NOT_OK", "error_msg" => $mysqli->error))); }
	$arr = array();
	$i = 0;
	while ($row = $query->fetch_assoc()) {
		$arr[$i] = array("id" => $row['id'], "IBAN" => $row['IBAN'], "type" => $row['type'], "accID" => $row['accID'], "currency" =>  $row['currency'], "userid" => $row['userid'], "balance" => $row['balance']);
		$i++;
	}
	die(json_encode(array("result" => "OK", "accounts" => $arr)));
?>
<?php
if(isset($_POST)){
    $user = json_decode($_POST['account'], true);
    $mysqli = new mysqli('localhost', 'username', 'pass', 'alexbamm_unitbv');
	$result = $mysqli->query("SELECT * FROM `bambank_accounts` WHERE `accID`='" . $mysqli->real_escape_string($user['accID']) . "' LIMIT 1;");
	$result2 = $mysqli->query("SELECT * FROM `bambank_accounts` WHERE `userid`='" . $mysqli->real_escape_string($user['userid']) . "' LIMIT 1;");
    if($result->num_rows > 0){
		if(!$mysqli->query("UPDATE `bambank_accounts` SET `IBAN`='" . $mysqli->real_escape_string($user['IBAN']) . "', `type`='" . $mysqli->real_escape_string($user['type']) . "', `currency`='" . $mysqli->real_escape_string($user['currency']) . "', `balance`='" . $mysqli->real_escape_string($user['balance']) . "' WHERE `accID`='" . $mysqli->real_escape_string($user['accID']) . "';")){
			die(json_encode(array("result" => "NOT_OK", "error_msg" => $mysqli->error)));
		}
		die(json_encode(array("result" => "OK")));
	}elseif($result2->num_rows == 1){
		if(!$mysqli->query("UPDATE `bambank_accounts` SET `IBAN`='" . $mysqli->real_escape_string($user['IBAN']) . "', `type`='" . $mysqli->real_escape_string($user['type']) . "', `accID`='" . $mysqli->real_escape_string($user['accID']) . "', `currency`='" . $mysqli->real_escape_string($user['currency']) . "', `balance`='" . $mysqli->real_escape_string($user['balance']) . "' WHERE `userid`='" . $mysqli->real_escape_string($user['userid']) . "';")){
			die(json_encode(array("result" => "NOT_OK", "error_msg" => $mysqli->error)));
		}
		die(json_encode(array("result" => "OK")));	
	}else{
		if(!$result = $mysqli->query("INSERT INTO `bambank_accounts` (`id`, `IBAN`, `type`, `accID`, `currency`, `accID`, `balance`) VALUES (NULL, '" . $mysqli->real_escape_string($user['IBAN']) . "', '" . $mysqli->real_escape_string($user['type']) . "', '" . $mysqli->real_escape_string($user['currency']) . "', '" . $mysqli->real_escape_string($user['accID']) . "', '" . $mysqli->real_escape_string($user['balance']) . "');")){
			die(json_encode(array("result" => "NOT_OK", "error_msg" => $mysqli->error)));
		}
		die(json_encode(array("result" => "NEW_OK", "new_id" => $mysqli->insert_id)));
	}
//	if(!$query = $mysqli->query($q)) { die(json_encode(array("result" => "NOT_OK", "error_msg" => $mysqli->error))); }
}
?>
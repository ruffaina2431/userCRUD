<?php
$servername = "192.168.100.175"; // keep as 'localhost' if PHP is running on WAMP
$username = "admin";
$password = "qwerty123";
$database = "accounts";

// Create connection
$conn = new mysqli($servername, $username, $password, $database);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>

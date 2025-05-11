<?php
header('Content-Type: application/json');

// Database connection
$host = '192.168.100.175';  // Use your server IP here if remote
$dbname = 'accounts';
$username = 'admin';
$password = 'qwerty123';  // Your password

$conn = new mysqli($host, $username, $password, $dbname);

if ($conn->connect_error) {
    die(json_encode(["error" => "Connection failed: " . $conn->connect_error]));
}

// Get user ID from request
$id = $_GET['id'];  // GET parameter

// Delete user from database
$sql = "DELETE FROM users WHERE id = $id";

if ($conn->query($sql) === TRUE) {
    echo json_encode(["message" => "User deleted successfully"]);
} else {
    echo json_encode(["error" => "Error: " . $conn->error]);
}

$conn->close();
?>

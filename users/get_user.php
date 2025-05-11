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

// Fetch user data
$sql = "SELECT id, name, email FROM users WHERE id = $id";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $user = $result->fetch_assoc();
    echo json_encode($user);
} else {
    echo json_encode(["error" => "User not found"]);
}

$conn->close();
?>

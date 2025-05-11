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

// Fetch all users
$sql = "SELECT id, name, email FROM users";
$result = $conn->query($sql);

$users = [];
while ($row = $result->fetch_assoc()) {
    $users[] = $row;
}

echo json_encode($users);

$conn->close();
?>
 
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

// Get input data
$data = json_decode(file_get_contents("php://input"));
$name = $data->name;
$email = $data->email;
$password = password_hash($data->password, PASSWORD_BCRYPT);  // Hash the password

// Insert user into database
$sql = "INSERT INTO users (name, email, password) VALUES ('$name', '$email', '$password')";

if ($conn->query($sql) === TRUE) {
    echo json_encode(["message" => "User created successfully"]);
} else {
    echo json_encode(["error" => "Error: " . $conn->error]);
}

$conn->close();
?>

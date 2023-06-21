<?php

require_once './Config.class.php';

class UserDao
{
    private $conn;

    public function __construct()
    {
        $this->conn = Config::getInstance();
    }

    public function registerUser($firstName, $lastName, $email, $password)
    {
        $hashedPassword = password_hash($password, PASSWORD_BCRYPT);

        $insertStmt = $this->conn->prepare("
            INSERT INTO users (first_name, last_name, email, password)
            VALUES (:firstName, :lastName, :email, :password)
        ");

        $insertStmt->bindParam(':firstName', $firstName);
        $insertStmt->bindParam(':lastName', $lastName);
        $insertStmt->bindParam(':email', $email);
        $insertStmt->bindParam(':password', $hashedPassword);

        if ($insertStmt->execute()) {
            return "Registration successful";
        } else {
            return "Registration failed";
        }
    }

    public function loginUser($email, $password)
    {
        $selectStmt = $this->conn->prepare("SELECT * FROM users WHERE email = :email");
        $selectStmt->bindParam(':email', $email);
        $selectStmt->execute();

        if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            return "Invalid email format";
        }

        if ($selectStmt->rowCount() > 0) {
            $user = $selectStmt->fetch(PDO::FETCH_ASSOC);
            $hashedPassword = $user['password'];

            if (password_verify($password, $hashedPassword)) {
                return array('message' => 'Login successful', 'user' => $user);
            } else {
                return "Invalid password";
            }
        } else {
            return "User not found";
        }
    }

    public function updateUser($userId, $firstName, $lastName, $email, $password)
    {
        $updateStmt = $this->conn->prepare("
            UPDATE users
            SET first_name = :firstName, last_name = :lastName, email = :email, password = :password
            WHERE id = :userId
        ");

        // Hash the password
        $hashedPassword = password_hash($password, PASSWORD_BCRYPT);

        $updateStmt->bindParam(':firstName', $firstName);
        $updateStmt->bindParam(':lastName', $lastName);
        $updateStmt->bindParam(':email', $email);
        $updateStmt->bindParam(':password', $hashedPassword);
        $updateStmt->bindParam(':userId', $userId);

        if ($updateStmt->execute()) {
            return "User information updated successfully";
        } else {
            return "Failed to update user information";
        }
    }

    public function getUser($userId)
    {
        $selectStmt = $this->conn->prepare("SELECT * FROM users WHERE id = :userId");
        $selectStmt->bindParam(':userId', $userId);
        $selectStmt->execute();

        $user = $selectStmt->fetch(PDO::FETCH_ASSOC);

        return $user;
    }
}

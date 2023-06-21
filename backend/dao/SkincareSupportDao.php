<?php

require_once './Config.class.php';

class SkincareSupportDao
{
    private $conn;

    public function __construct()
    {
        $this->conn = Config::getInstance();
    }

    public function createSupportRequest($firstName, $lastName, $email, $subject)
    {
        $insertStmt = $this->conn->prepare("
            INSERT INTO skincare_support (first_name, last_name, email, subject)
            VALUES (:firstName, :lastName, :email, :subject)
        ");

        $insertStmt->bindParam(':firstName', $firstName);
        $insertStmt->bindParam(':lastName', $lastName);
        $insertStmt->bindParam(':email', $email);
        $insertStmt->bindParam(':subject', $subject);

        if ($insertStmt->execute()) {
            return "Support request created successfully";
        } else {
            return "Failed to create support request";
        }
    }
}

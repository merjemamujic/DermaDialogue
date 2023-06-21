<?php

require_once './Config.class.php';

class PostsDao
{
    private $conn;

    public function __construct()
    {
        $this->conn = Config::getInstance();
    }

    // Add a new method to retrieve all blog posts from the database
    public function getAllPosts()
    {
        $selectStmt = $this->conn->prepare("SELECT * FROM posts");
        $selectStmt->execute();
        return $selectStmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function addPost($blogName, $datePosted, $description, $type)
    {
        $insertStmt = $this->conn->prepare("
            INSERT INTO posts (blog_name, date_posted, description, type)
            VALUES (:blogName, :datePosted, :description, :type)
        ");

        $insertStmt->bindParam(':blogName', $blogName);
        $insertStmt->bindParam(':datePosted', $datePosted);
        $insertStmt->bindParam(':description', $description);
        $insertStmt->bindParam(':type', $type);

        if ($insertStmt->execute()) {
            return "Post added successfully";
        } else {
            return "Failed to add post";
        }
    }

    public function editPost($postId, $blogName, $datePosted, $description)
    {
        $updateStmt = $this->conn->prepare("
            UPDATE posts
            SET blog_name = :blogName, date_posted = :datePosted, description = :description
            WHERE id = :postId
        ");

        $updateStmt->bindParam(':blogName', $blogName);
        $updateStmt->bindParam(':datePosted', $datePosted);
        $updateStmt->bindParam(':description', $description);
        $updateStmt->bindParam(':postId', $postId);

        if ($updateStmt->execute()) {
            return "Post updated successfully";
        } else {
            return "Failed to update post";
        }
    }

    public function deletePost($postId)
    {
        $deleteStmt = $this->conn->prepare("
            DELETE FROM posts
            WHERE id = :postId
        ");

        $deleteStmt->bindParam(':postId', $postId);

        if ($deleteStmt->execute()) {
            return "Post deleted successfully";
        } else {
            return "Failed to delete post";
        }
    }
}

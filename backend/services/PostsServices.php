<?php

require_once './dao/PostsDao.php';

class PostsServices
{
    private $postDao;

    public function __construct()
    {
        $this->postDao = new PostsDao();
    }

    public function getAllPosts()
    {
        return $this->postDao->getAllPosts();
    }

    public function addPost($blogName, $description, $type)
    {
        // Get the current date and time
        $datePosted = date("Y-m-d H:i:s");

        // Add the post
        $message = $this->postDao->addPost($blogName, $datePosted, $description, $type);
        return $message;
    }

    public function editPost($postId, $blogName, $description)
    {
        // Get the current date and time
        $datePosted = date("Y-m-d H:i:s");

        // Update the post
        $message = $this->postDao->editPost
        ($postId, $blogName, $datePosted, $description);
        return $message;
    }

    public function deletePost($postId)
    {
        // Delete the post
        $message = $this->postDao->deletePost($postId);
        return $message;
    }
}

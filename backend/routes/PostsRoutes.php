<?php

require_once './services/PostsServices.php';

Flight::route('GET /get_all_posts', function()
{
    $postServices = new PostsServices();
    $posts = $postServices->getAllPosts();

    Flight::json($posts, 200);
});

Flight::route('POST /add_post', function()
{
    $blogName = Flight::request()->data['blogName'];
    $description = Flight::request()->data['description'];
    $type = Flight::request()->data['type'];

    $postServices = new PostsServices();
    $message = $postServices->addPost($blogName, $description, $type);

    Flight::json(array('message' => $message), 200);
});

Flight::route('PUT /edit_post/@id', function($id)
{
    $data = Flight::request()->getBody(); 
    $postData = json_decode($data, true); 
    
    $blogName = $postData['blogName'];
    $description = $postData['description'];

    $postServices = new PostsServices();
    $message = $postServices->editPost($id, $blogName, $description);

    Flight::json(array('message' => $message), 200);
});


Flight::route('DELETE /delete_post/@id', function($id)
{
    $postServices = new PostsServices();
    $message = $postServices->deletePost($id);

    Flight::json(array('message' => $message), 200);
});

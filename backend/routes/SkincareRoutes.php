<?php

require_once './services/SkincareSupportServices.php';

Flight::route('POST /support_request', function()
{
    $firstName = Flight::request()->data['firstName'];
    $lastName = Flight::request()->data['lastName'];
    $email = Flight::request()->data['email'];
    $subject = Flight::request()->data['subject'];

    // Validate input fields
    if (empty($firstName) || empty($lastName) || empty($email) || empty($subject)) {
        Flight::json(array('message' => 'Please provide all required fields'), 400);
        return;
    }

    // Validate email format
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        Flight::json(array('message' => 'Invalid email format'), 400);
        return;
    }

    $skincareSupportServices = new SkincareSupportServices();
    $message = $skincareSupportServices->createSupportRequest(
        $firstName,
        $lastName,
        $email,
        $subject);
    Flight::json(array('message' => $message), 200);
});

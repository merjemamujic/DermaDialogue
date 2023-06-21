<?php

require_once './dao/SkincareSupportDao.php';

class SkincareSupportServices
{
    private $skincareSupportDao;

    public function __construct()
    {
        $this->skincareSupportDao = new SkincareSupportDao();
    }

    public function createSupportRequest($firstName, $lastName, $email, $subject)
    {
        $message = $this->skincareSupportDao->createSupportRequest(
            $firstName,
            $lastName,
            $email,
            $subject
        );
        return $message;
    }
}

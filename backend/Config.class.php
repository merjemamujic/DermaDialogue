<?php

class Config
{
    private static $instance;
    private $connection;

    private function __construct() 
    {
        try {
            $dbHost = 'sql.freedb.tech';
            $dbName = 'freedb_skin_care';
            $dbUser = 'freedb_ajla_merjema';
            $dbPassword = 'XPgW$bAs5S5A4&6';

            $this->connection = new PDO("mysql:host=$dbHost;
            dbname=$dbName", $dbUser, $dbPassword);
            $this->connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $e) {
            die("Connection failed: " . $e->getMessage());
        }
    }

    public static function getInstance()
    {
        if (!self::$instance) {
            self::$instance = new Config();
        }

        return self::$instance->connection;
    }
}

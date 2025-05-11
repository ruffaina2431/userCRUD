package com.example.usercrud.network;

public class endpoints {
    private static final String BASE_URL = "http://192.168.100.175/users/"; // Replace with your WAMP IP & folder

    public static final String CREATE = BASE_URL + "create_user.php";
    public static final String READ_ALL = BASE_URL + "fetch_users.php";
    public static final String READ_ONE = BASE_URL + "get_user.php";
    public static final String UPDATE = BASE_URL + "update_user.php";
    public static final String DELETE = BASE_URL + "delete_user.php";
}
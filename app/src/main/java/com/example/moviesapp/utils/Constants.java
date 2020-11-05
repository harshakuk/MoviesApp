package com.example.moviesapp.utils;

public class Constants {

    private static String BASE_URL = "https://api.themoviedb.org/3/";
    private static String API_KEY = "e5f9944e8d56d79ac512b7189c56d3e7";
    private static Integer CACHE_TIME = 6000;

    public static String getBASE_URL() {
        return BASE_URL;
    }

    public static Integer getCACHE_TIME() {
        return CACHE_TIME;
    }

    public static String getAPI_KEY() {
        return API_KEY;
    }
}

package com.example.moviesapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moviesapp.deps.DaggerDeps;
import com.example.moviesapp.deps.Deps;
import com.example.moviesapp.networking.NetworkModule;

import java.io.File;

public class BaseApp extends AppCompatActivity {
    Deps deps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
    }

    public Deps getDeps() {
        return deps;
    }
}

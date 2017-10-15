package com.sania.booklistingapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class Home extends AppCompatActivity {

    Button response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        response=(Button)findViewById(R.id.response);
        response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FetchItemsTask().execute();
            }
        });
    }
    private class FetchItemsTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                String result = new BooksFetcher().fetchBooks();
                Log.i("Tag", "Fetched contents of URL: " + result);
            } catch (Exception io) {
                Log.e("Tag", "Failed to fetch URL: ", io);
            }
            return null;
        }
    }
}

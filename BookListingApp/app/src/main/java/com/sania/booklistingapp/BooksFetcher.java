package com.sania.booklistingapp;

import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sania on 16/05/2017.
 */

public class BooksFetcher {

    public byte[] getURLBytes(String urlSpec) throws IOException{


        URL url=new URL(urlSpec);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        //connection.setRequestMethod("GET");
        try{

            ByteArrayOutputStream output=new ByteArrayOutputStream();
            InputStream input=connection.getInputStream();

            if (connection.getResponseCode()!=HttpURLConnection.HTTP_OK){

                throw new IOException(connection.getResponseMessage()+"with"+urlSpec);
            }

            int byteRead=0;
            byte[] buffer=new byte[1024];
            while((byteRead=input.read(buffer))>0){
                output.write(buffer,0,byteRead);

            }
            output.close();
            return output.toByteArray();

        }finally {
            connection.disconnect();
        }
    }

    public String getURLString(String URLSpec) throws IOException{
        return new String(getURLBytes(URLSpec));
    }

    public String fetchBooks(){
        String jsonString="";
        try {
            String url = Uri.parse("https://www.googleapis.com/books/key=AIzaSyCVSbpCxBfjUswIx_mmtvyNLdg0gxNp6z8")
                    .toString();
            jsonString = getURLString(url);

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonString;
    }
}


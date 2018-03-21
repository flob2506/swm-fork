package com.group.tube.networking;

import android.os.AsyncTask;

import org.json.JSONArray;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkTask extends AsyncTask {
    private OkHttpClient httpClient;
    public AsyncResponse delegate = null;

    public NetworkTask() {
        this.httpClient = new OkHttpClient();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            return this.run((String)objects[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Object object){
        delegate.processFinish((String)object);
    }

    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = this.httpClient.newCall(request).execute();
        return response.body().string();
    }
}


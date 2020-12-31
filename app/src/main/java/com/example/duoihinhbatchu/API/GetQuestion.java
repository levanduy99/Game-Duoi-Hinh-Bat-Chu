package com.example.duoihinhbatchu.API;

import android.os.AsyncTask;

import com.example.duoihinhbatchu.Data;
import com.example.duoihinhbatchu.object.Riddle;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
// AsyncTask chay ngam trong android
public class GetQuestion extends AsyncTask<Void,Void,Void> {
    String data;
    @Override
    protected Void doInBackground(Void... voids) {
        //client co nhiem vu goi den API --> get data
        OkHttpClient client = new OkHttpClient();
        //request --> declare address API
        Request request = new Request.Builder()
                .url ("http://192.168.56.1/DuoiHinhBatChu/LayCauHoi.php")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            data = responseBody.string();
        } catch (IOException e) {
            data = null;
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (data != null) {
            try {
                Data.getData ().arrRiddle.clear ();
                JSONArray array = new JSONArray (data);
                for(int i = 0;i<array.length ();i++){
                    JSONObject o = array.getJSONObject (i);
                    Riddle r = new Riddle();
                    r.image = o.getString ("anh");
                    r.name = o.getString ("ten");
                    r.answer_correct = o.getString ("dapan");
                    Data.getData ().arrRiddle.add (r);
                }
            } catch (JSONException e) {
                e.printStackTrace ( );
            }

        } else {

        }
    }

}

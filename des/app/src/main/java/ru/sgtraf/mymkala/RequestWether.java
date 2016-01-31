package ru.sgtraf.mymkala;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by gad on 31.01.2016.
 */
public class RequestWether {
    private String line;
    // запрашиваем погоду с сервера

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();


    // code request code here
    String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public  void test () throws IOException {


        new RequestWeath().execute("http://vitoelexir.ru/android/weather.php");



    }





    // запрашиваем погоду с сервера

    class RequestWeath extends AsyncTask<String, String, String> {

        private final String Tag = "frag";

        @Override
        protected String doInBackground(String... params) {



            // создаем клиента
            try {
                // issue the Get request
                RequestWether example = new RequestWether();
                String getResponse = example.doGetRequest(params[0]);
                System.out.println(getResponse);
                line = getResponse;

            } catch (Exception e) {
                Log.i("Request exception", "Exception: " + e.getMessage()); // Oops
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            final ArrayList<HashMap<String, Object>> myBooks = new ArrayList<HashMap<String, Object>>();

           // TextView textViewAir = (TextView) rootView.findViewById(R.id.textView23);
          //  TextView textViewWater = (TextView) rootView.findViewById(R.id.textView24);


            try {

                System.out.println(line);


            } catch (Exception e) {
                Log.e("log_tag", "Error parsing data " + e.toString());
            }


            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
    }






}

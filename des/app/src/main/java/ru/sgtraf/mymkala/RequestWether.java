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
import java.util.concurrent.ExecutionException;

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
    private ArrayList<HashMap<String, Object>> result;

    // запрашиваем погоду с сервера

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    private AsyncTask<String, Void, ArrayList<HashMap<String, Object>>> Wether;


    // code request code here
    String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public  ArrayList<HashMap<String, Object>> test () throws IOException {


       Wether =   new RequestWeath();
       Wether.execute("http://vitoelexir.ru/android/weather.php");
        try {
            result = Wether.get();
        //    System.out.println(result.get(0).get("temperature").toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


return result;
    }





    // запрашиваем погоду с сервера

    class RequestWeath extends AsyncTask<String, Void, ArrayList<HashMap<String, Object>>> {

        private final String Tag = "frag";

        @Override
        protected ArrayList<HashMap<String, Object>> doInBackground(String... params) {



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

            final ArrayList<HashMap<String, Object>> myBooks = new ArrayList<HashMap<String, Object>>();

            //  TextView textViewAir = (TextView) rootView.findViewById(R.id.textView23);
            //   TextView textViewWater = (TextView) rootView.findViewById(R.id.textView24);


            try {

                // создали читателя json объектов и отдали ему строку - result
                JSONObject json = new JSONObject(line);
                // дальше находим вход в наш json им является ключевое слово
                // data
                JSONArray urls = json.getJSONArray("data");
                // проходим циклом по всем нашим параметрам
                for (int i = 0; i < urls.length(); i++) {
                    HashMap<String, Object> hm;
                    hm = new HashMap<String, Object>();

                    // читаем что в себе хранит параметр date
                    hm.put("temperature", urls.getJSONObject(i).getString("temperature"));

                    // читаем что в себе хранит параметр title
                    hm.put("water_temp", urls.getJSONObject(i).getString("water_temperature"));

                    myBooks.add(hm);

                }

                // дальше добавляем полученные параметры в наш текствью

                //  textViewAir.setText("Воздух " + myBooks.get(0).get("temperature").toString());
                //  textViewWater.setText("Вода " + myBooks.get(0).get("water_temp").toString());


                // Toast.makeText(getActivity(),  myBooks.get(0).get("temperature").toString() , Toast.LENGTH_SHORT).show();
             //   System.out.println(myBooks.get(0).get("temperature").toString());


            } catch (Exception e) {
                Log.e("log_tag", "Error parsing data " + e.toString());
            }


            return myBooks;
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, Object>> result) {


            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
    }






}

package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public JSONArray jarr =new JSONArray() ;
    public ArrayList<avatar> arrayList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView imageView = findViewById(R.id.imageView);
        ListView listView =findViewById(R.id.listView);

        new DownloadTask().execute("https://lebavui.github.io/jsons/users.json");


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList =new ArrayList<>();


                try {
                    for(int i =0 ; i<jarr.length(); i++){
                        JSONObject object = jarr.getJSONObject(i);
                        try {
                            JSONObject avatar = object.optJSONObject("avatar");
                            Log.v("TAG", "chay" +i);
                            arrayList.add( new avatar("https://lebavui.github.io/"+avatar.getString("thumbnail"),
                                    object.getString("username"), object.getString("email")));

                        } catch (Exception e){
                            e.printStackTrace();

                        }

                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                avatarAdapter AvatarAdapter = new avatarAdapter(MainActivity.this,arrayList,R.layout.avatar );
                listView.setAdapter(AvatarAdapter);
                imageView.setText("");



                Log.v("TAG", "on" + jarr.length());

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, inforActivity.class);
                try {
                    intent.putExtra("data", jarr.getJSONObject(position).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
                Log.v("TAG", "so "+ position);

            }
        });



    }
    class DownloadTask extends AsyncTask<String, Void, String> {
        StringBuffer content =new StringBuffer();
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line ;
                while ((line = bufferedReader.readLine()) !=null){
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return content.toString();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Log.v("TAG","1");
                jarr = new JSONArray(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(MainActivity.this, "Dữ Liệu đã tải về click để xem danh sách" , Toast.LENGTH_SHORT).show();
        }
    }
}
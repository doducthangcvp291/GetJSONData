package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class inforActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);

        ImageView imageView =findViewById(R.id.imageView2);
        TextView username = findViewById(R.id.username);
        TextView name = findViewById(R.id.nameinf);
        TextView email  = findViewById(R.id.emailinf);
        TextView address = findViewById(R.id.addressinf);
        TextView company = findViewById(R.id.companyinf);
        TextView Phone = findViewById(R.id.phoneinf);

        Intent intent = getIntent();

        try {
            JSONObject object=  new JSONObject(intent.getStringExtra("data"));
            JSONObject avatar = object.getJSONObject("avatar");
            JSONObject addr = object.getJSONObject("address");
            JSONObject geo  = addr.getJSONObject("geo");
            JSONObject compa = object.getJSONObject("company");
            Picasso.get().load("https://lebavui.github.io"+avatar.getString("photo")).into(imageView);
            username.setText("Username : "+object.getString("username"));
            name.setText("Name : "+object.getString("name"));
            email.setText("Email : "+object.getString("email"));
            address.setText("Address : "+addr.getString("street")+"  "+ addr.getString("suite")+ "   "+ addr.getString("city") + "\n Zipcode:" + addr.getString("zipcode" )+
                    "\n"
            + "geo"+ geo.getString("lat")+";"+geo.getString("lng"));

            company.setText("Company : "+compa.getString("name") +"\n"+ compa.getString("catchPhrase") +"\n"+ compa.getString("bs") + "\n\n" +"Website : "+object.getString("website"));
            Phone.setText("Phone : "+object.getString("phone"));




        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
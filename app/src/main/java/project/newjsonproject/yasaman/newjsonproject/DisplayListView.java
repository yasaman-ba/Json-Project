package project.newjsonproject.yasaman.newjsonproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String json_string_parse;

    JSONObject jsonObject;
    JSONArray jsonArray;

     String name;
     String password;
     String contact;
     String country;

     ContentAdapter contentAdapter;

     ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);

        contentAdapter = new ContentAdapter(this,R.layout.row_laout);

        listView= findViewById(R.id.listview_display);
        listView.setAdapter(contentAdapter);

        json_string_parse = getIntent().getExtras().getString("json_data");

        try {
            jsonObject = new JSONObject(json_string_parse);
            int count = 0;
            jsonArray=jsonObject.getJSONArray("server_response");

            while (count<jsonArray.length()){

                JSONObject JO = jsonArray.getJSONObject(count);

                name = JO.getString("name");
                password = JO.getString("password");
                contact = JO.getString("contact");
                country = JO.getString("country");


                Content content = new Content(name,password,contact,country);
                contentAdapter.add(content);
                count++;



            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

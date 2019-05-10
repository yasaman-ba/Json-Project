package project.newjsonproject.yasaman.newjsonproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String json_string;
    String json_string_parse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

        public void getjson(View view){

            new BackgroundTask().execute();

        }

        public void parsejson(View view){

            Intent intent = new Intent(MainActivity.this, DisplayListView.class);
            intent.putExtra("json_data", json_string_parse);
            startActivity(intent);


        }


    class BackgroundTask extends AsyncTask<Void,Void, String>{

        String json_url="http://yasbaprogrammer.com/Udemy_Databases/jsongetdata.php";

        TextView txt_main;


    @Override
     protected String doInBackground(Void... voids) {

        try {

            URL url = new URL(json_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();

            while ((json_string=bufferedReader.readLine())!=null){

                stringBuilder.append(json_string+"\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public BackgroundTask() {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {

        txt_main = findViewById(R.id.txt_main);
        txt_main.setText(result);

        json_string_parse = result;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

}

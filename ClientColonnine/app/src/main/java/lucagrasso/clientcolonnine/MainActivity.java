package lucagrasso.clientcolonnine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.util.Log;
import java.util.Map;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.RequestQueue;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MyActivity";
    private EditText latInput;
    private EditText lonInput;
    private EditText minpowerkwInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn = (Button) findViewById(R.id.cerca);
        latInput = (EditText) findViewById(R.id.Latitudine);
        lonInput = (EditText) findViewById(R.id.Longitudine);
        minpowerkwInput = (EditText) findViewById(R.id.minPowerKW);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://lumbar-check.glitch.me/trova-colonnine/?";
                url = url + "latitude=" + latInput.getText().toString() + "&";
                url = url + "longitude=" + lonInput.getText().toString() + "&";
                url = url + "minpowerkw=" + minpowerkwInput.getText().toString();


                    JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                                @Override
                                public void onResponse(JSONArray response) {
                                    //Toast.makeText(getApplicationContext(), response.length(), Toast.LENGTH_LONG).show();
                                    Log.i(TAG, response.toString() + response.length());
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.i(TAG, "errore" + error.getMessage() + error.toString());

                                }
                            });

                    MyApplication.getInstance().addToRequestQueue(jsonObjectRequest, MyApplication.TAG);



            }
        });
    }





}

package lucagrasso.clientcolonnine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

    private boolean isValidLat = false, isValidLon = false, isValidMin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // creo il layout della activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creo delle variabili che si riferiscono agli elementi della activity: bottone submit, caselle di testo
        final Button btn = (Button) findViewById(R.id.cerca);
        latInput = (EditText) findViewById(R.id.Latitudine);
        lonInput = (EditText) findViewById(R.id.Longitudine);
        minpowerkwInput = (EditText) findViewById(R.id.minPowerKW);

        btn.setEnabled(false);

        // applico un onclick listener al bottone: quando l'utente clicca sul bottone,
        // si apre la activity ListActivity e si passa a questa activity i dati
        // inseriti dall'utente (latitudine, longitudine, minpowerkw)
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), ListActivity.class);
                intent.putExtra("longitudine", lonInput.getText().toString());
                intent.putExtra("latitudine", latInput.getText().toString());
                intent.putExtra("minpowerkw", minpowerkwInput.getText().toString());

                startActivity(intent);
            }
        });


        // ogni casella di testo ha un listener che controlla se i dati inseriti sono numeri
        // validi, se così non è non viene attivato il bottone ricerca
        latInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Double.parseDouble(s.toString());
                    isValidLat = true;
                    if(isValidLon && isValidMin)
                        btn.setEnabled(true);
                } catch (NumberFormatException ex){
                    isValidLat = false;
                    btn.setEnabled(false);
                }
            }
        });

        lonInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Double.parseDouble(s.toString());
                    isValidLon = true;
                    if(isValidLat && isValidMin)
                        btn.setEnabled(true);
                } catch (NumberFormatException ex){
                    isValidLon = false;
                    btn.setEnabled(false);
                }
            }
        });

        minpowerkwInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Integer.parseInt(s.toString());
                    isValidMin = true;
                    if(isValidLon && isValidLat)
                        btn.setEnabled(true);
                } catch (NumberFormatException ex){
                    isValidMin = false;
                    btn.setEnabled(false);
                }
            }
        });
    }






}

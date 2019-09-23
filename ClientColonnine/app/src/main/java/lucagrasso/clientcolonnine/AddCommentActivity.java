package lucagrasso.clientcolonnine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddCommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // creo il layout della activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        // costruisco delle variabili che fanno riferimento al campo del commento e al bottone
        final Button btn = (Button) findViewById(R.id.inviaCommento);
        final EditText commento = (EditText) findViewById(R.id.commentoView);

        // imposto un listener al bottone, in modo che al click dell'utente invii il commento al servizio
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inviaCommento(getIntent().getIntExtra("id", 0), commento.getText().toString());

            }
        });
    }

    // metodo che chiama il servizio https://lumbar-check.glitch.me/storeComment/ per caricare un commento
    private void inviaCommento(final int id, final String commento) {

        String url = "https://lumbar-check.glitch.me/storeComment/?";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // se il commento viene inserito correttamente, compare un popup
                        Toast.makeText(AddCommentActivity.this,"commento inviato",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("commento",commento);
                params.put("colonnina","" + id);

                return params;
            }

        };

        // la request viene aggiunta alla coda della app
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}

package lucagrasso.clientcolonnine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    private ColonninaArrayAdapter arrayAdapter;

    private String TAG = "ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // creo il layout della activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // creo delle variabili che fanno riferimento agli elementi della activity: listview e casella di testo
        final ListView lv = (ListView) findViewById(R.id.lv);
        final TextView errore = (TextView) findViewById(R.id.niente);

        // recupero i dati dalla activity chiamante (MainActivity)
        String latitudine = getIntent().getStringExtra("latitudine");
        String longitudine = getIntent().getStringExtra("longitudine");
        String minpowerkw = getIntent().getStringExtra("minpowerkw");

        // costruisco l'url a cui fare la richiesta
        String url = "https://find-ev-charging-stations.glitch.me/trova-colonnine/?";
        url = url + "latitude=" + latitudine + "&";
        url = url + "longitude=" + longitudine + "&";
        url = url + "minpowerkw=" + minpowerkw;

        // creo la lista in cui verranno inserite le colonnine trovate
        final ArrayList<Colonnina> colonnine = new ArrayList<Colonnina>();

        // costruisco l'arrayadapter che gestisce il layout dell'elenco delle colonnine
        arrayAdapter = new ColonninaArrayAdapter(this, 0, colonnine);

        // collego l'arrayadapter alla listview
        lv.setAdapter(arrayAdapter);

        // creo la richiesta al servizio che cerca le colonnine
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // trasformo la risposta in un array di oggetti Colonnina
                            parseColonnine(colonnine, response);

                            // notifico all'adapter di aggiornare la activity
                            arrayAdapter.notifyDataSetChanged();

                            // se la lista di colonnine trovate è vuota o si verifica un errore,
                            // scrivo un messaggio di avvertimento
                            if(colonnine.isEmpty())
                                errore.setText("Nessuna colonnina trovata.");
                        } catch(Exception ex) {
                            errore.setText("Nessuna colonnina trovata.");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errore.setText("Nessuna colonnina trovata.");

                    }
                });

        // aggiungo la richiesta alla coda della app
        MyApplication.getInstance().addToRequestQueue(jsonObjectRequest, MyApplication.TAG);


    }

    // metodo che trasforma la risposta del servizio trova-colonnine in una List di Colonnina
    private void parseColonnine(ArrayList<Colonnina> colonnine, JSONArray arrayColonnine) throws JSONException {

        JSONObject colObject = null;
        for(int i=0; i<arrayColonnine.length(); i++){
            colObject = arrayColonnine.getJSONObject(i);
            Colonnina col = new Colonnina();

            col.setDistanza(colObject.get("distance").toString());
            col.setLatitudine(colObject.get("Latitude").toString());
            col.setLongitudine(colObject.get("Longitude").toString());
            col.setId(colObject.get("id").toString());

            colonnine.add(col);
        }

    }

    // classe che crea per ogni colonnina il suo elemento grafico da inserire nella pagina
    class ColonninaArrayAdapter extends ArrayAdapter {

        private Context context;
        private List<Colonnina> colonnine;
        private Colonnina col;

        public ColonninaArrayAdapter(Context context, int resource, ArrayList<Colonnina> objects) {
            super(context, resource, objects);

            this.context = context;
            this.colonnine = objects;
        }

        // creazione dell'elemento grafico che contiene i dati di una colonnina
        // questo metodo viene chiamato per ogni colonnina trovata
        public View getView(int position, View convertView, ViewGroup parent) {

            // recupero la colonnina da visualizzare dalla lista
            col = colonnine.get(position);

            // creo il layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.list_colonnina_item, null);

            // creo delle variabili che si riferiscono agli elementi grafici: aree di testo e bottoni
            TextView id_colonnina = (TextView) view.findViewById(R.id.id_colonnina);
            TextView coordinate = (TextView) view.findViewById(R.id.coordinate);

            Button inviaCommento = view.findViewById(R.id.commento);
            Button apriMappa = view.findViewById(R.id.apri_mappa);

            // aggiorno il testo delle aree di testo che contengono l'id della colonnina e le coordinate
            id_colonnina.setText("ID: " + col.getId());
            coordinate.setText("Coordinate: " + col.getLatitudine() + ", " + col.getLongitudine());

            // collego un onclicklistener al bottone INVIA COMMENTO
            inviaCommento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), AddCommentActivity.class);
                    intent.putExtra("id", col.getId() + "");
                    startActivity(intent);
                }
            });

            // collego un onclicklistener al bottone VAI ALLA MAPPA
            apriMappa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri gmmIntentUri = Uri.parse("google.navigation:q=" + col.getLatitudine()+","+col.getLongitudine());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            });

            return view;
        }
    }
}

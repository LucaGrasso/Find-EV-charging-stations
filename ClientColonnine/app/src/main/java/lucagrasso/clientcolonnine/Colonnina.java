package lucagrasso.clientcolonnine;

import android.os.Parcelable;
import android.os.Parcel;

public class Colonnina {

    private String latitudine;
    private String longitudine;
    private String distanza;
    private String id;

    public Colonnina(String latitudine, String longitudine, String distanza) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.distanza = distanza;
    }

    public Colonnina(){
    }


    public String getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    public String getDistanza() {
        return distanza;
    }

    public void setDistanza(String distanza) {
        this.distanza = distanza;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

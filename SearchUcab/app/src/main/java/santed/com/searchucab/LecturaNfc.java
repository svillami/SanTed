package santed.com.searchucab;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class LecturaNfc extends AppCompatActivity {

    public static final String MIME_TEXT_PLAIN = "text/plain";
    public static final String TAG = "NfcDemo";

    //private TextView mTextView;
    private NfcAdapter mNfcAdapter;

    public ProgressDialog pd;

    private Toolbar toolbar;

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    public TextView tderecha;
    public TextView tfrente;
    public TextView tizquierda;
    public TextView tatras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_nfc);

        this.setTitle("Busqueda por NFC");

        //Llamando al menu
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        pd = new ProgressDialog(LecturaNfc.this);
        //pd.setTitle("Esperando lectura del Tag NFC");
        pd.setMessage("Por favor, Acerque su dispositivo al NFC para su lectura.");
        pd.setCancelable(false);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        pd.show();



        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter == null) {
            //Aqui necesitamos del NFC
            Toast.makeText(this, "\n" + "Lo sentimos. Este dispositivo no es compatible con NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;

        }

        if (!mNfcAdapter.isEnabled()) {
            //mTextView.setText("NFC esta deshabilitado.");
            Toast.makeText(this, "NFC esta deshabilitado. Enciendalo para ubicarse", Toast.LENGTH_LONG).show();
        } else {
            //mTextView.setText(R.string.explanation);
            Toast.makeText(this, "Nada Sucedio", Toast.LENGTH_LONG).show();
        }

        handleIntent(getIntent());
    }




    @Override
    protected void onResume() {
        super.onResume();

        /**
         * Es importante , que la actividad este en el primer plano (continuación). De otra manera
         * un IllegalStateException se dispara.
         */
        setupForegroundDispatch(this, mNfcAdapter);
    }

    @Override
    protected void onPause() {
        /**
         * Llamar a esto antes del onPause , de lo contrario un IllegalArgumentException se disparará
         */
        stopForegroundDispatch(this, mNfcAdapter);

        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        /**
         * Este método se llama , cuando un nuevo intento se asocia con la instancia de actividad actual.
         * En lugar de crear una nueva actividad , será llamado onNewIntent .
         *
         *En nuestro caso, este método se llama cuando el dispositivo detecta el nfc.
         */
        handleIntent(intent);
    }


    /**
     * @param activity El correspondiente {@link Activity } que solicita la expedición de primer plano.
     * @param adapter El {@link NfcAdapter} utilizado para el despacho de primer plano.
     */
    public static void setupForegroundDispatch(final Activity activity, NfcAdapter adapter) {
        final Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        final PendingIntent pendingIntent = PendingIntent.getActivity(activity.getApplicationContext(), 0, intent, 0);

        IntentFilter[] filters = new IntentFilter[1];
        String[][] techList = new String[][]{};

        //Este es el mismo filtro como en nuestro AndroidManifest.
        filters[0] = new IntentFilter();
        filters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filters[0].addCategory(Intent.CATEGORY_DEFAULT);
        try {
            filters[0].addDataType(MIME_TEXT_PLAIN);
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("Compruebe su tipo MIME.");
        }

        adapter.enableForegroundDispatch(activity, pendingIntent, filters, techList);
    }

    /**
     * @param adapter El {@link NfcAdapter} utilizado para el despacho de primer plano
     */
    public static void stopForegroundDispatch(final Activity activity, NfcAdapter adapter) {
        adapter.disableForegroundDispatch(activity);
    }

    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {

            String type = intent.getType();
            if (MIME_TEXT_PLAIN.equals(type)) {

                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                new NdefReaderTask().execute(tag);

            } else {
                Log.d(TAG, "Wrong mime type: " + type);
            }
        } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {

            // En este caso todavia se esta leyendo del intent
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String[] techList = tag.getTechList();
            String searchedTech = Ndef.class.getName();

            for (String tech : techList) {
                if (searchedTech.equals(tech)) {
                    new NdefReaderTask().execute(tag);
                    break;
                }
            }
        }
    }

    /**
     * Tarea en segundo plano para la lectura de los datos. No bloquee el hilo de interfaz de usuario durante la lectura.
     *
     * @author Sandra Villamizar
     *
     */
    private class NdefReaderTask extends AsyncTask<Tag, Void, String> {


        @Override
        protected String doInBackground(Tag... params) {
            Tag tag = params[0];

            Ndef ndef = Ndef.get(tag);
            if (ndef == null) {
                // NDEF no soporta este tag.
                return null;
            }

            NdefMessage ndefMessage = ndef.getCachedNdefMessage();

            NdefRecord[] records = ndefMessage.getRecords();
            for (NdefRecord ndefRecord : records) {
                if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
                    try {
                        return readText(ndefRecord);
                    } catch (UnsupportedEncodingException e) {
                        Log.e(TAG, "\n" + "La codificación no es compatible", e);
                    }
                }
            }

            return null;
        }

        private String readText(NdefRecord record) throws UnsupportedEncodingException {

            byte[] payload = record.getPayload();

            // Obtengo el Text Encoding y sustitui el codigo del tutorial por este
            String textEncoding;
            if ((payload[0] & 128) == 0) textEncoding = "UTF-8";
            else textEncoding = "UTF-16";

            // Obtenengo el código de lenguaje en este caso es: (es - Español)
            int languageCodeLength = payload[0] & 0063;

            // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
            // e.g. "en"

            // Ontengo  el Texto
            return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        }

        // La variable result recibe el valor que tiene almacenado el nfc
        @Override
        protected void onPostExecute(String result) {

            pd.dismiss();

            if (result != null) {


                if (result.equals("1E")){
                    String titulo= getString(R.string.titulo_1);
                    String derecha= getString(R.string.mensaje_1_derecha);
                    String frente= getString(R.string.mensaje_1_frente);
                    String izquierda= getString(R.string.mensaje_1_izquierda);
                    String atras= getString(R.string.mensaje_1_atras);
                    createDialogCorrect(titulo, derecha, frente, izquierda, atras);
                } else if (result.equals("2E")){
                    String titulo= getString(R.string.titulo_2);
                    String derecha= getString(R.string.mensaje_2_derecha);
                    String frente= getString(R.string.mensaje_2_frente);
                    String izquierda= getString(R.string.mensaje_2_izquierda);
                    String atras= getString(R.string.mensaje_2_atras);
                    createDialogCorrect(titulo,derecha,frente,izquierda,atras);
                } else if (result.equals("3E")){
                    String titulo= getString(R.string.titulo_3);
                    String derecha= getString(R.string.mensaje_3_derecha);
                    String frente= getString(R.string.mensaje_3_frente);
                    String izquierda= getString(R.string.mensaje_3_izquierda);
                    String atras= getString(R.string.mensaje_3_atras);
                    createDialogCorrect(titulo,derecha,frente,izquierda,atras);
                } else if (result.equals("4E")){
                    String titulo= getString(R.string.titulo_4);
                    String derecha= getString(R.string.mensaje_4_derecha);
                    String frente= getString(R.string.mensaje_4_frente);
                    String izquierda= getString(R.string.mensaje_4_izquierda);
                    String atras= getString(R.string.mensaje_4_atras);
                    createDialogCorrect(titulo,derecha,frente,izquierda,atras);
                }
                else {
                    String mensaje= "Por favor verifique que sea un NFC de la Ucab";
                    //Este libro no es el correcto para devolver verifiquelo
                    createSimpleDialog(mensaje, result);
                }
            }
        }

    }

    //Agregando el Dialogo Correcto
    /*public AlertDialog createDialogCorrect(String Titulo, String mensaje) {

        AlertDialog.Builder builder = new AlertDialog.Builder(LecturaNfc.this);

        builder.setIcon(R.drawable.nfc_icon)
                .setTitle(Titulo)
                .setMessage(mensaje);
        builder.setCancelable(false);

        builder.setPositiveButton("Retornar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();

                return;
            }
        });

        AlertDialog dialog = builder.show();
        TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER_HORIZONTAL);



        return builder.create();
    }*/

    //Agregando el Dialogo Correcto
    public AlertDialog createDialogCorrect(String titulo, String derecha, String frente, String izquierda, String atras) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppTheme_Dark_Dialog);

        builder.setIcon(R.drawable.nfc_icon);
        builder.setTitle(titulo);
        builder.setCancelable(false);

            LayoutInflater inflater = this.getLayoutInflater();
            View v = inflater.inflate(R.layout.dialog_nfc, null);

            tderecha=(TextView) v.findViewById(R.id.derecha);
            tderecha.setText(derecha);

            tfrente=(TextView) v.findViewById(R.id.frente);
            tfrente.setText(frente);

            tizquierda=(TextView) v.findViewById(R.id.izquierda);
            tizquierda.setText(izquierda);

            tatras=(TextView) v.findViewById(R.id.atras);
            tatras.setText(atras);

        builder.setView(v);

        builder.setPositiveButton("Retornar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();

                return;
            }
        });

            builder.show();
            //return builder.create();
        return builder.create();

    }

    //Agregando el Dialogo Incorrecto
    public AlertDialog createSimpleDialog(String result, String var) {

        AlertDialog.Builder builder = new AlertDialog.Builder(LecturaNfc.this);

        builder.setIcon(R.drawable.nfc_icon)
                .setTitle("NFC incorrecto")
                .setMessage(result);
                //.setMessage(result + " id: " + var);
        builder.setCancelable(false);

        AlertDialog dialog = builder.show();
        TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER_HORIZONTAL);

        //Para cerrar la actividad completa
        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                finish();
                closeContextMenu();
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 4000);

        return builder.create();
    }

}

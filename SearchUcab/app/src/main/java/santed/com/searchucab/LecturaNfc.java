package santed.com.searchucab;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LecturaNfc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        createSimpleDialog("hola");
    }

    public AlertDialog createSimpleDialog(String result) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppTheme_Dark_Dialog);

        builder.setIcon(R.drawable.ic_menu)
                .setTitle("El valor del NFC")
                .setMessage(result);
        builder.show();

        return builder.create();
    }
}

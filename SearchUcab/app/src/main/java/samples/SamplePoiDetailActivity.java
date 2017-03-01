package samples;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import santed.com.searchucab.MenuPrincipal;
import santed.com.searchucab.R;

public class SamplePoiDetailActivity extends AppCompatActivity  {

	public static final String EXTRAS_KEY_POI_ID = "id";
	public static final String EXTRAS_KEY_POI_TITILE = "title";
	public static final String EXTRAS_KEY_POI_DESCR = "description";
	public static final String EXTRAS_KEY_POI_INFOR = "information";

	public static final String OPCION = "OPCION";
	String text = "RA3D";

	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.sample_poidetail);

		this.setTitle("Detalles");

		//LLamo al MenuPrincipal para que se cargue junto con la RA
		toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();


		//actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
		//actionBar.setDisplayHomeAsUpEnabled(true);

		actionBar.setDisplayHomeAsUpEnabled(true);


		//((TextView)findViewById(R.id.poi_id)).setText(  getIntent().getExtras().getString(EXTRAS_KEY_POI_ID) );
		((TextView)findViewById(R.id.poi_title)).setText( getIntent().getExtras().getString(EXTRAS_KEY_POI_TITILE) );
		((TextView)findViewById(R.id.poi_description)).setText(getIntent().getExtras().getString(EXTRAS_KEY_POI_DESCR));
		((TextView)findViewById(R.id.poi_information)).setText(getIntent().getExtras().getString(EXTRAS_KEY_POI_INFOR));
	}

	//Menu para implementar los valores del Toolbar

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_ar3d, menu);
		return true;
	}

	//Menu para seleccionar los valores del Toolbar
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		switch (id) {
			case android.R.id.home:
				//mDrawerLayout.openDrawer(GravityCompat.START);
				//return true;
				Log.i("ActionBar", "Atrás!");
				finish();
				return true;
			//default:
			//return super.onOptionsItemSelected(item);
			case R.id.action_set:
				//Llamada para la información
				createSimpleDialog();

				return true;

			case R.id.action:
				//Llamada para la información
				//createSimpleDialog();
				Intent p = new Intent(SamplePoiDetailActivity.this, SampleCamActivity.class);
				p.putExtra(OPCION,text);
				startActivity(p);

				return true;
			/*case R.id.action_sal:
				//Llamada para salir de la app
				finish();
				return true;*/
		}

		return super.onOptionsItemSelected(item);
	}

	public AlertDialog createSimpleDialog() {

		//Instanciamos el constructor de dialogos de alerta en este activity
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		//Agregamos el icono en la parte superior derecha, titulo central y la informaicon
		builder.setIcon(R.mipmap.ic_launcher)
				.setTitle("Search UCAB")
				.setMessage(R.string.informacion);

		//Alinea el texto
		AlertDialog dialog = builder.show();
		TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
		messageView.setGravity(Gravity.CENTER_HORIZONTAL);

		//El constructor crea el dialogo ya mostrado
		return builder.create();
	}



}

package samples;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.location.Location;
import android.location.LocationListener;
import android.media.AudioManager;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.ArchitectView.ArchitectUrlListener;
import com.wikitude.architect.ArchitectView.SensorAccuracyChangeListener;
import com.wikitude.architect.StartupConfiguration;
import com.wikitude.architect.StartupConfiguration.CameraPosition;

import santed.com.searchucab.Area;
import santed.com.searchucab.Auditorio;
import santed.com.searchucab.Banco;
import santed.com.searchucab.Dependencia;
import santed.com.searchucab.Deporte;
import santed.com.searchucab.Entidad;
import santed.com.searchucab.Escuela;
import santed.com.searchucab.Facultad;
import santed.com.searchucab.Laboratorio;
import santed.com.searchucab.Local;
import santed.com.searchucab.Monumento;
import santed.com.searchucab.R;
import santed.com.searchucab.Salud;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Abstract activity which handles live-cycle events.
 * Feel free to extend from this activity when setting up your own AR-Activity 
 *
 */
public abstract class AbstractArchitectCamActivity extends AppCompatActivity implements ArchitectViewHolderInterface{

	/**
	 * holds the Wikitude SDK AR-View, this is where camera, markers, compass, 3D models etc. are rendered
	 */
	protected ArchitectView architectView;
	
	/**
	 * sensor accuracy listener in case you want to display calibration hints
	 */
	protected SensorAccuracyChangeListener sensorAccuracyListener;
	
	/**
	 * last known location of the user, used internally for content-loading after user location was fetched
	 */
	protected Location 						lastKnownLocaton;

	/**
	 * sample location strategy, you may implement a more sophisticated approach too
	 */
	protected ILocationProvider				locationProvider;
	
	/**
	 * location listener receives location updates and must forward them to the architectView
	 */
	protected LocationListener 				locationListener;
	
	/**
	 * urlListener handling "document.location= 'architectsdk://...' " calls in JavaScript"
	 */
	protected ArchitectUrlListener urlListener;
	
	protected JSONArray poiData;

	protected boolean isLoading = false;

	private Toolbar toolbar;

	protected JSONArray convertirajson (Entidad entidad){

		JSONArray jsonArray = new JSONArray();

		try{

			//De acuerdo al tipo de entidad que nos referimos obtendremos sus datos
			JSONObject entidadJson = new JSONObject();

			if (entidad instanceof Auditorio)
			{
				Auditorio miAuditorio = (Auditorio) entidad;
				entidadJson.put("latitud", miAuditorio.getLatitud());
				entidadJson.put("longitud", miAuditorio.getLongitud());
				entidadJson.put("nombre", miAuditorio.getNombre());
				entidadJson.put("descripcion", miAuditorio.getDescripcion());

				/*Agrego las informaciones que existan en el JSONOBJECT, si es mayor que dos
				significara que hay informaciones adicionales que no sean ni areas/ piso, sino
				simplemente no hay informacion relevante*/
				if(miAuditorio.getTextosInformacion().size() > 2)
				{
					for (int aux = 1; aux < miAuditorio.getTextosInformacion().size() ; aux ++)
					{
						entidadJson.accumulate
								("informacion", miAuditorio.getTextosInformacion().get(aux));
					}
				}
				else
				{
					entidadJson.put("informacion", "No hay informacion adicional para mostrar");
				}

				jsonArray.put(entidadJson);
			}
			else if (entidad instanceof Banco)
			{
				Banco miBanco = (Banco) entidad;
				entidadJson.put("latitud", miBanco.getLatitud());
				entidadJson.put("longitud", miBanco.getLongitud());
				entidadJson.put("nombre", miBanco.getNombre());
				entidadJson.put("descripcion", miBanco.getDescripcion());

				/*Agrego las informaciones que existan en el JSONOBJECT, si es mayor que dos
				significara que hay informaciones adicionales que no sean ni areas/ piso, sino
				simplemente no hay informacion relevante*/
				if(miBanco.getTextosInformacion().size() > 2)
				{
					for (int aux = 1; aux < miBanco.getTextosInformacion().size() ; aux ++)
					{
						entidadJson.accumulate("informacion", miBanco.getTextosInformacion().get(aux));
					}
				}
				else
				{
					entidadJson.put("informacion", "No hay informacion adicional para mostrar");
				}

				jsonArray.put(entidadJson);
			}
			else if (entidad instanceof Dependencia)
			{
				Dependencia miAdministrativa = (Dependencia) entidad;
				entidadJson.put("latitud", miAdministrativa.getLatitud());
				entidadJson.put("longitud", miAdministrativa.getLongitud());
				entidadJson.put("nombre", miAdministrativa.getNombre());
				entidadJson.put("descripcion", miAdministrativa.getDescripcion());

				/*Agrego las informaciones que existan en el JSONOBJECT, si es mayor que dos
				significara que hay informaciones adicionales que no sean ni areas/ piso, sino
				simplemente no hay informacion relevante*/
				if(miAdministrativa.getTextosInformacion().size() > 2)
				{
					for (int aux = 1; aux < miAdministrativa.getTextosInformacion().size() ; aux ++)
					{
						entidadJson.accumulate
								("informacion", miAdministrativa.getTextosInformacion().get(aux));
					}
				}
				else
				{
					entidadJson.put("informacion", "No hay informacion adicional para mostrar");
				}

				jsonArray.put(entidadJson);
			}
			else if (entidad instanceof Deporte)
			{
				Deporte miDeporte = (Deporte) entidad;
				entidadJson.put("latitud", miDeporte.getLatitud());
				entidadJson.put("longitud", miDeporte.getLongitud());
				entidadJson.put("nombre", miDeporte.getNombre());
				entidadJson.put("descripcion", miDeporte.getDescripcion());

				/*Agrego las informaciones que existan en el JSONOBJECT, si es mayor que dos
				significara que hay informaciones adicionales que no sean ni areas/ piso, sino
				simplemente no hay informacion relevante*/
				if(miDeporte.getTextosInformacion().size() > 2)
				{
					for (int aux = 1; aux < miDeporte.getTextosInformacion().size() ; aux ++)
					{
						entidadJson.accumulate
								("informacion", miDeporte.getTextosInformacion().get(aux));
					}
				}
				else
				{
					entidadJson.put("informacion", "No hay informacion adicional para mostrar");
				}

				jsonArray.put(entidadJson);
			}
			else if (entidad instanceof Escuela){
				Escuela miEscuela = (Escuela) entidad;
				entidadJson.put("latitud", miEscuela.getLatitud());
				entidadJson.put("longitud", miEscuela.getLongitud());
				entidadJson.put("nombre", miEscuela.getNombre());
				entidadJson.put("descripcion", miEscuela.getDescripcion());

				/*Agrego las informaciones que existan en el JSONOBJECT, si es mayor que dos
				significara que hay informaciones adicionales que no sean ni areas/ piso, sino
				simplemente no hay informacion relevante*/
				if(miEscuela.getTextosInformacion().size() > 2)
				{
					for (int aux = 1; aux < miEscuela.getTextosInformacion().size() ; aux ++)
					{
						entidadJson.accumulate
								("informacion", miEscuela.getTextosInformacion().get(aux));
					}
				}
				else
				{
					entidadJson.put("informacion", "No hay informacion adicional para mostrar");
				}

				jsonArray.put(entidadJson);
			}
			else if (entidad instanceof Facultad)
			{
				Facultad miFacultad = (Facultad) entidad;
				entidadJson.put("latitud", miFacultad.getLatitud());
				entidadJson.put("longitud", miFacultad.getLongitud());
				entidadJson.put("nombre", miFacultad.getNombre());
				entidadJson.put("descripcion", miFacultad.getDescripcion());

				/*Agrego las informaciones que existan en el JSONOBJECT, si es mayor que dos
				significara que hay informaciones adicionales que no sean ni areas/ piso, sino
				simplemente no hay informacion relevante*/
				if(miFacultad.getTextosInformacion().size() > 2)
				{
					for (int aux = 1; aux < miFacultad.getTextosInformacion().size() ; aux ++)
					{
						entidadJson.accumulate
								("informacion", miFacultad.getTextosInformacion().get(aux));
					}
				}
				else
				{
					entidadJson.put("informacion", "No hay informacion adicional para mostrar");
				}

				jsonArray.put(entidadJson);
			}
			else if (entidad instanceof Laboratorio)
			{
				Laboratorio miLaboratorio = (Laboratorio) entidad;
				entidadJson.put("latitud", miLaboratorio.getLatitud());
				entidadJson.put("longitud", miLaboratorio.getLongitud());
				entidadJson.put("nombre", miLaboratorio.getNombre());
				entidadJson.put("descripcion", miLaboratorio.getDescripcion());

				/*Agrego las informaciones que existan en el JSONOBJECT, si es mayor que dos
				significara que hay informaciones adicionales que no sean ni areas/ piso, sino
				simplemente no hay informacion relevante*/
				if(miLaboratorio.getTextosInformacion().size() > 2)
				{
					for (int aux = 1; aux < miLaboratorio.getTextosInformacion().size() ; aux ++)
					{
						entidadJson.accumulate
								("informacion", miLaboratorio.getTextosInformacion().get(aux));
					}
				}
				else
				{
					entidadJson.put("informacion", "No hay informacion adicional para mostrar");
				}

				jsonArray.put(entidadJson);
			}
			else if (entidad instanceof Local)
			{
				Local miLocal = (Local) entidad;
				entidadJson.put("latitud", miLocal.getLatitud());
				entidadJson.put("longitud", miLocal.getLongitud());
				entidadJson.put("nombre", miLocal.getNombre());
				entidadJson.put("descripcion", miLocal.getEspecialidad());

				/*Agrego las informaciones que existan en el JSONOBJECT, si es mayor que dos
				significara que hay informaciones adicionales que no sean ni areas/ piso, sino
				simplemente no hay informacion relevante*/
				if(miLocal.getTextosInformacion().size() > 2)
				{
					for (int aux = 1; aux < miLocal.getTextosInformacion().size() ; aux ++)
					{
						entidadJson.accumulate("informacion", miLocal.getTextosInformacion().get(aux));
					}
				}
				else
				{
					entidadJson.put("informacion", "No hay informacion adicional para mostrar");
				}

				jsonArray.put(entidadJson);
			}
			else if (entidad instanceof Monumento)
			{
				Monumento miMonumento = (Monumento) entidad;
				entidadJson.put("latitud", miMonumento.getLatitud());
				entidadJson.put("longitud", miMonumento.getLongitud());
				entidadJson.put("nombre", miMonumento.getNombre());
				entidadJson.put("descripcion", miMonumento.getDescripcion());

				/*Agrego las informaciones que existan en el JSONOBJECT, si es mayor que dos
				significara que hay informaciones adicionales que no sean ni areas/ piso, sino
				simplemente no hay informacion relevante*/
				if(miMonumento.getTextosInformacion().size() > 2)
				{
					for (int aux = 1; aux < miMonumento.getTextosInformacion().size() ; aux ++)
					{
						entidadJson.accumulate("informacion", miMonumento.getTextosInformacion().get(aux));
					}
				}
				else
				{
					entidadJson.put("informacion", "No hay informacion adicional para mostrar");
				}

				jsonArray.put(entidadJson);
			}
			else if (entidad instanceof Salud)
			{
				Salud miSalud = (Salud) entidad;
				entidadJson.put("latitud", miSalud.getLatitud());
				entidadJson.put("longitud", miSalud.getLongitud());
				entidadJson.put("nombre", miSalud.getNombre());
				entidadJson.put("descripcion", miSalud.getDescripcion());

				/*Agrego las informaciones que existan en el JSONOBJECT, si es mayor que dos
				significara que hay informaciones adicionales que no sean ni areas/ piso, sino
				simplemente no hay informacion relevante*/
				if(miSalud.getTextosInformacion().size() > 2)
				{
					for (int aux = 1; aux < miSalud.getTextosInformacion().size() ; aux ++)
					{
						entidadJson.accumulate("informacion", miSalud.getTextosInformacion().get(aux));
					}
				}
				else
				{
					entidadJson.put("informacion", "No hay informacion adicional para mostrar");
				}

				jsonArray.put(entidadJson);
			}

			else if (entidad instanceof Area)
			{
				Area miArea = (Area) entidad;
				entidadJson.put("latitud", miArea.getLatitud());
				entidadJson.put("longitud", miArea.getLongitud());
				entidadJson.put("nombre", miArea.getNombre());
				entidadJson.put("descripcion", miArea.getDescripcion());

				/*Agrego las informaciones que existan en el JSONOBJECT, si es mayor que dos
				significara que hay informaciones adicionales que no sean ni areas/ piso, sino
				simplemente no hay informacion relevante*/
				if(miArea.getTextosInformacion().size() > 2)
				{
					for (int aux = 1; aux < miArea.getTextosInformacion().size() ; aux ++)
					{
						entidadJson.accumulate("informacion", miArea.getTextosInformacion().get(aux));
					}
				}
				else
				{
					entidadJson.put("informacion", "No hay informacion adicional para mostrar");
				}

				jsonArray.put(entidadJson);
			}

			return jsonArray;


		}catch (JSONException jse){
			jse.printStackTrace();
		}
		return jsonArray;
	}

	/** Called when the activity is first created. */
	@SuppressLint("NewApi")
	@Override
	public void onCreate( final Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		/* pressing volume up/down should cause music volume changes */
		this.setVolumeControlStream( AudioManager.STREAM_MUSIC );

		/* set samples content view */
		this.setContentView( this.getContentViewId() );
		
		this.setTitle( this.getActivityTitle() );

		//LLamo al MenuPrincipal para que se cargue junto con la RA
		toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		//actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
		//actionBar.setDisplayHomeAsUpEnabled(true);

		actionBar.setDisplayHomeAsUpEnabled(true);



		/*  
		 *	this enables remote debugging of a WebView on Android 4.4+ when debugging = true in AndroidManifest.xml
		 *	If you get a compile time error here, ensure to have SDK 19+ used in your ADT/Eclipse.
		 *	You may even delete this block in case you don't need remote debugging or don't have an Android 4.4+ device in place.
		 *	Details: https://developers.google.com/chrome-developer-tools/docs/remote-debugging
		 */
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
		    if ( 0 != ( getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE ) ) {
		        WebView.setWebContentsDebuggingEnabled(true);
		    }
		}

		/* set AR-view for life-cycle notifications etc. */
		this.architectView = (ArchitectView)this.findViewById( this.getArchitectViewId()  );

		// aqui coloque la clave para usar la ra que se descarga de la pag de wikitude
		//String clave = "ffKpJMBDLeYaFOiMQQzR5FP/ofaEZTYuVf7sGP9kZR0atcRLt1uGKdAfPYljQX32cz6EHd8AidtmQvRVDgwD8D0w1wpQthhfy5wR9iaFftsc59cUr7Fb+SQfpQHZBOmPOTneycJlEB5LsSHwcg6jl6sx8u4rq3MWyAr3FrlPHBZTYWx0ZWRfX6hzCWpeLo3Ts4ZR8wJbm79wALi1YBW5Q6rzdkjXTXHOIG47FbYcfyphHBlXT120afoXLNDdfUSUTwiuPfnLqjHynh84hQpHOpf8Y4oWblY7wwIVurwjYx+RrfEjytgpA/AQWCPuWV8/EQO/bfBDqQVicEwrOs/YG5OPUeDXYDdg++hIy6QSIzp6eLaDlStl3pi5zShJdX8+S7WTOlL4epW9MvU0V238r9KB3GxT5yldWvScPHxPitnYWWMSikO5Lc9tj8pAfAPkqU1xslD7pJCqFKDuw0aWfReHAuQbPXitypubn9g3sNjSXp6/fE4J8zEfQE8MLHC4K520eqmKJdzgA+e508Mre7Fr7SppctUWVHmacQn2Vs6FmjK3arpeniK21N5/NZVgj0+vIY0imBLchD6uYxLvBsGRtJIKkz4plE19Iu9ZLLkJpKkaoA1Bp3dr11rKAyhyb6HVCb11IPRRKx6TxKythdESGRnUc9STTJc5a27k8R0=";
		//this.getWikitudeSDKLicenseKey()
		/* pass SDK key if you have one, this one is only valid for this package identifier and must not be used somewhere else */
		final StartupConfiguration config = new StartupConfiguration( this.getWikitudeSDKLicenseKey(), this.getFeatures(), this.getCameraPosition() );

		try {
			/* first mandatory life-cycle notification */
			this.architectView.onCreate( config );
		} catch (RuntimeException rex) {
			this.architectView = null;
			Toast.makeText(getApplicationContext(), "No se pudo cargar la AR, por favor intente nuevamente.", Toast.LENGTH_SHORT).show();
			Log.e(this.getClass().getName(), "Exception in ArchitectView.onCreate()", rex);
		}

		// set accuracy listener if implemented, you may e.g. show calibration prompt for compass using this listener
		this.sensorAccuracyListener = this.getSensorAccuracyListener();
		
		// set urlListener, any calls made in JS like "document.location = 'architectsdk://foo?bar=123'" is forwarded to this listener, use this to interact between JS and native Android activity/fragment
		this.urlListener = this.getUrlListener();  
		
		// register valid urlListener in architectView, ensure this is set before content is loaded to not miss any event
		if (this.urlListener != null && this.architectView != null) {
			this.architectView.registerUrlListener( this.getUrlListener() );
		}
		
		if (hasGeo()) {
			// listener passed over to locationProvider, any location update is handled here
			this.locationListener = new LocationListener() {
	
				@Override
				public void onStatusChanged( String provider, int status, Bundle extras ) {
				}
	
				@Override
				public void onProviderEnabled( String provider ) {
				}
	
				@Override
				public void onProviderDisabled( String provider ) {
				}
	
				@Override
				public void onLocationChanged( final Location location ) {
					// forward location updates fired by LocationProvider to architectView, you can set lat/lon from any location-strategy
					if (location!=null) {
					// sore last location as member, in case it is needed somewhere (in e.g. your adjusted project)
						AbstractArchitectCamActivity.this.lastKnownLocaton = location;
						if ( AbstractArchitectCamActivity.this.architectView != null ) {
							// check if location has altitude at certain accuracy level & call right architect method (the one with altitude information)
							if ( location.hasAltitude() && location.hasAccuracy() && location.getAccuracy()<7) {
								AbstractArchitectCamActivity.this.architectView.setLocation( location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getAccuracy() );
							} else {
								AbstractArchitectCamActivity.this.architectView.setLocation( location.getLatitude(), location.getLongitude(), location.hasAccuracy() ? location.getAccuracy() : 1000 );
							}
						}
					}
				}
			};

			// locationProvider used to fetch user position
			this.locationProvider = getLocationProvider( this.locationListener );
		} else {
			this.locationProvider = null;
			this.locationListener = null;
		}
	}

	protected abstract CameraPosition getCameraPosition();

	private int getFeatures() {
		int features = (hasGeo() ? StartupConfiguration.Features.Geo : 0) | (hasIR() ? StartupConfiguration.Features.Tracking2D : 0);
		return features;
	}

	protected abstract boolean hasGeo();
	protected abstract boolean hasIR();

	@Override
	protected void onPostCreate( final Bundle savedInstanceState ) {
		super.onPostCreate(savedInstanceState);
		Entidad entidad = (Entidad) getIntent().getSerializableExtra("clase");

		if ( this.architectView != null ) {
			
			// call mandatory live-cycle method of architectView
			this.architectView.onPostCreate();
			
			try {

				// <Script src="architect://architect.js"> se debe incluir
				// load content via url in architectView, ensure '<script src="architect://architect.js"></script>' is part of this HTML file, have a look at wikitude.com's developer section for API references
				this.architectView.load( this.getARchitectWorldPath() );

				if (this.getInitialCullingDistanceMeters() != ArchitectViewHolderInterface.CULLING_DISTANCE_DEFAULT_METERS) {
					// set the culling distance - meaning: the maximum distance to render geo-content
					this.architectView.setCullingDistance( this.getInitialCullingDistanceMeters() );
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		this.injectData(entidad);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		// call mandatory live-cycle method of architectView
		if ( this.architectView != null ) {
			this.architectView.onResume();
			
			// register accuracy listener in architectView, if set
			if (this.sensorAccuracyListener!=null) {
				this.architectView.registerSensorAccuracyChangeListener( this.sensorAccuracyListener );
			}
		}

		// tell locationProvider to resume, usually location is then (again) fetched, so the GPS indicator appears in status bar
		if ( this.locationProvider != null ) {
			this.locationProvider.onResume();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		// call mandatory live-cycle method of architectView
		if ( this.architectView != null ) {
			this.architectView.onPause();
			
			// unregister accuracy listener in architectView, if set
			if ( this.sensorAccuracyListener != null ) {
				this.architectView.unregisterSensorAccuracyChangeListener(this.sensorAccuracyListener);
			}
		}
		
		// tell locationProvider to pause, usually location is then no longer fetched, so the GPS indicator disappears in status bar
		if ( this.locationProvider != null ) {
			this.locationProvider.onPause();
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		// call mandatory live-cycle method of architectView
		if ( this.architectView != null ) {
			this.architectView.onDestroy();
		}
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		if ( this.architectView != null ) {
			this.architectView.onLowMemory();
		}
	}

	/**
	 * title shown in activity
	 * @return
	 */
	public abstract String getActivityTitle();
	
	/**
	 * path to the architect-file (AR-Experience HTML) to launch
	 * @return
	 */
	@Override
	public abstract String getARchitectWorldPath();
	
	/**
	 * url listener fired once e.g. 'document.location = "architectsdk://foo?bar=123"' is called in JS
	 * @return
	 */
	@Override
	public abstract ArchitectUrlListener getUrlListener();
	
	/**
	 * @return layout id of your layout.xml that holds an ARchitect View, e.g. R.layout.camview
	 */
	@Override
	public abstract int getContentViewId();
	
	/**
	 * @return Wikitude SDK license key, checkout www.wikitude.com for details
	 */
	@Override
	public abstract String getWikitudeSDKLicenseKey();
	
	/**
	 * @return layout-id of architectView, e.g. R.id.architectView
	 */
	@Override
	public abstract int getArchitectViewId();

	/**
	 * 
	 * @return Implementation of a Location
	 */
	@Override
	public abstract ILocationProvider getLocationProvider(final LocationListener locationListener);
	
	/**
	 * @return Implementation of Sensor-Accuracy-Listener. That way you can e.g. show prompt to calibrate compass
	 */
	@Override
	public abstract ArchitectView.SensorAccuracyChangeListener getSensorAccuracyListener();
	
	/**
	 * helper to check if video-drawables are supported by this device. recommended to check before launching ARchitect Worlds with videodrawables
	 * @return true if AR.VideoDrawables are supported, false if fallback rendering would apply (= show video fullscreen)
	 */
	public static final boolean isVideoDrawablesSupported() {
		String extensions = GLES20.glGetString( GLES20.GL_EXTENSIONS );
		return extensions != null && extensions.contains( "GL_OES_EGL_image_external" );
	}

	protected void injectData(final Entidad entidad) {



		if (!isLoading) {
			final Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					isLoading = true;
					
					final int WAIT_FOR_LOCATION_STEP_MS = 2000;
					
					while (lastKnownLocaton==null && !isFinishing()) {
					
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								Toast.makeText(AbstractArchitectCamActivity.this, R.string.location_fetching, Toast.LENGTH_SHORT).show();	
							}
						});
			
						try {
							Thread.sleep(WAIT_FOR_LOCATION_STEP_MS);
						} catch (InterruptedException e) {
							break;
						}
					}
					
					if (lastKnownLocaton!=null && !isFinishing()) {
						// TODO: you may replace this dummy implementation and instead load POI information e.g. from your database
						//poiData = getPoiInformation(lastKnownLocaton, 20);
						poiData = convertirajson(entidad);
						callJavaScript("World.loadPoisFromJsonData", new String[] { poiData.toString() });
					}
					
					isLoading = false;
				}
			});
			t.start();
		}
	}

	/**
	 * call JacaScript in architectView
	 * @param methodName
	 * @param arguments
	 */
	private void callJavaScript(final String methodName, final String[] arguments) {
		final StringBuilder argumentsString = new StringBuilder("");
		for (int i= 0; i<arguments.length; i++) {
			argumentsString.append(arguments[i]);
			if (i<arguments.length-1) {
				argumentsString.append(", ");
			}
		}
		
		if (this.architectView!=null) {
			final String js = ( methodName + "( " + argumentsString.toString() + " );" );
			this.architectView.callJavascript(js);
		}
	}
	
	/**
	 * loads poiInformation and returns them as JSONArray. Ensure attributeNames of JSON POIs are well known in JavaScript, so you can parse them easily
	 * @param userLocation the location of the user
	 * @param numberOfPlaces number of places to load (at max)
	 * @return POI information in JSONArray
	 */
	public static JSONArray getPoiInformation(final Location userLocation, final int numberOfPlaces) {
		
		if (userLocation==null) {
			return null;
		}
		
		final JSONArray pois = new JSONArray();
		
		// ensure these attributes are also used in JavaScript when extracting POI data
		final String ATTR_ID = "id";
		final String ATTR_NAME = "name";
		final String ATTR_DESCRIPTION = "description";
		final String ATTR_LATITUDE = "latitude";
		final String ATTR_LONGITUDE = "longitude";
		final String ATTR_ALTITUDE = "altitude";
		
		for (int i=1;i <= numberOfPlaces; i++) {
			final HashMap<String, String> poiInformation = new HashMap<String, String>();
			poiInformation.put(ATTR_ID, String.valueOf(i));
			poiInformation.put(ATTR_NAME, "POI#" + i);
			poiInformation.put(ATTR_DESCRIPTION, "This is the description of POI#" + i);
			double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
			poiInformation.put(ATTR_LATITUDE, String.valueOf(poiLocationLatLon[0]));
			poiInformation.put(ATTR_LONGITUDE, String.valueOf(poiLocationLatLon[1]));
			final float UNKNOWN_ALTITUDE = -32768f;  // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
			// Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
			poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
			pois.put(new JSONObject(poiInformation));
		}
		
		return pois;
	}
	
	/**
	 * helper for creation of dummy places.
	 * @param lat center latitude
	 * @param lon center longitude
	 * @return lat/lon values in given position's vicinity
	 */
	private static double[] getRandomLatLonNearby(final double lat, final double lon) {
		return new double[] { lat + Math.random()/5-0.1 , lon + Math.random()/5-0.1};
	}

	//Menu para implementar los valores del Toolbar

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_info, menu);
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
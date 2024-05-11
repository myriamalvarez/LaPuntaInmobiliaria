package com.myrsoft.lapuntainmobiliaria;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.myrsoft.lapuntainmobiliaria.modelo.Propietario;
import com.myrsoft.lapuntainmobiliaria.request.ApiClient;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> errorMutable;
    private LeeSensor leeSensor;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        leeSensor = new LeeSensor(context);

    }
    public LiveData<String> getErrorMutable(){
        if (errorMutable == null){
            errorMutable = new MutableLiveData<>();
        }
        return errorMutable;
    }
    public void login(String usuario, String contraseña){
        ApiClient api = ApiClient.getApi();
        Propietario propietarioLogueado = api.login(usuario, contraseña);
        if (propietarioLogueado != null){
            Intent intent = new Intent(context, MenuActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else{
            errorMutable.setValue("usuario y/o contraseña incorrecto");
        }
    }
    private class LeeSensor implements SensorEventListener{
        private SensorManager sensorManager;
        private Sensor acelerometro;
        private long ultimoRegistro = 0;
        private float ultimo_x, ultimo_y, ultimo_z;
        private static final int SHAKE_THRESHOLD = 600;
        public LeeSensor(Context context){
            this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            this.acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            registerListener();
        }
        public void registerListener(){
            sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        }
        public void unregisterListener(){
            sensorManager.unregisterListener(this);
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            Sensor miSensor = event.sensor;

            if(miSensor.getType() == Sensor.TYPE_ACCELEROMETER){
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                long tiempoActual = System.currentTimeMillis();

                if ((tiempoActual - ultimoRegistro) > 100){
                    long difTiempo = (tiempoActual - ultimoRegistro);
                    ultimoRegistro = tiempoActual;

                    float velocidad = Math.abs(x + y + z - ultimo_x - ultimo_y - ultimo_z)/difTiempo * 10000;

                    if (velocidad > SHAKE_THRESHOLD){
                        llamarInmobiliaria();
                    }
                    ultimo_x = x;
                    ultimo_y = y;
                    ultimo_z = z;

                }

            }
        }
        private void llamarInmobiliaria(){
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel: 999"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try{
                context.startActivity(intent);
            }catch (Exception e){
                errorMutable.setValue("No tiene permisos");
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}

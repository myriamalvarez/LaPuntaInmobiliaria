package com.myrsoft.lapuntainmobiliaria.ui.inquilinos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myrsoft.lapuntainmobiliaria.modelo.Inmueble;
import com.myrsoft.lapuntainmobiliaria.request.ApiClient;

import java.util.List;

public class InquilinosViewModel extends AndroidViewModel {
    private MutableLiveData<List<Inmueble>> inmuebles;
    private Context context;
    private ApiClient apiClient;
    public InquilinosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        apiClient = ApiClient.getApi();
    }
    public LiveData<List<Inmueble>> getInmuebles(){
        if (inmuebles == null){
            inmuebles = new MutableLiveData<>();
        }
        return inmuebles;
    }
    public void cargarInmuebles(){
        List<Inmueble> propiedades = apiClient.obtenerPropiedadesAlquiladas();
        inmuebles.setValue(propiedades);
    }

}
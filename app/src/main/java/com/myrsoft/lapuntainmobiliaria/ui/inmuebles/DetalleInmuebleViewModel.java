package com.myrsoft.lapuntainmobiliaria.ui.inmuebles;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myrsoft.lapuntainmobiliaria.modelo.Inmueble;
import com.myrsoft.lapuntainmobiliaria.request.ApiClient;

public class DetalleInmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmuebleMutable;
    private ApiClient apiClient;

    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
        apiClient = ApiClient.getApi();
    }
    public LiveData<Inmueble> getInmuebleMutable(){
        if (inmuebleMutable == null){
            inmuebleMutable = new MutableLiveData<>();
        }
        return inmuebleMutable;
    }
    public void cargarInmueble(Bundle bundle){
        Inmueble propiedad = (Inmueble) bundle.getSerializable("inmueble");
        inmuebleMutable.setValue(propiedad);
    }
    public void editarDisponibilidad(Inmueble inmueble){
        apiClient.actualizarInmueble(inmueble);
    }
}
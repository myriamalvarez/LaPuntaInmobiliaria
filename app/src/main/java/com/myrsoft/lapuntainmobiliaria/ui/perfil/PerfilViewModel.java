package com.myrsoft.lapuntainmobiliaria.ui.perfil;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myrsoft.lapuntainmobiliaria.modelo.Propietario;
import com.myrsoft.lapuntainmobiliaria.request.ApiClient;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> usuarioMutable;
    private MutableLiveData<Boolean> estadoMutable;
    private MutableLiveData<String> textoBotonMutable;
    private Context context;
    private ApiClient apiClient;


    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        apiClient = ApiClient.getApi();
    }
    public LiveData<Propietario> getUsuarioMutable(){
        if(usuarioMutable == null){
            usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }
    public LiveData<Boolean> getEstadoMutable(){
        if(estadoMutable == null){
            estadoMutable = new MutableLiveData<>();
        }
        return estadoMutable;
    }
    public LiveData<String> getTextoBotonMutable(){
        if(textoBotonMutable == null){
            textoBotonMutable = new MutableLiveData<>();
        }
        return textoBotonMutable;
    }
    public void cambioBoton(String textoB, Propietario propietario){
        if (textoB.equals("Editar")){
            textoBotonMutable.setValue("Guardar");
            estadoMutable.setValue(true);
        }else{
            textoBotonMutable.setValue("Editar");
            estadoMutable.setValue(false);
            apiClient.actualizarPerfil(propietario);
        }
    }
    public void cargarUsuario(){
        usuarioMutable.setValue(apiClient.obtenerUsuarioActual());
    }
}
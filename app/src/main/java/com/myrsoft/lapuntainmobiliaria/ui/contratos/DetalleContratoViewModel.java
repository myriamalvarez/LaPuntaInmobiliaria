package com.myrsoft.lapuntainmobiliaria.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myrsoft.lapuntainmobiliaria.modelo.Contrato;
import com.myrsoft.lapuntainmobiliaria.modelo.Inmueble;
import com.myrsoft.lapuntainmobiliaria.request.ApiClient;

import java.util.PrimitiveIterator;

public class DetalleContratoViewModel extends AndroidViewModel {
private MutableLiveData<Contrato> contratoMutable;
private Contrato contratoActual;
private Context context;
    public DetalleContratoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<Contrato> getContratoMutable(){
        if (contratoMutable == null){
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;
    }
    public void cargarContrato(Bundle bundle){
        Inmueble inmueble = (Inmueble) bundle.getSerializable("contrato");
        contratoActual = ApiClient.getApi().obtenerContratoVigente(inmueble);
        contratoMutable.setValue(contratoActual);
    }
}
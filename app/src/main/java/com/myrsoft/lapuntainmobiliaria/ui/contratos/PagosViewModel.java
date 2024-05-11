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
import com.myrsoft.lapuntainmobiliaria.modelo.Pago;
import com.myrsoft.lapuntainmobiliaria.request.ApiClient;

import java.util.List;

public class PagosViewModel extends AndroidViewModel {
    private MutableLiveData<List<Pago>> pagosMutable;
    private Context context;
    public PagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<List<Pago>> getPagos(){
        if (pagosMutable == null){
            pagosMutable = new MutableLiveData<>();
        }
        return pagosMutable;
    }
    public void cargarPagos(Bundle bundle){
        ApiClient apiClient = ApiClient.getApi();
        Contrato contrato = (Contrato) bundle.getSerializable("contrato");
        pagosMutable.setValue(apiClient.obtenerPagos(contrato));
    }
}
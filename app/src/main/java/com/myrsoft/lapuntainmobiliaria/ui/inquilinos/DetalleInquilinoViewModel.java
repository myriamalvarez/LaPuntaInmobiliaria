package com.myrsoft.lapuntainmobiliaria.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myrsoft.lapuntainmobiliaria.modelo.Inmueble;
import com.myrsoft.lapuntainmobiliaria.modelo.Inquilino;
import com.myrsoft.lapuntainmobiliaria.request.ApiClient;

public class DetalleInquilinoViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> inquilino;
    private ApiClient apiClient;
    private Context context;
    public DetalleInquilinoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        apiClient = ApiClient.getApi();
    }
    public LiveData<Inquilino> getInquilino(){
        if (inquilino == null){
            inquilino = new MutableLiveData<>();
        }
        return inquilino;
    }
    public void cargarInquilino(Bundle bundle){
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        Inquilino inqui = apiClient.obtenerInquilino(inmueble);
        inquilino.setValue(inqui);
    }
}
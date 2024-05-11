package com.myrsoft.lapuntainmobiliaria.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myrsoft.lapuntainmobiliaria.R;
import com.myrsoft.lapuntainmobiliaria.databinding.FragmentContratosBinding;
import com.myrsoft.lapuntainmobiliaria.databinding.FragmentInmueblesBinding;
import com.myrsoft.lapuntainmobiliaria.modelo.Inmueble;
import com.myrsoft.lapuntainmobiliaria.ui.inmuebles.InmuebleAdapter;
import com.myrsoft.lapuntainmobiliaria.ui.inmuebles.InmueblesViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContratosFragment extends Fragment {

    private ContratosViewModel vm;
    private FragmentContratosBinding binding;
    private InmuebleConContratoAdapter adapter;
    private Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentContratosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = root.getContext();
        inicializar(root);
        return root;
    }

    private void inicializar(View root) {

            vm = new ViewModelProvider(this).get(ContratosViewModel.class);
            vm.getInmueblesMutable().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
                @Override
                public void onChanged(List<Inmueble> inmuebles) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false);
                    binding.rvInmuebles.setLayoutManager(gridLayoutManager);
                    adapter = new InmuebleConContratoAdapter(inmuebles, context, getLayoutInflater());
                    binding.rvInmuebles.setAdapter(adapter);
                }
            });
            vm.cargarInmueblesConContrato();
        }
        public void onDestroyView(){
        super.onDestroyView();
        binding = null;
        }




}
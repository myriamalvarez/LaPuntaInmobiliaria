package com.myrsoft.lapuntainmobiliaria.ui.inmuebles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.myrsoft.lapuntainmobiliaria.databinding.FragmentInmueblesBinding;
import com.myrsoft.lapuntainmobiliaria.modelo.Inmueble;

import java.util.ArrayList;

public class InmueblesFragment extends Fragment {

    private FragmentInmueblesBinding binding;
    private InmueblesViewModel vm;
    private InmuebleAdapter adapter;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = root.getContext();
        inicializar(root);
        return root;
    }

    private void inicializar(View root) {
        vm = new ViewModelProvider(this).get(InmueblesViewModel.class);
        vm.getInmueblesMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false);
                binding.rvInmuebles.setLayoutManager(gridLayoutManager);
                adapter = new InmuebleAdapter(inmuebles, context, getLayoutInflater());
                binding.rvInmuebles.setAdapter(adapter);
            }
        });
        vm.cargarInmuebles();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
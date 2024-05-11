package com.myrsoft.lapuntainmobiliaria.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myrsoft.lapuntainmobiliaria.R;
import com.myrsoft.lapuntainmobiliaria.databinding.FragmentPagosBinding;
import com.myrsoft.lapuntainmobiliaria.modelo.Pago;

import java.util.List;

public class PagosFragment extends Fragment {

    private PagosViewModel vm;
    private FragmentPagosBinding binding;
    private Context context;
    private PagosAdapter adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPagosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = root.getContext();
        inicializar(root);
        return root;
    }

    private void inicializar(View root) {
        vm = new ViewModelProvider(this).get(PagosViewModel.class);
        vm.getPagos().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                GridLayoutManager grid = new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false);
                binding.rvInmuebles.setLayoutManager(grid);
                adapter = new PagosAdapter(pagos, context, getLayoutInflater());
                binding.rvInmuebles.setAdapter(adapter);
            }
        });
        vm.cargarPagos(getArguments());
    }
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
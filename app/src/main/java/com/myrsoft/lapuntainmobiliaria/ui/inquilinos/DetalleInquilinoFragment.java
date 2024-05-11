package com.myrsoft.lapuntainmobiliaria.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myrsoft.lapuntainmobiliaria.R;
import com.myrsoft.lapuntainmobiliaria.databinding.FragmentDetalleInquilinoBinding;
import com.myrsoft.lapuntainmobiliaria.modelo.Inquilino;

public class DetalleInquilinoFragment extends Fragment {

    private DetalleInquilinoViewModel vm;
    private FragmentDetalleInquilinoBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        inicializar(root);
        return root;
    }

    private void inicializar(View root) {
        vm = new ViewModelProvider(this).get(DetalleInquilinoViewModel.class);
        vm.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                binding.tvCodigo.setText(inquilino.getIdInquilino() + "");
                binding.tvApellido.setText(inquilino.getApellido());
                binding.tvNombre.setText(inquilino.getNombre());
                binding.tvDNI.setText(inquilino.getDNI() + "");
                binding.tvEmail.setText(inquilino.getEmail());
                binding.tvTelefono.setText(inquilino.getTelefono());
                binding.tvGarante.setText(inquilino.getNombreGarante());
                binding.tvTelefonoGarante.setText(inquilino.getTelefonoGarante());
            }
            });
        vm.cargarInquilino(getArguments());
            }

    }

package com.myrsoft.lapuntainmobiliaria.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.myrsoft.lapuntainmobiliaria.R;
import com.myrsoft.lapuntainmobiliaria.databinding.FragmentDetalleInmuebleBinding;
import com.myrsoft.lapuntainmobiliaria.modelo.Inmueble;

public class DetalleInmuebleFragment extends Fragment {

    private DetalleInmuebleViewModel vm;
    private FragmentDetalleInmuebleBinding binding;
    private Inmueble propiedad;

    public static DetalleInmuebleFragment newInstance() {

        return new DetalleInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        inicializar(root);
        return root;
    }

    private void inicializar(View root) {
        vm = new ViewModelProvider(this).get(DetalleInmuebleViewModel.class);
        vm.getInmuebleMutable().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                propiedad = inmueble;
                binding.tvId.setText(inmueble.getIdInmueble()+"");
                binding.tvDireccion.setText(inmueble.getDireccion());
                binding.tvTipo.setText(inmueble.getTipo());
                binding.tvUso.setText(inmueble.getUso());
                binding.tvAmbientes.setText(inmueble.getAmbientes() + "");
                binding.tvPrecio.setText("$"+inmueble.getPrecio()+"");
                binding.cbEstado.setChecked(inmueble.isEstado());
                Glide.with(getContext())
                        .load(inmueble.getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.ivImagenInmueble);
            }
        });
        vm.cargarInmueble(getArguments());
        binding.cbEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean disponible = binding.cbEstado.isChecked();
                propiedad.setEstado(disponible);
                vm.editarDisponibilidad(propiedad);
            }
        });
    }


}
package com.myrsoft.lapuntainmobiliaria.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.myrsoft.lapuntainmobiliaria.databinding.FragmentPerfilBinding;
import com.myrsoft.lapuntainmobiliaria.databinding.FragmentPerfilBinding;
import com.myrsoft.lapuntainmobiliaria.modelo.Propietario;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel vm;
    private Propietario usuario;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm.getUsuarioMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                usuario = propietario;
                binding.etId.setText(propietario.getId() + "");
                binding.etDNI.setText(propietario.getDni() + "");
                binding.etNombre.setText(propietario.getNombre());
                binding.etApellido.setText(propietario.getApellido());
                binding.etEmail.setText(propietario.getEmail());
                binding.etContraseA.setText(propietario.getContraseña());
                binding.etTelefono.setText(propietario.getTelefono());
            }
        });
        vm.getEstadoMutable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.etDNI.setEnabled(aBoolean);
                binding.etNombre.setEnabled(aBoolean);
                binding.etApellido.setEnabled(aBoolean);
                binding.etTelefono.setEnabled(aBoolean);
            }
        });
        vm.getTextoBotonMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.btEditar.setText(s);
            }
        });
        vm.cargarUsuario();
        inicializar(root);
        return root;
    }

    private void inicializar(View root) {
        binding.btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setDni(Long.parseLong(binding.etDNI.getText().toString()));
                usuario.setNombre(binding.etNombre.getText().toString());
                usuario.setApellido(binding.etApellido.getText().toString());
                usuario.setTelefono(binding.etTelefono.getText().toString());
                String texto = binding.btEditar.getText().toString();
                vm.cambioBoton(texto, usuario);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
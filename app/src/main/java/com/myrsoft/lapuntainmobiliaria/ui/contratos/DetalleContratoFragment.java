package com.myrsoft.lapuntainmobiliaria.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myrsoft.lapuntainmobiliaria.R;
import com.myrsoft.lapuntainmobiliaria.databinding.FragmentContratosBinding;
import com.myrsoft.lapuntainmobiliaria.databinding.FragmentDetalleContratoBinding;
import com.myrsoft.lapuntainmobiliaria.databinding.FragmentDetalleInmuebleBinding;
import com.myrsoft.lapuntainmobiliaria.modelo.Contrato;

public class DetalleContratoFragment extends Fragment {

    private DetalleContratoViewModel vm;
    private FragmentDetalleContratoBinding binding;
    private Contrato contratoActual;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        inicializar(root);
        return root;
    }

    private void inicializar(View root) {
        vm = new ViewModelProvider(this).get(DetalleContratoViewModel.class);
        vm.getContratoMutable().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                contratoActual = contrato;
                binding.tvCodigoContrato.setText(contrato.getIdContrato()+"");
                binding.tvFechaInicio.setText(contrato.getFechaInicio()+"");
                binding.tvFechaFin.setText(contrato.getFechaFin()+"");
                binding.tvInmueble.setText(contrato.getInmueble().getDireccion());
                binding.tvInquilino.setText(contrato.getInquilino().getNombre()+ " " + contrato.getInquilino().getNombre());
                binding.tvMontoAqluiler.setText("$"+ contrato.getMontoAlquiler()+"");
            }
        });
        vm.cargarContrato(getArguments());
        binding.btPagosC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("contrato", contratoActual);
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_menu)
                        .navigate(R.id.pagosFragment, bundle);
            }
        });
    }


}
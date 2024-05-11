package com.myrsoft.lapuntainmobiliaria.ui.contratos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.myrsoft.lapuntainmobiliaria.R;
import com.myrsoft.lapuntainmobiliaria.modelo.Inmueble;

import java.io.Serializable;
import java.util.List;

public class InmuebleConContratoAdapter extends RecyclerView.Adapter<InmuebleConContratoAdapter.ViewHolder>{
    private List<Inmueble> inmuebles;
    private Context context;
    private LayoutInflater inflater;

    public InmuebleConContratoAdapter(List<Inmueble> inmuebles, Context context, LayoutInflater inflater) {
        this.inmuebles = inmuebles;
        this.context = context;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public InmuebleConContratoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_inmueble_con_contrato, parent, false);
        return new InmuebleConContratoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inmueble propiedad = inmuebles.get(position);
        holder.tvDireccion.setText(inmuebles.get(position).getDireccion());
        Glide.with(context)
                .load(inmuebles.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivImagenInmueble);
    }

    @Override
    public int getItemCount() {

        return inmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDireccion;
        private ImageView ivImagenInmueble;
        private Button btContrato;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            ivImagenInmueble = itemView.findViewById(R.id.ivImagenInmueble);
            btContrato = itemView.findViewById(R.id.btContrato);
            btContrato.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    Inmueble inmueble = inmuebles.get(getLayoutPosition());
                    bundle.putSerializable("contrato", (Serializable) inmueble);
                    Navigation.findNavController(v).navigate(R.id.detalleContratoFragment, bundle);
                }
            });
        }
    }
}

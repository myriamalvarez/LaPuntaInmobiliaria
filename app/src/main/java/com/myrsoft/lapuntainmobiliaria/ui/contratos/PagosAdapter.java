package com.myrsoft.lapuntainmobiliaria.ui.contratos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myrsoft.lapuntainmobiliaria.R;
import com.myrsoft.lapuntainmobiliaria.modelo.Pago;

import java.util.List;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder> {
private Context context;
private List<Pago> lista;
private LayoutInflater inflater;

    public PagosAdapter(List<Pago> lista, Context context, LayoutInflater inflater) {
        this.context = context;
        this.lista = lista;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public PagosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_pago, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagosAdapter.ViewHolder holder, int position) {
    holder.codigoP.setText(String.valueOf(lista.get(position).getIdPago()));
    holder.numero.setText(String.valueOf(lista.get(position).getNumero()));
    holder.codigoC.setText(String.valueOf(lista.get(position).getContrato().getIdContrato()));
    holder.importe.setText(String.valueOf(lista.get(position).getImporte()));
    holder.fecha.setText(String.valueOf(lista.get(position).getFechaDePago()));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    TextView codigoP, numero, codigoC, importe, fecha;
    public ViewHolder(@NonNull View itemView){
        super(itemView);
        codigoP = itemView.findViewById(R.id.tvCodigo);
        numero = itemView.findViewById(R.id.tvNumero);
        codigoC = itemView.findViewById(R.id.tvCodigoContrato);
        importe = itemView.findViewById(R.id.tvImporte);
        fecha = itemView.findViewById(R.id.tvFecha);
    }
    }
}

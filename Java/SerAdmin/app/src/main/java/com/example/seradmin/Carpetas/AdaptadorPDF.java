package com.example.seradmin.Carpetas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.seradmin.R;
import com.example.seradmin.Recycler.AdaptadorListado;

import java.util.ArrayList;

public class AdaptadorPDF extends RecyclerView.Adapter<AdaptadorPDF.ViewHolder>{
    private ArrayList<Archivos> archivosList;

    public interface ItemClickListener {
        void onClick(View view, int position, Archivos archivosList);
    }

    private AdaptadorPDF.ItemClickListener clickListener;

    public void setClickListener(AdaptadorCarpetas.ItemClickListener itemClickListener) {
        //this.clickListener = itemClickListener;
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

    private AdaptadorPDF.RecyclerViewClickListener listener;

    public AdaptadorPDF(java.util.ArrayList<Archivos> dataSet) {
        archivosList = dataSet;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView nomArchivo;
        private final TextView fechaMod;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            nomArchivo = (TextView) v.findViewById(R.id.nomArchivo);
            fechaMod = (TextView) v.findViewById(R.id.fechaMod);
        }


        public TextView getNomArchivo() {
            return nomArchivo;
        }
        public TextView getUltimaMod() {
            return fechaMod;
        }

        public void onClick(View view) {
            // Si tengo un manejador de evento lo propago con el índice
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition(), archivosList.get(getAdapterPosition()));
        }

    }

    @Override
    public AdaptadorPDF.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.caja_archivos, viewGroup, false);
        //AdaptadorPDF.ViewHolder viewHolder = new AdaptadorPDF().ViewHolder(v);
        AdaptadorPDF.ViewHolder viewHolder = null;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdaptadorPDF.ViewHolder holder, int position) {
        holder.getNomArchivo().setText(archivosList.get(position).getNombre());
        holder.getUltimaMod().setText((CharSequence) archivosList.get(position).getUltimaMod());

    }

    @Override
    public int getItemCount() {
        return archivosList.size();
    }

}

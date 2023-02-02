package com.example.seradmin.Carpetas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seradmin.R;
import com.example.seradmin.Recycler.AdaptadorListado;
import com.example.seradmin.Recycler.PerfilesClientes;

import java.util.ArrayList;

public class AdaptadorCarpetas extends RecyclerView.Adapter<AdaptadorListado.ViewHolder>{
    private ArrayList<PerfilesClientes> carpetasList;

    public interface ItemClickListener {
        void onClick(View view, int position, PerfilesClientes perfilesClientes);
    }

    private AdaptadorCarpetas.ItemClickListener clickListener;

    public void setClickListener(AdaptadorListado.ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

    private AdaptadorListado.RecyclerViewClickListener listener;

    public AdaptadorListado(java.util.ArrayList<PerfilesClientes> dataSet) {
        perfilesList = dataSet;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView nomClienteCajaPerfiles1;
        private final TextView letraNombre;
        private final ImageView imagenCajaPerfiles;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            nomClienteCajaPerfiles1 = (TextView) v.findViewById(R.id.nomClienteCajaPerfiles1);
            letraNombre = (TextView) v.findViewById(R.id.Letra);
            imagenCajaPerfiles = (ImageView) v.findViewById(R.id.imagenCajaPerfiles);

        }


        public TextView getNomCliente() {
            return nomClienteCajaPerfiles1;
        }

        public TextView getLetraNom() {
            return letraNombre;
        }

        public ImageView getImagenPerfil() {
            return imagenCajaPerfiles;
        }

        public void onClick(View view) {
            // Si tengo un manejador de evento lo propago con el índice
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition(), perfilesList.get(getAdapterPosition()));
        }

    }


    @Override
    public AdaptadorListado.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.caja_perfiles, viewGroup, false);
        AdaptadorListado.ViewHolder viewHolder = new AdaptadorListado.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdaptadorListado.ViewHolder holder, int position) {
        holder.getNomCliente().setText(perfilesList.get(position).getNombre() + perfilesList.get(position).getApellidos());
        holder.getLetraNom().setText(perfilesList.get(position).getLetra());
        //holder.getImagenPerfil().setImageResource(perfilesList.get(position).getImagen());

    }

    @Override
    public int getItemCount() {
        return perfilesList.size();
    }

}
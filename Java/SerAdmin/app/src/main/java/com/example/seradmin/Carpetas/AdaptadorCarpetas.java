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
//
//public class AdaptadorCarpetas extends RecyclerView.Adapter<AdaptadorCarpetas.ViewHolder>{
//    private ArrayList<Carpetas> carpetasList;
//
//    public interface ItemClickListener {
//        void onClick(View view, int position, Carpetas carpetasList);
//    }
//
//    private AdaptadorCarpetas.ItemClickListener clickListener;
//
//    public void setClickListener(AdaptadorCarpetas.ItemClickListener itemClickListener) {
//        this.clickListener = itemClickListener;
//    }
//
//    public interface RecyclerViewClickListener {
//        void onClick(View v, int position);
//    }
//
//    private AdaptadorCarpetas.RecyclerViewClickListener listener;
//
//    public AdaptadorCarpetas(java.util.ArrayList<Carpetas> dataSet) {
//        carpetasList = dataSet;
//        this.listener = listener;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private final TextView nomCarpeta;
//        private final TextView fechaMod;
//
//        public ViewHolder(View v) {
//            super(v);
//            v.setOnClickListener(this);
//            nomCarpeta = (TextView) v.findViewById(R.id.nomCarpeta);
//            fechaMod = (TextView) v.findViewById(R.id.fechaMod);
//        }
//
//
//        public TextView getNomCarpeta() {
//            return nomCarpeta;
//        }
//        public TextView getUltimaMod() {
//            return fechaMod;
//        }
//
//        public void onClick(View view) {
//            // Si tengo un manejador de evento lo propago con el índice
//            if (clickListener != null) clickListener.onClick(view, getAdapterPosition(), carpetasList.get(getAdapterPosition()));
//        }
//
//    }
//
//    @Override
//    public AdaptadorCarpetas.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.caja_carpetas, viewGroup, false);
//        AdaptadorCarpetas.ViewHolder viewHolder = new AdaptadorCarpetas().ViewHolder(v);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(AdaptadorCarpetas.ViewHolder holder, int position) {
//        holder.getNomCarpeta().setText(carpetasList.get(position).getNombre());
//        holder.getUltimaMod().setText((CharSequence) carpetasList.get(position).getUltimaMod());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return carpetasList.size();
//    }
//
//}
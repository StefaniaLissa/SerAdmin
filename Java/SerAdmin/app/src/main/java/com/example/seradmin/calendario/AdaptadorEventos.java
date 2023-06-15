package com.example.seradmin.calendario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.seradmin.R;
import com.example.seradmin.Recycler.Cliente;
import com.example.seradmin.database.eventosDatabase.Evento;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdaptadorEventos extends RecyclerView.Adapter<AdaptadorEventos.ViewHolder>{

    private ArrayList<Evento> eventosList = new ArrayList<Evento>();

    private ArrayList<Evento> eventosList2;

    public interface ItemClickListener {
        void onClick(View view, int position, Evento evento);
    }

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public AdaptadorEventos(ArrayList<Evento> dataSet) {
        eventosList = dataSet;
        this.clickListener = clickListener;
    }

    public void filtrado(final String nombreCliente) {
        int longitud = nombreCliente.length();
        if (longitud == 0) {
            eventosList.clear();
            eventosList.addAll(eventosList2);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Evento> collecion = eventosList.stream()
                        .filter(i -> i.getTitulo().toLowerCase().contains(nombreCliente.toLowerCase()))
                        .collect(Collectors.toList());
                eventosList.clear();
                eventosList.addAll(collecion);
            } else {
                for (Evento c : eventosList2) {
                    if (c.getTitulo().toLowerCase().contains(nombreCliente.toLowerCase())) {
                        eventosList.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tituloEvento;
        private final TextView fechaEvento;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            tituloEvento = (TextView) v.findViewById(R.id.tituloEvento);
            fechaEvento = (TextView) v.findViewById(R.id.fechaEvento);

        }


        public TextView getTituloEvento() {
            return tituloEvento;
        }

        public TextView getFechaEvento() {
            return fechaEvento;
        }

        public void onClick(View view) {
            // Si tengo un manejador de evento lo propago con el índice
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition(), eventosList.get(getAdapterPosition()));
        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.caja_eventos, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTituloEvento().setText(eventosList.get(position).getTitulo());
        holder.getFechaEvento().setText(eventosList.get(position).getFechaInicio().toString());
    }

    @Override
    public int getItemCount() {
        return eventosList.size();
    }
}


package com.example.seradmin;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

//import com.example.firebasead.DescripcionArchivo;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdaptadorArchivos extends RecyclerView.Adapter<AdaptadorArchivos.ViewHolder> {
    private List<Archivos> listaArchivos;
    private ArrayList<Archivos> arrayListaArchivos;

    public AdaptadorArchivos(List<Archivos> listaArchivos) {
        this.listaArchivos = listaArchivos;
        arrayListaArchivos = new ArrayList<>();
        arrayListaArchivos.addAll(listaArchivos);
    }
    public void filtrado(final String nombreArchivo) {
        int longitud = nombreArchivo.length();
        if (longitud == 0) {
            listaArchivos.clear();
            listaArchivos.addAll(arrayListaArchivos);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Archivos> collecion = listaArchivos.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(nombreArchivo.toLowerCase()))
                        .collect(Collectors.toList());
                listaArchivos.clear();
                listaArchivos.addAll(collecion);
            } else {
                for (Archivos c : arrayListaArchivos) {
                    if (c.getNombre().toLowerCase().contains(nombreArchivo.toLowerCase())) {
                        listaArchivos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.caja_archivos, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Archivos archivos = listaArchivos.get(position);
        holder.nombre.setText(archivos.getNombre());
        holder.fecha.setText(archivos.getFechaCreacion());
    }
    @Override
    public int getItemCount() {
        return listaArchivos.size();
    }

    public interface ItemClickListener {
        void onClick(View view, Archivos listaArchivos);
    }
    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //private final TextView nombre, id, propietario;
        private final TextView nombre, fecha;
        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            nombre = (TextView) v.findViewById(R.id.idNombreArchivo1);
            fecha = (TextView) v.findViewById(R.id.fechaMod);
        }

        public void onClick(View view) {
            // Si tengo un manejador de evento lo propago con el índice
            if (clickListener != null) clickListener.onClick(view, listaArchivos.get(getAdapterPosition()));

            Archivos archivo = listaArchivos.get(getAdapterPosition());
//            Intent intent = new Intent(view.getContext(), Login.class);
//            intent.putExtra("archivo", archivo);
//            view.getContext().startActivity(intent);
//            ((Activity) view.getContext()).finish();
            openFile(view, archivo.getNombre(), archivo.getDniCliente());
        }

    }

    private void openFile(View view, String fileName, String dni_cliente) {
        Log.d(TAG, fileName + " " + dni_cliente);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child(dni_cliente).child("pdfs").child(fileName);

        storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
            // Obtener la URL de descarga del archivo
            String fileUrl = uri.toString();

            // Crear un Intent con la acción VIEW y la URL del archivo
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(fileUrl), "application/pdf");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            try {
                // Iniciar la actividad para abrir el archivo con una aplicación externa
                view.getContext().startActivity(intent);
            } catch (ActivityNotFoundException e) {
                // Manejar el caso en el que no haya ninguna aplicación en el dispositivo capaz de abrir el archivo PDF
                //Toast.makeText(requireContext(), "No se encontró ninguna aplicación para abrir el archivo", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(exception -> {
            // Ha ocurrido un error al obtener la URL de descarga del archivo
            // Maneja el error aquí
        });
    }

}

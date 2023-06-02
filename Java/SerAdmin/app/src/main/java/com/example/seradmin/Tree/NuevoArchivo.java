package com.example.seradmin.Tree;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.seradmin.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;

public class NuevoArchivo extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;

    private Button selectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_archivo);

        selectButton = findViewById(R.id.selected);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPDF();
            }
        });
    }

    private void selectPDF() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select PDF"), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedPDF = data.getData();
            FirebaseStorage mStorageRef = FirebaseStorage.getInstance();
            // Aquí puedes realizar acciones con el archivo PDF seleccionado, como subirlo a un servidor
            StorageReference storageRef = mStorageRef.getReference().child("pdfs").child(selectedPDF.getLastPathSegment());

            // Sube el archivo a Firebase Storage
            UploadTask uploadTask = storageRef.putFile(selectedPDF);

            // Registra un listener para la finalización de la carga
            uploadTask.addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // La carga del archivo se ha completado exitosamente
                    // Puedes obtener la URL de descarga del archivo usando storageRef.getDownloadUrl()
                    storageRef.getDownloadUrl().addOnSuccessListener(downloadUrl -> {
                        String fileUrl = downloadUrl.toString();
                        // Aquí puedes hacer algo con la URL del archivo subido, como guardarla en una base de datos
                        // o mostrarla al usuario
                    });
                } else {
                    // La carga del archivo ha fallado
                    Exception exception = task.getException();
                    // Maneja el error de carga del archivo aquí
                }
            });
        }
        }
    }

//    private StorageReference mStorageRef;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_nuevo_archivo);
//        // Create a Cloud Storage reference from the app
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        final StorageReference referencia = mStorageRef.child("texto/"+System.currentTimeMillis()+"."+"txt");
//        InputStream archivo = getResources().openRawResource((R.raw.NuevoDocumentode));
//
//        referencia.putStream(archivo).
//                addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        Toast.makeText(NuevoArchivo.this, "Se ha subido", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(NuevoArchivo.this, "No Se ha subido", Toast.LENGTH_SHORT).show();
//
//
//                    }
//                });
//    }
//}
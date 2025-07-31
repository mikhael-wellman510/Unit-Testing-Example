package com.example.Sport_Station.FirebaseService.Repository;

import com.example.Sport_Station.FirebaseService.Entity.Mahasiswa;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MahasiswaRepository {

    private static final  String COLLECTION_NAME = "mahasiswa";
    private final Firestore db;

    public Mahasiswa saveMahasiswa(Mahasiswa mahasiswa) throws ExecutionException, InterruptedException {

        // Todo -> Auto membuat id random -> Kek UUID
        // Todo -> Memakai add supaya generate ID baru
        ApiFuture<DocumentReference> addedDocRef = db.collection(COLLECTION_NAME).add(mahasiswa);
        mahasiswa.setId(addedDocRef.get().getId());

        return mahasiswa;
    }

    public Mahasiswa save(Mahasiswa mahasiswa){

        DocumentReference addedDocRef =  db.collection(COLLECTION_NAME).document();
        mahasiswa.setId(addedDocRef.getId());
        ApiFuture<WriteResult> add = addedDocRef.set(mahasiswa);


        return mahasiswa;

    }

    public Mahasiswa findById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()){
            Mahasiswa mahasiswa = document.toObject(Mahasiswa.class);
            log.info("Hasil mahasiswa : {} " , mahasiswa);
            return mahasiswa;
        }else {
            throw new RuntimeException("Id Tidak Di temukan");
        }

    }

    public Mahasiswa updated(Mahasiswa mahasiswa) throws ExecutionException, InterruptedException {
        // Todo -> Updated Existing Document
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(mahasiswa.getId());

        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();

        if (document.exists()){
            // Todo -> Async update one Field
            ApiFuture<WriteResult> writeResult = docRef.set(mahasiswa);
            return mahasiswa;
        }else {
            throw new RuntimeException("Id tidak ditemukan , Gagal Updated");
        }

    }


    public String deleted(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);

        ApiFuture<DocumentSnapshot>future = docRef.get();

        DocumentSnapshot document = future.get();

        if (document.exists()){
            ApiFuture<WriteResult> writeResult = docRef.delete();
            return "Success Deleted " + id;
        }else {
            throw new RuntimeException("Id Not Found");
        }

    }


    public List<Mahasiswa>findAll() throws ExecutionException, InterruptedException {

        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Mahasiswa> listMahasiswa = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents){

            Mahasiswa mhs = document.toObject(Mahasiswa.class);

            listMahasiswa.add(mhs);
        }

        return listMahasiswa;
    }




}

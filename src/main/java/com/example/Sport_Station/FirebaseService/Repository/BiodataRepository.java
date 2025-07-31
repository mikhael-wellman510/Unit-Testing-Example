package com.example.Sport_Station.FirebaseService.Repository;

import com.example.Sport_Station.FirebaseService.Dto.BiodataResponse;
import com.example.Sport_Station.FirebaseService.Entity.Biodata;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
@RequiredArgsConstructor
public class BiodataRepository {

    private static final String COLLECTION_NAME = "biodata";
    private final Firestore db;

    public Biodata save(Biodata biodata) throws ExecutionException, InterruptedException {

        // Todo -> menambahkan dokumen baru
        DocumentReference addDocRef = db.collection(COLLECTION_NAME).document();
        biodata.setId(addDocRef.getId());
        // todo -> paki api future untuk save secara async
        ApiFuture<WriteResult> writeResultApiFuture = addDocRef.set(biodata);

        return biodata;
    }

    public Biodata findById(String id) throws ExecutionException, InterruptedException {
        // Todo -> Mengambil refrensi ke dokumen dengan id tertentu
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
        // Todo -> Mengambil data dokumen dari firestore secara async
        ApiFuture<DocumentSnapshot> future = docRef.get();
        // todo -> object yg berisi data tsb
        DocumentSnapshot document = future.get();

        if (document.exists()){
            Biodata biodata = document.toObject(Biodata.class);

            return biodata;
        }else {
            throw new RuntimeException("Id Not Found");
        }
    }

    public Biodata updated(Biodata biodata) throws ExecutionException, InterruptedException {
        // Todo -> mengambil data berdasarkan id nya
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(biodata.getId());

        // Todo -> mengambil data secara async
        ApiFuture<DocumentSnapshot>future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()){
            Biodata bio = document.toObject(Biodata.class);

            assert bio != null;

            bio.setName(biodata.getName());
            bio.setAddress(biodata.getAddress());
            bio.setAge(biodata.getAge());
            bio.setHobby(biodata.getHobby());
            bio.setHeight(biodata.getHeight());
            bio.setUpdatedAt(new Date());

            // Todo -> set data (Gunakan merge , supaya data lama tidak hilang)
            ApiFuture<WriteResult> writeResult = docRef.set(bio , SetOptions.merge());
            return bio;
        }else {
            throw  new RuntimeException("Id Not Found");
        }
    }

    public String deleted(String id) throws ExecutionException, InterruptedException {
        // Todo - > Mengambil satu refirensi dokumen yg spesifik
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();

        if (document.exists()){
            ApiFuture<WriteResult> write = docRef.delete();


            return "Success Deleted " + id;
        }else {
            throw new RuntimeException("Id Not Found");
        }

    }

    public List<Biodata> findAll() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Biodata> bio = new ArrayList<>();

        for (QueryDocumentSnapshot document : documents){
            Biodata biodata = document.toObject(Biodata.class);
            bio.add(biodata);
        }

        return bio;
    }

    // Todo -> find by name
    public List<Biodata> findByName(String name) throws ExecutionException, InterruptedException {
        // todo -> mendapatkan semua nya (find all doc)
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).whereEqualTo("name", name).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Biodata> data = new ArrayList<>();

        for(DocumentSnapshot document :  documents){
            Biodata bio = document.toObject(Biodata.class);
            data.add(bio);
        }

        return data;
    }

}

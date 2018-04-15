package com.kylecorry.bb1102.trash;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import okhttp3.OkHttpClient;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ClarifaiController {

    private ClarifaiClient client;

    public ClarifaiController(){
        Scanner scanner = new Scanner(getClass().getResourceAsStream("/api-key.txt"));
        String key = scanner.nextLine();
        client = new ClarifaiBuilder(key).client(new OkHttpClient()).buildSync();
    }


    public List<Classification> getClassifications(File file){
        List<ClarifaiOutput<Concept>> classifications = classify(file);
        List<Concept> data = classifications.get(0).data();

        List<Classification> objects = new ArrayList<>(data.size());

        for (Concept concept: data){
            objects.add(new Classification(concept.name(), (double) concept.value()));
        }

        objects.sort(Comparator.comparingDouble(Classification::getProbability).reversed());

        return objects;
    }


    private List<ClarifaiOutput<Concept>> classify(File file){
        return client.getDefaultModels().generalModel().predict()
                .withMaxConcepts(200)
                .withMinValue(0.5)
                .withInputs(ClarifaiInput.forImage(file)).executeSync().get();
    }

}

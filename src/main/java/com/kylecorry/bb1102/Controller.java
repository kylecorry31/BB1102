package com.kylecorry.bb1102;

import com.jfoenix.controls.JFXRadioButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label classificationsLbl;

    @FXML
    private ImageView trashImage;

    @FXML
    private JFXRadioButton weightedRadio, tallyRadio, highestRadio;

    private List<Classification> lastClassifications;

    private ITrashDetector getDetectionAlgorithm(){
        if(weightedRadio.isSelected()){
            return new WeightedTrashDetector();
        } else if (tallyRadio.isSelected()){
            return new TallyTrashDetector();
        } else {
            return new HighestProbabilityTrashDetector();
        }
    }

    public void chooseImage() throws IOException {
        classificationsLbl.setText("Classifying, please wait...");
        DialogFileSelector fileSelector = new DialogFileSelector();
        File file = fileSelector.openFile("Images", "*.jpg", "*.jpeg", "*.png", "*.gif");
        if(file == null){
            return;
        }

        trashImage.setImage(new Image(new FileInputStream(file)));

        Platform.runLater(() -> {
            ClarifaiController clarifai = new ClarifaiController();
            try {
                lastClassifications = clarifai.getClassifications(file);

                runClassification();

            } catch (Exception e){
                classificationsLbl.setText("Error classifying image");
            }
        });
    }

    private void runClassification(){
        if(lastClassifications != null){
            if(lastClassifications.isEmpty()){
                classificationsLbl.setText("No classifications");
            } else {
                ITrashDetector trashDetector = getDetectionAlgorithm();
                double isTrash = trashDetector.containsTrash(lastClassifications, new TrashRepo());
                classificationsLbl.setText(String.format("Plastic probability: %.2f", isTrash));
                LogManager.getInstance().log(getClass().getSimpleName(), String.format("Plastic probability: %.3f, classifications: %s", isTrash, lastClassifications));
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        weightedRadio.selectedProperty().addListener(observable -> runClassification());

        tallyRadio.selectedProperty().addListener(observable -> runClassification());

        highestRadio.selectedProperty().addListener(observable -> runClassification());
    }
}

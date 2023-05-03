package com.example.mediaplayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;


public class HelloController implements Initializable {

    public BorderPane container;
    public Label curTime;
    public Label totTime;

    @FXML
    private MediaPlayer mediaPlayer;

    @FXML
    private Slider duration;

    @FXML
    private MediaView mediaView;

    @FXML
    private Button music;

    @FXML
    private ImageView pause;

    @FXML
    private ImageView play;

    @FXML
    private ImageView stop;

    @FXML
    private Button video;

    @FXML
    private Slider volume;

    @FXML
    private File file;

    @FXML
    void music(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("C:\\Users\\Sello\\Music\\4 Your Eyez Only"));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Media Player", "*.mp3"));
        file = chooser.showOpenDialog(null);
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView.setMediaPlayer(mediaPlayer);

        volume.setValue(mediaPlayer.getVolume() * 100);
        volume.valueProperty().addListener(observable -> mediaPlayer.setVolume(volume.getValue() / 100));

        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            duration.setValue(newValue.toSeconds());
            duration.setMax(mediaPlayer.getTotalDuration().toSeconds());
            curTime.setText(("" + newValue.toSeconds()).charAt(0) + ("" + newValue.toSeconds()).charAt(1) + "");
            String dur = mediaPlayer.getMedia().getDuration().toMinutes() + "";
            String durSec = mediaPlayer.getMedia().getDuration().toSeconds() + "";

            String curMin = mediaPlayer.getCurrentTime().toMinutes() + "";
            String curSec = mediaPlayer.getCurrentTime().toSeconds() + "";

            StringBuilder manip = new StringBuilder( curSec.charAt(0) + "" + curSec.charAt(1));
            manip = new StringBuilder(Double.parseDouble(manip.toString()) % 60 + "");
            if (manip.charAt(1) == '.')
            {
                char temp = manip.charAt(0);
                manip.setCharAt(0, '0');
                manip.setCharAt(1, temp);
            }

            curTime.setText(("" + curMin.substring(0, curMin.indexOf('.')) + ":") + "" + manip.charAt(0) + "" + manip.charAt(1));
            totTime.setText(("" + dur.substring(0, dur.indexOf('.')) + ":") + "" + durSec.charAt(0) + "" + durSec.charAt(1));
        });
        duration.setOnMouseDragged(event1 -> mediaPlayer.seek(javafx.util.Duration.seconds(duration.getValue())));
        duration.setOnMousePressed(event1 -> {
            mediaPlayer.seek(javafx.util.Duration.seconds(duration.getValue()));
        });
    }

    @FXML
    void video(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("C:\\Users\\Sello\\Videos"));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Media Player", "*.mp4"));
        file = chooser.showOpenDialog(null);
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView.setMediaPlayer(mediaPlayer);

//        DoubleProperty width = mediaView.fitWidthProperty();
//        DoubleProperty height = mediaView.fitHeightProperty();

//        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
//        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));

        volume.setValue(mediaPlayer.getVolume() * 100);
        volume.valueProperty().addListener(observable -> mediaPlayer.setVolume(volume.getValue() / 100));

        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            duration.setValue(newValue.toSeconds());
            duration.setMax(mediaPlayer.getTotalDuration().toSeconds());
            curTime.setText(("" + newValue.toSeconds()).charAt(0) + ("" + newValue.toSeconds()).charAt(1) + "");
            String dur = mediaPlayer.getMedia().getDuration().toMinutes() + "";
            String durSec = mediaPlayer.getMedia().getDuration().toSeconds() % 60 + "";

            String curMin = mediaPlayer.getCurrentTime().toMinutes() + "";
            String curSec = mediaPlayer.getCurrentTime().toSeconds() + "";

            StringBuilder manip = new StringBuilder( curSec.charAt(0) + "" + curSec.charAt(1));
            manip = new StringBuilder(Double.parseDouble(manip.toString()) % 60 + "");

            if (manip.charAt(1) == '.')
            {
                char temp = manip.charAt(0);
                manip.setCharAt(0, '0');
                manip.setCharAt(1, temp);
            }

            curTime.setText(("" + curMin.substring(0, curMin.indexOf('.')) + ":") + "" + manip.charAt(0) + "" + manip.charAt(1));
            totTime.setText(("" + dur.substring(0, dur.indexOf('.')) + ":") + "" + durSec.charAt(0) + "" + durSec.charAt(1));
        });

        duration.setOnMouseDragged(event1 -> mediaPlayer.seek(javafx.util.Duration.seconds(duration.getValue())));
        duration.setOnMousePressed(event1 -> {
            mediaPlayer.seek(javafx.util.Duration.seconds(duration.getValue()));
        });
    }

    public void play(){
        if (mediaPlayer == null)
            return;

        if (mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING)
            mediaPlayer.play();
    }

    public void pause(){
        if (mediaPlayer == null)
            return;

        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
            mediaPlayer.pause();
    }

    public void stop(){
        if (mediaPlayer == null)
            return;

        if (mediaPlayer.getStatus() != MediaPlayer.Status.STOPPED)
            mediaPlayer.stop();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        mediaView.setFitWidth(container.getBoundsInParent().getWidth());
    }
}

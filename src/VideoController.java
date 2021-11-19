import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;

/**
 *
 * @author Victor
 */

public class VideoController implements Initializable {

    private Stage stage;
    private Scene scene;
    @FXML
    private Button selecionaArquivo;

    @FXML
    private TextArea areaTexto;

    @FXML
    private MediaView mediaView;

    @FXML
    private Button playButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button buttonAvanca;

    @FXML
    private Button voltarButton;

    public File file;

    public File file2;

    public String caminhoTxt;

    public String caminhoArquivo;

    public Media media;

    public Scanner input;

    public MediaPlayer mediaPlayer;

    @FXML
    void avancaImagem(ActionEvent event) throws IOException {
        for (Arquivo.indice = 0; Arquivo.indice < Arquivo.vetor.length; Arquivo.indice++) {
            Arquivo.vetor[Arquivo.indice] = null;
        }
        Arquivo.indice = 0;
        Parent root = FXMLLoader.load(getClass().getResource("telas/iniciar.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        areaTexto.setEditable(false);
        areaTexto.setWrapText(true);
        caminhoTxt = Arquivo.caminho + ".txt";
        caminhoArquivo = Arquivo.caminho + ".mp4";
        file = new File(caminhoArquivo);
        file2 = new File(caminhoTxt);
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        try {
            input = new Scanner(file2);
            input.useDelimiter("\\A");
            if (input.hasNext()) {
                areaTexto.setText(input.next());

            }
            input.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

    }

    @FXML
    void playVideo(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    void pauseVideo(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    void resetVideo(ActionEvent event) {
        if (mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
        }

    }

    @FXML
    void avancaLista(ActionEvent event) throws IOException {
        Arquivo.indice++;

        if (Arquivo.vetor[Arquivo.indice] == null) {
            System.out.println("Fim dos arquivos");
        } else {
            System.out.println(Arquivo.vetor[Arquivo.indice]);
            Arquivo.caminho = Arquivo.vetor[Arquivo.indice];
            Parent root = FXMLLoader.load(getClass().getResource("telas/video.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    @FXML
    void voltarLista(ActionEvent event) throws IOException {
        if (Arquivo.indice == 0) {
            System.out.println("Fim dos arquivos");
        }
        if (Arquivo.vetor[Arquivo.indice] == null) {
            Arquivo.indice--;
        } else {
            Arquivo.indice--;
            System.out.println(Arquivo.vetor[Arquivo.indice]);
            Arquivo.caminho = Arquivo.vetor[Arquivo.indice];
            Parent root = FXMLLoader.load(getClass().getResource("telas/video.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

}

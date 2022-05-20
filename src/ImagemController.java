import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Victor
 */

public class ImagemController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button avancaCena;

    @FXML
    private TextArea areaTexto;

    @FXML
    private ImageView imageView;

    @FXML
    private Button buttonVoltar;

    @FXML
    private Button buttonAvancar;

    @FXML
    private TextField campoPesquisa;

    @FXML
    private Button buttonPesquisa;

    public String caminhoTxt;
    public String caminhoArquivo;
    public File arquivoFoto;
    public File arquivoTexto;
    public Image image;
    public Scanner input;

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
    public void initialize(java.net.URL location, ResourceBundle resources) {
        caminhoTxt = Arquivo.caminho + ".txt";
        caminhoArquivo = Arquivo.caminho + ".jpg";
        arquivoFoto = new File(caminhoArquivo);
        arquivoTexto = new File(caminhoTxt);
        image = new Image(arquivoFoto.toURI().toString());
        areaTexto.setEditable(false);
        areaTexto.setWrapText(true);
        imageView.setImage(image);

        try {
            input = new Scanner(arquivoTexto);
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
    void avancarArquivo(ActionEvent event) throws IOException {
        Arquivo.indice++;

        if (Arquivo.vetor[Arquivo.indice] == null) {
            System.out.println("Fim dos arquivos");
        } else {
            System.out.println(Arquivo.vetor[Arquivo.indice]);
            Arquivo.caminho = Arquivo.vetor[Arquivo.indice];
            Parent root = FXMLLoader.load(getClass().getResource("telas/imagem.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        }
    }

    @FXML
    void voltarArquivo(ActionEvent event) throws IOException {

        if (Arquivo.indice < 0) {
            System.out.println("Fim dos arquivos");
        }
        if (Arquivo.vetor[Arquivo.indice] == null) {
            Arquivo.indice--;
        } else {
            Arquivo.indice--;
            System.out.println(Arquivo.vetor[Arquivo.indice]);
            Arquivo.caminho = Arquivo.vetor[Arquivo.indice];
            Parent root = FXMLLoader.load(getClass().getResource("telas/imagem.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }


    @FXML
    void pesquisaArquivo(ActionEvent event) {

    }
}

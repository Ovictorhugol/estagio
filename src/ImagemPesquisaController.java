import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 *
 * @author Victor
 */

public class ImagemPesquisaController implements Initializable {

    @FXML
    private Button avancaCena;

    @FXML
    private Button buttonVoltarPesquisa;

    @FXML
    private ImageView imageView;

    @FXML
    private TextArea areaTexto;


    public String caminhoTxt;
    public String caminhoArquivo;
    public File arquivoFoto;
    public File arquivoTexto;
    public Image image;


    @FXML
    void avancaImagem(ActionEvent event) throws IOException {
        for (Arquivo.indice = 0; Arquivo.indice < Arquivo.vetor.length; Arquivo.indice++) {
            Arquivo.vetor[Arquivo.indice] = null;
            Arquivo.pesquisaVetor[Arquivo.indice] = null;
        }
          Arquivo.indice = 0;
          Parent root = FXMLLoader.load(getClass().getResource("telas/iniciar.fxml"));
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          Scene scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
    }

    @FXML
    void voltaPesquisa(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("telas/pesquisaImagem.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        caminhoTxt = Arquivo.telaImagemPesquisa[0] + ".txt";
        caminhoArquivo = Arquivo.telaImagemPesquisa[0] + ".jpg";
        arquivoFoto = new File(caminhoArquivo);
        arquivoTexto = new File(caminhoTxt);
        image = new Image(arquivoFoto.toURI().toString());
        areaTexto.setEditable(false);
        areaTexto.setWrapText(true);
        imageView.setImage(image);

        try {
            Scanner input = new Scanner(arquivoTexto);
            input.useDelimiter("\\A");
            if (input.hasNext()) {
                areaTexto.setText(input.next());

            }
            input.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

}

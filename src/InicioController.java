import java.io.File;
import java.io.IOException;
import java.util.List;

import com.drew.imaging.ImageProcessingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author Victor
 */

public class InicioController {
    private Stage stage;
    private Scene scene;
    @FXML
    private Button iniciarButton;

    @FXML
    private Button selecaoArquivo;

    @FXML
    private Button buttonArquivo;

    @FXML
    private ListView listView;

    public String caminhoArquivo;
    public String formato;

    private File file;
    private String texto;

    public ExtratorMetadadosJPEGMPEG4 extrator;

    public ImagemController imagem;

    @FXML
    void selecaoArquivo(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("jpg", "*.jpg"));
        fc.getExtensionFilters().addAll(new ExtensionFilter("mp4", "*.mp4"));
        List<File> arquivo = fc.showOpenMultipleDialog(null);
        if (arquivo != null) {
            for (int i = 0; i < arquivo.size(); i++) {
                listView.getItems().add(arquivo.get(i).getName());
                file = arquivo.get(i);
                caminhoArquivo = file.getAbsolutePath();

                formato = caminhoArquivo.substring(caminhoArquivo.length() - 3);
                texto = caminhoArquivo.substring(0, caminhoArquivo.length() - 4);
                Arquivo.vetor[i] = texto;

                Arquivo.caminho = texto;
                texto += ".txt";
                System.out.println(Arquivo.vetor[i]);

                try {
                    extrator = new ExtratorMetadadosJPEGMPEG4(caminhoArquivo, formato);
                } catch (ImageProcessingException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }

            }
        } else {
            System.out.println("Arquivo não encontrado");
        }

    }

    @FXML
    void IniciaCena(ActionEvent event) throws Exception {

        switch (formato) {
        case "jpg": {

            Parent imagem = FXMLLoader.load(getClass().getResource("telas/imagem.fxml"));
            Stage janela = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(imagem);
            janela.setScene(scene);
            janela.show();

            break;
        }
        case "mp4": {
            Parent imagem = FXMLLoader.load(getClass().getResource("telas/video.fxml"));
            Stage janela = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(imagem);
            janela.setScene(scene);
            janela.show();

        }
        default:
            break;
        }

    }

}

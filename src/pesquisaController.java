import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Victor
 */

public class pesquisaController implements Initializable {

    @FXML
    private ListView <String> listView;

    @FXML
    private Button buttonInicio;

    @FXML
    void selecionaArquivo(MouseEvent event) throws IOException {  
      Arquivo.telaImagemPesquisa[0] = listView.getSelectionModel().getSelectedItem();
      Parent root = FXMLLoader.load(getClass().getResource("telas/imagemPesquisa.fxml"));
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }

    @FXML
    void voltaInicio(ActionEvent event) throws IOException {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      for (int i = 0; i < 95; i++) {
        if(Arquivo.pesquisaVetor[i]!=null && Arquivo.pesquisaVetor[i]!= Arquivo.pesquisaVetor[i + 1 ] ) { 
          listView.getItems().addAll(Arquivo.pesquisaVetor[i]);
        }
      }
    }

}

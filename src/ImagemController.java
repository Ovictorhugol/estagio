import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
    private Stage janela;

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
            Arquivo.pesquisaVetor[Arquivo.indice] = null;
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
        System.out.println(Arquivo.indice);
        if (Arquivo.indice <= 0) {
            System.out.println("Fim dos arquivos");
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
    void pesquisaArquivo(ActionEvent event) throws IOException {
        FileReader arquivo = null;
        String pesquisaUppercase = null;
        String linhaUppercase = null;
        BufferedReader leitor = null;
        String pesquisa = String.valueOf(campoPesquisa.getText());
        pesquisaUppercase = pesquisa.toUpperCase();
        int indicePesquisa = 0;
      
        try {
            for(int j =0; j<Arquivo.vetor.length; j++) {
                arquivo = new FileReader(new File(Arquivo.vetor[j]+".txt"));
                // System.out.println(Arquivo.vetor[j]);
                Scanner scanner = new Scanner(arquivo);
                while(scanner.hasNextLine()) {
                    String linha  = scanner.nextLine();
                    String [] line = linha.split(" ");
                    // System.out.println(pesquisa);
                    
                        
                        for(int i = 0; i < line.length;i++) {
                            // System.out.println(line[i]+"\n");
                            // System.out.println(i + line[i]);
                            linhaUppercase = line[i].toUpperCase();
                            if(stringCompare(linhaUppercase, pesquisaUppercase) == 0){
                                System.out.println("Encontrei essa palavra");
                                
                                Arquivo.pesquisaVetor[indicePesquisa] = Arquivo.vetor[j];
                                System.out.println(Arquivo.pesquisaVetor[indicePesquisa]);
                                indicePesquisa++;
                            }else{
                                // System.out.println("Não encontrei nenhum palavra relacionada a essa pesquisa");
                                Alert alert = new Alert(AlertType.WARNING);
                                alert.setTitle("Falha na pesquisa");
                                alert.setContentText("Não encontrei nenhum palavra relacionada a essa pesquisa");

                            }
                        }
                }
            }

            
        } catch (IOException erro) {
            
        }
        Parent root = FXMLLoader.load(getClass().getResource("telas/pesquisaImagem.fxml"));
        janela = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        janela.setScene(scene);
        janela.show();
    }
    public static int stringCompare(String str1, String str2)
    {
  
        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);
  
        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);
  
            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        if (l1 != l2) {
            return l1 - l2;
        }
 
        else {
            return 0;
        }
    }
  
}

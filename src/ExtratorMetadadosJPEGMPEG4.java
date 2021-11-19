
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.mp4.Mp4MetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author joao
 */
public class ExtratorMetadadosJPEGMPEG4 {

    Metadata metadataJPG, metadataMP4;
    ArrayList<String> metadata;

    public ExtratorMetadadosJPEGMPEG4(String nomearquivo, String formato) throws IOException, ImageProcessingException {
        extraiMetadados(nomearquivo, formato);
        // extraiMetadados("c:/temp/midias/teste.mp4", "mp4");

    }

    public void extraiMetadados(String nomeArquivo, String extensao)
            throws IOException, JpegProcessingException, ImageProcessingException {
        String nomeArquivoSemExtensao;

        nomeArquivoSemExtensao = nomeArquivo.substring(0, nomeArquivo.length() - 4);
        switch (extensao.toUpperCase()) {
        case "JPG": {
            if ((new File(nomeArquivo)).getName().endsWith(".jpg")) {
                metadataJPG = JpegMetadataReader.readMetadata(new File(nomeArquivo));
                geraMetadadosJPG(nomeArquivoSemExtensao);
            } else {
                System.out.println("Extensão de arquivo não é JPG");
            }
            break;
        }
        case "MP4": {
            if ((new File(nomeArquivo)).getName().endsWith(".mp4")) {
                metadataMP4 = Mp4MetadataReader.readMetadata(new File(nomeArquivo));
                geraMetadadosMP4(nomeArquivoSemExtensao);
            } else {
                System.out.println("Extensão de arquivo não é MP4");
            }
            break;
        }
        }
    }

    public void geraMetadadosJPG(String nomeArquivo) {
        FileWriter arquivo;
        BufferedWriter escritor;
        try {
            nomeArquivo += ".txt";
            // System.out.println("Gerando metadados no arquivo: " + nomeArquivo + "\n");
            arquivo = new FileWriter(new File(nomeArquivo));
            escritor = new BufferedWriter(arquivo);
            escritor.append("METADADOS EXTRAÍDOS - JPEG\r\n");
            escritor.append("==========================\r\n");
            for (Directory entrada : metadataJPG.getDirectories()) {
                escritor.append("\r\nGRUPO DE DADOS: " + entrada.getName() + "\r\n");
                for (Tag tag : entrada.getTags()) {
                    if (!tag.getTagName().contains("Unknown")) {
                        // System.out.println(tag.getTagName() + ": " + tag.getDescription());
                        escritor.append(tag.getTagName() + ": " + tag.getDescription() + "\r\n");
                    }
                }
            }
            System.out.println("Metadados gerados com sucesso!\n");
            escritor.close();
        } catch (IOException erro) {

        }
    }

    public void geraMetadadosMP4(String nomeArquivo) {
        FileWriter arquivo;
        BufferedWriter escritor;
        try {
            nomeArquivo += ".txt";
            // System.out.println("Gerando metadados no arquivo: " + nomeArquivo + "\n");
            arquivo = new FileWriter(new File(nomeArquivo));
            escritor = new BufferedWriter(arquivo);
            escritor.append("METADADOS EXTRAÍDOS - MP4\r\n");
            escritor.append("=========================\r\n");
            for (Directory entrada : metadataMP4.getDirectories()) {
                escritor.append("\r\nGRUPO DE DADOS: " + entrada.getName() + "\r\n");
                for (Tag tag : entrada.getTags()) {
                    if (!tag.getTagName().contains("Unknown")) {
                        // System.out.println(tag.getTagName() + ": " + tag.getDescription());
                        escritor.append(tag.getTagName() + ": " + tag.getDescription() + "\r\n");
                    }
                }
            }
            System.out.println("Metadados gerados com sucesso!\n");
            escritor.close();
        } catch (IOException erro) {

        }
    }

    // public static void main(String args[]) {
    // ExtratorMetadadosJPEGMPEG4 aplicacao;
    // try {
    // aplicacao = new ExtratorMetadadosJPEGMPEG4();
    // } catch (IOException | ImageProcessingException ex) {
    // Logger.getLogger(ExtratorMetadadosJPEGMPEG4.class.getName()).log(Level.SEVERE,
    // null, ex);
    // }
    // }

}

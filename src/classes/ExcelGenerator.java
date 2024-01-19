package classes;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javafx.stage.FileChooser;

public class ExcelGenerator {
    
    public void generateExcel(List<Produit> produits) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le fichier Excel");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Fichiers Excel", "*.xlsx")
        );
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Données des produits");

            // En-têtes des colonnes
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID du Produit");
            headerRow.createCell(1).setCellValue("Nom du Produit");
            headerRow.createCell(2).setCellValue("Prix");
            headerRow.createCell(3).setCellValue("Description");
            headerRow.createCell(4).setCellValue("Quantité");
            headerRow.createCell(5).setCellValue("Image");
            headerRow.createCell(6).setCellValue("Catégorie");
            // Ajoutez les en-têtes des autres colonnes ici

            // Ajoutez les données des produits
            for (int i = 0; i < produits.size(); i++) {
                Row dataRow = sheet.createRow(i + 1); // +1 pour éviter d'écraser l'en-tête
                Produit produit = produits.get(i);

                dataRow.createCell(0).setCellValue(produit.getId_prod());
                dataRow.createCell(1).setCellValue(produit.getNom_prod());
                dataRow.createCell(2).setCellValue(produit.getPrix_prod());
                dataRow.createCell(3).setCellValue(produit.getdescription_prod());
                dataRow.createCell(4).setCellValue(produit.getquantite());
                dataRow.createCell(5).setCellValue(produit.getImage());
                dataRow.createCell(6).setCellValue(produit.getCategorie().getNom_categorie());
                // Ajoutez les données des autres colonnes ici
            }

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                workbook.close();
                System.out.println("Fichier Excel généré avec succès.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Autres méthodes et classes ici si nécessaire
}
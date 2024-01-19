package service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import classes.User;
import javafx.scene.control.Cell;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class ExcelSender {
    public void generateExcel(TableView<User> tableView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Excel");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Excel Files", "*.xlsx")
        );
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");

            // Create header row
           // Row headerRow = sheet.createRow(0);
            //String[] headers = {
              //  "Username", "Email", "Password", "Role",
               // "Image", "Age", "Sexe"
            //};
            // En-têtes des colonnes
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Username");
            headerRow.createCell(1).setCellValue("Email");
            headerRow.createCell(2).setCellValue("Password");
            headerRow.createCell(3).setCellValue("Role");
            headerRow.createCell(4).setCellValue("Picture");
            headerRow.createCell(5).setCellValue("Age");
            headerRow.createCell(6).setCellValue("Gender");
            // Ajoutez les en-têtes des autres colonnes ici

            for (int i = 0; i < tableView.getItems().size(); i++) {
                Row dataRow = sheet.createRow(i + 1); // +1 pour éviter d'écraser l'en-tête
                User user = tableView.getItems().get(i);
                
                 dataRow.createCell(0).setCellValue(user.getUsername());
                    dataRow.createCell(1).setCellValue(user.getUsername());
                    dataRow.createCell(2).setCellValue( user.getMail());
                    dataRow.createCell(3).setCellValue( user.getMdp());
                    dataRow.createCell(4).setCellValue(user.getRole());
                    dataRow.createCell(5).setCellValue(user.getImage());
                    dataRow.createCell(6).setCellValue(Integer.toString(user.getAge()));
                    dataRow.createCell(6).setCellValue(user.getSexe());
          
                
            }

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                workbook.close();
                System.out.println("Excel generated successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

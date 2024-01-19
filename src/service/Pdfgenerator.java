/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author THEOLDISBACK
 */
public class Pdfgenerator {
      public void generatePdfe() {
          
          
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
        );
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();

                PdfPTable table = new PdfPTable(9); // 3 columns
                table.setWidthPercentage(100); // set table width to 100% of page
EventService s = new EventService();
                // Add table headers
          
                    
                      addTableCell(table, "nom_event ");
                      addTableCell(table, "descript ");
                      addTableCell(table, "date_event ");
                      addTableCell(table, "heure_event ");
                      addTableCell(table, "lieu_event ");
                      addTableCell(table, "nb_participants ");
                      addTableCell(table, "image ");
                      addTableCell(table, "organisateur  ");
          
             
              

                // Add table rows
                for (int i = 0; i < s.afficherevent().size(); i++) {
                  
                    addTableCell(table, ""+s.afficherevent().get(i).getNom_event());
                    addTableCell(table, ""+s.afficherevent().get(i).getDescript());
                    addTableCell(table, ""+s.afficherevent().get(i).getDate_event());
                    addTableCell(table, ""+s.afficherevent().get(i).getHeure_event());
                    addTableCell(table, ""+s.afficherevent().get(i).getLieu_event());
                    addTableCell(table, ""+s.afficherevent().get(i).getNb_participant());
                    addTableCell(table, ""+s.afficherevent().get(i).getImage());
                    addTableCell(table, ""+s.afficherevent().get(i).getOrganisateur());
                }

                document.add(table);
                document.close();
                System.out.println("PDF generated successfully.");
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addTableCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new com.itextpdf.text.Paragraph(text));
        table.addCell(cell);
    }
 public void generateExcel() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Excel");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel Files", "*.xlsx")
        );
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");

            // Add header row
            Row headerRow = sheet.createRow(0);
         
            Cell headerCell2 = headerRow.createCell(0);
            headerCell2.setCellValue("nom_event ");
            Cell headerCell3 = headerRow.createCell(1);
            headerCell3.setCellValue("descript ");

              Cell headerCell4 = headerRow.createCell(2);
            headerCell4.setCellValue("date_event ");
              Cell headerCell5 = headerRow.createCell(3);
            headerCell5.setCellValue("heure_event ");
              Cell headerCell6 = headerRow.createCell(4);
            headerCell6.setCellValue("lieu_event ");
              Cell headerCell7 = headerRow.createCell(5);
            headerCell7.setCellValue("nb_participants ");
              Cell headerCell8 = headerRow.createCell(6);
            headerCell8.setCellValue("image ");
              Cell headerCell9 = headerRow.createCell(7);
            headerCell9.setCellValue("organisateur ");
            
            EventService f = new EventService();
        
            // Add data rows
            for (int i = 0; i <f.afficherevent().size(); i++) {
                Row row = sheet.createRow(i);
               
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(f.afficherevent().get(i).getNom_event());
                                Cell cell2 = row.createCell(1);

                cell2.setCellValue(f.afficherevent().get(i).getDescript());
                          Cell cell3 = row.createCell(2);

                cell3.setCellValue(f.afficherevent().get(i).getDate_event());
                            Cell cell4 = row.createCell(3);

                cell4.setCellValue(f.afficherevent().get(i).getHeure_event());
                           Cell cell5 = row.createCell(4);

                cell5.setCellValue(f.afficherevent().get(i).getLieu_event());
                            Cell cell6 = row.createCell(5);

                cell6.setCellValue(f.afficherevent().get(i).getNb_participant());
                              Cell cell7 = row.createCell(6);

                cell7.setCellValue(f.afficherevent().get(i).getImage());
                               Cell cell8 = row.createCell(7);

                cell8.setCellValue(f.afficherevent().get(i).getOrganisateur());
                             Cell cell22 = row.createCell(8);

            }

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                workbook.close();
                System.out.println("Excel generated successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

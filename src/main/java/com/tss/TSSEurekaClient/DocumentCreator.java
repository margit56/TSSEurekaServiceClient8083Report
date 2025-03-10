/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tss.TSSEurekaClient;

import org.springframework.stereotype.Component;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.PageSize;

import java.text.SimpleDateFormat;

import com.tss.services.ProductService;
import com.tss.to.ProductTO;
        
import java.util.stream.Stream;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.File;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author mariusz
 * https://www.baeldung.com/java-pdf-creation
 * Ja uzywam biblioteki OpenPDF
 * https://migueldoctor.medium.com/how-to-create-pdf-files-with-java-and-openpdf-2eaf9c91e65b
 * 
 * Zmieniły się ścieżki pakietów  z import com.itextpdf.text.* na import com.lowagie.text.*;
 * https://github.com/eugenp/tutorials/blob/master/pdf/src/main/java/com/baeldung/pdf/PDFSampleMain.java
 * 
 * takie same obrazki na każdej stronie - page handler i ustawianie tła na każdej stronie
 * https://kb.itextpdf.com/home/it7kb/faq/how-to-set-a-fixed-background-image-for-all-my-pages
 * 
 * https://kb.itextpdf.com/home/it5kb/examples/background-images
 */
@Component
public class DocumentCreator {
    
    @Autowired
    ProductService productService;

    public File createPDF()
    {
        Document document = new Document();
        LocalDateTime now = LocalDateTime.now();  
        DateTimeFormatter dtfFile = DateTimeFormatter.ofPattern("HH_mm_ss");
        try
        {
        //Tworzenie wydruku bez użycia pliku na dysku - nie zrobione 
        //File memoryPDF = new File("D://Report.pdf");
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PdfWriter.getInstance(document, outputStream);
        
        //Operacja tworzenia wydruku z użyciem pliku na dysku
         
        File filePDF = new File("D://Report_"+dtfFile.format(now)+".pdf");
        FileOutputStream fileOutputStream = new FileOutputStream(filePDF);
        PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
        


        document.open();
        Font fontSmall = FontFactory.getFont(FontFactory.COURIER, 12, Color.BLACK);
        Font fontMedium = FontFactory.getFont(FontFactory.COURIER, 16, Color.BLACK);
        Font fontBig = FontFactory.getFont(FontFactory.COURIER, 24, Color.BLACK);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
        
        //Image image = Image.getInstance("https://kesizo.github.io/assets/images/kesizo-logo-6-832x834.png");
        Image image = Image.getInstance(getClass().getClassLoader().getResource("image.png"));
        image.scaleAbsolute(50f,50f);
        
//        Tło wydruku
//        PdfContentByte canvas = writer.getDirectContentUnder();
//        image.scaleAbsolute(PageSize.A4.getWidth(),PageSize.A4.getHeight());
//        image.setAbsolutePosition(0, 0);
//        canvas.addImage(image);

        document.add(image);
        document.add(new Paragraph("Lista produktów",fontBig));

        
        document.add(new Paragraph("               ", fontBig));  
        
        PdfPTable table = new PdfPTable(5);
        
        addTableHeader(table);
        addRows(table);
        
        document.add(table);
        
        document.add(new Paragraph("                 ", fontBig));        
        document.add(new Paragraph("Czas wydruku: "+dtf.format(now), fontSmall));

//        document.setFooter(new HeaderFooter(new Phrase("Czas raportu: "+dtf.format(now)), new Phrase(".")));
                

        document.close();
        
        return filePDF;
        }
        catch(Exception ex)
        {
          return null;           
        }
    }
    
        private void addTableHeader(PdfPTable table) {
        Stream.of("ID", "Nazwa", "Cena", "Data", "Opis")
        .forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(java.awt.Color.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });
    }

    private void addRows(PdfPTable table) {
        
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.ENGLISH);
      java.util.Date date = new java.util.Date();  

        
        java.util.List<ProductTO> productList = productService.productList();
        
        for(ProductTO product: productList)
        {
                    table.addCell(product.getId().toString());
                    table.addCell(product.getName());
                    table.addCell(product.getPrice().toString());
                    table.addCell(formatter.format(product.getUpdated()));
                    table.addCell(product.getDescription());                    
        }
        //productList.clear();

//                    table.addCell("data 1");
//                    table.addCell("data 2");
//                    table.addCell("data 3");
//                    table.addCell("data 4");
//                    table.addCell("data 5");

    }


    
}

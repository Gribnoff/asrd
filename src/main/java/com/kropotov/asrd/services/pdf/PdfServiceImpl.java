package com.kropotov.asrd.services.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.kropotov.asrd.exceptions.EmailException;
import com.kropotov.asrd.exceptions.PdfWriteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class PdfServiceImpl implements PdfService {

    @Override
    public void generatePlainPdf(String pathAndFileName, String text) {
        writeFile(new Document(), pathAndFileName, text);
    }

    @Override
    public void generateCustomPageSizePdf(String pathAndFileName, String text) {
        Rectangle rectangle = new Rectangle(216f, 720f);
        Document document = new Document(rectangle, 36f, 7f, 108f, 180f);
        writeFile(document, pathAndFileName, text);
    }

    @Override
    public void generateCustomFontAndColorPdf(String pathAndFileName, String text) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream((pathAndFileName)));
        } catch (DocumentException | FileNotFoundException ex) {
            log.error("Ошибка получения экземпляра документа {}.", pathAndFileName);
            throw new PdfWriteException("Ошибка получения экземпляра документа " +  pathAndFileName, ex);
        }
        document.open();
        try {
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
            document.add(new Paragraph(text, font));

            Font font2 = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.RED);
            Chunk chunk = new Chunk(text, font2);
            document.add(chunk);
        } catch (DocumentException ex) {
            log.error("Ошибка при модификации документа {}.", pathAndFileName);
            throw new PdfWriteException("Ошибка при модификации документа " + pathAndFileName, ex);
        }
        document.close();
    }

    @Override
    public void generateTextWithImagePdf(String imageFileName, String pathAndFileName, String text) {
        Path path = null;
        try {
            path = Paths.get(new ClassPathResource(imageFileName).getURI());
        } catch (IOException ex) {
            log.error("Ошибка обработки файла изображения {}.", imageFileName);
            throw new PdfWriteException("Ошибка обработки файла изображения " +  imageFileName, ex);

        }
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pathAndFileName));
        } catch (DocumentException | FileNotFoundException ex) {
            log.error("Ошибка получения экземпляра документа {}.", pathAndFileName);
            throw new PdfWriteException("Ошибка получения экземпляра документа " +  pathAndFileName, ex);
        }
        document.open();
        Image img = null;
        try {
            img = Image.getInstance(path.toAbsolutePath().toString());
        } catch (BadElementException | IOException ex) {
            log.error("Ошибка при обработке вставки изображения.");
            throw new PdfWriteException("Ошибка при обработке вставки изображения " + path.toAbsolutePath().toString(), ex);
        }
        try {
            document.add(img);
            document.add(new Paragraph(text));
        } catch (DocumentException ex) {
            log.error("Ошибка при модификации документа {}.", pathAndFileName);
            throw new PdfWriteException("Ошибка при модификации документа " + pathAndFileName, ex);
        }
        document.close();
    }

    private void writeFile(Document document, String pathAndFileName, String text) {
        try {
            PdfWriter.getInstance(document, new FileOutputStream((pathAndFileName)));
        } catch (DocumentException | FileNotFoundException ex) {
            log.error("Ошибка получения экземпляра документа {}.", pathAndFileName);
            throw new PdfWriteException("Ошибка получения экземпляра документа " +  pathAndFileName, ex);
        }
        document.open();
        try {
            document.add(new Paragraph(text));
        } catch (DocumentException ex) {
            log.error("Ошибка при модификации документа {}.", pathAndFileName);
            throw new PdfWriteException("Ошибка при модификации документа " + pathAndFileName, ex);
        }
        document.close();
    }
}

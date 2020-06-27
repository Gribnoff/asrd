package com.kropotov.asrd.services.pdf;

import org.springframework.stereotype.Service;

@Service
public interface PdfService {
    void generatePlainPdf(String pathAndFileName, String text);

    void generateCustomPageSizePdf(String pathAndFileName, String text);

    void generateTextWithImagePdf(String imageFileName, String pathAndFileName, String text);

    void generateCustomFontAndColorPdf(String pathAndFileName, String text);
}

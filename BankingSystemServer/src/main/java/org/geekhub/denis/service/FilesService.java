package org.geekhub.denis.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.geekhub.denis.entity.CardTransactionEntity;
import org.geekhub.denis.repository.CardTransactionRepositoryImpl;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * @author Apilat Denis
 * Date :08.05.2023
 * Time :12:30
 * Project Name :gh-hw-denis-apilat
 */

@Component
@AllArgsConstructor
public class FilesService {
    private final CardTransactionRepositoryImpl transactionRepository;

    public byte[] allTransactionsPDF() throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        List<CardTransactionEntity> cardTransactionEntities = transactionRepository.allTransactions();
        for (CardTransactionEntity data : cardTransactionEntities) {
            document.add(new Paragraph("Message: " + data.getMessage()));
        }
        document.close();
        return outputStream.toByteArray();
    }

}

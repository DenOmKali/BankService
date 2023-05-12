package org.geekhub.denis.controller;

import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.geekhub.denis.service.FilesService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

/**
 * @author Apilat Denis
 * Date :08.05.2023
 * Time :12:44
 * Project Name :gh-hw-denis-apilat
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FilesController {
    private final FilesService filesService;

    @GetMapping("/pdfTransactions")
    public ResponseEntity<InputStreamResource> generatePdf() throws DocumentException {
        byte[] pdfContent = filesService.allTransactionsPDF();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "AllTransactions.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        InputStreamResource pdfStream = new InputStreamResource(new ByteArrayInputStream(pdfContent));
        return new ResponseEntity<>(pdfStream, headers, HttpStatus.OK);
    }
}

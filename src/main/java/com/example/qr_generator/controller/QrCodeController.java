package com.example.qr_generator.controller;

import com.example.qr_generator.dto.QrCodeGenerateRequest;
import com.example.qr_generator.dto.QrCodeGenerateResponse;
import com.example.qr_generator.services.QrCodeGerenateService;
import com.google.zxing.WriterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/qrcode")
@RestController
public class QrCodeController {


    private final QrCodeGerenateService qrCodeGerenateService;

    public QrCodeController(QrCodeGerenateService qrCodeGerenateService) {
        this.qrCodeGerenateService = qrCodeGerenateService;
    }

    @PostMapping
    public ResponseEntity<QrCodeGenerateResponse> generateQrCode (@RequestBody QrCodeGenerateRequest qrCodeGenerateRequest) throws IOException, WriterException {
        try{
            QrCodeGenerateResponse response = this.qrCodeGerenateService.generateQrCode(qrCodeGenerateRequest.text());
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
}

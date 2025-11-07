package com.example.qr_generator.services;

import com.example.qr_generator.dto.QrCodeGenerateResponse;
import com.example.qr_generator.ports.StoragePort;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class QrCodeGerenateService {

    private final StoragePort storagePort;

    public QrCodeGerenateService(StoragePort storagePort) {
        this.storagePort = storagePort;
    }

    public QrCodeGenerateResponse generateQrCode(String qrCode) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCode, BarcodeFormat.QR_CODE, 200, 200 );
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

        byte[] pngData = pngOutputStream.toByteArray();

        String url = storagePort.uploadFile(pngData, UUID.randomUUID().toString() , "image/png");
        return new QrCodeGenerateResponse(url);
    }
}

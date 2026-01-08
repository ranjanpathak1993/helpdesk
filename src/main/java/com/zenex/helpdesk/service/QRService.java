package com.zenex.helpdesk.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class QRService {

    public byte[] generateQRCode(String text) throws Exception {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                text,
                BarcodeFormat.QR_CODE,
                250,
                250
        );

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(
                bitMatrix,
                "PNG",
                pngOutputStream
        );

        return pngOutputStream.toByteArray();
    }
}


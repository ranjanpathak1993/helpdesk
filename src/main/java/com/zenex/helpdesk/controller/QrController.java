package com.zenex.helpdesk.controller;

import com.zenex.helpdesk.service.QRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QrController {

    @Autowired
    private QRService qrService;

    @GetMapping(value = "/qr/ticket", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] ticketQr() throws Exception {

        // QR will open ticket form directly
        String url = "http://34.236.37.18:8081/ticket/new";
        return qrService.generateQRCode(url);
    }
}

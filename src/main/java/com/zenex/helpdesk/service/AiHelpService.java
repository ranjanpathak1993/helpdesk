package com.zenex.helpdesk.service;

import org.springframework.stereotype.Service;

@Service
public class AiHelpService {

    public String getReply(String message) {

        if (message == null || message.trim().isEmpty()) {
            return "Please describe your issue so I can assist you.";
        }

        String msg = message.toLowerCase();

        if (msg.contains("email") || msg.contains("outlook") || msg.contains("mail")) {
            return """
            I can help with email issues. Please try:
            1. Check your internet connection.
            2. Restart your email application.
            3. Verify your email password.
            4. Contact IT support if the issue continues.
            """;
        }

        if (msg.contains("printer")) {
            return """
            Printer troubleshooting steps:
            1. Ensure the printer is powered ON.
            2. Check cable or network connection.
            3. Restart the printer.
            4. Reinstall printer drivers if needed.
            """;
        }

        if (msg.contains("network") || msg.contains("lan")) {
            return """
            Network issue steps:
            1. Check LAN cable connection.
            2. Restart router or switch.
            3. Disable and enable network adapter.
            4. Contact IT support if needed.
            """;
        }

        if (msg.contains("wifi") || msg.contains("wi-fi")) {
            return """
            WiFi troubleshooting:
            1. Turn WiFi ON.
            2. Reconnect to the correct network.
            3. Restart router and system.
            4. Forget and reconnect WiFi network.
            """;
        }

        if (msg.contains("vpn")) {
            return """
            VPN issue steps:
            1. Check your internet connection.
            2. Verify VPN credentials.
            3. Restart VPN application.
            4. Contact IT if VPN still fails.
            """;
        }

        if (msg.contains("hardware") || msg.contains("mouse") || msg.contains("keyboard")) {
            return """
            Hardware troubleshooting:
            1. Reconnect the device.
            2. Check for physical damage.
            3. Restart your system.
            4. Try the device on another system.
            """;
        }

        if (msg.contains("software") || msg.contains("application")) {
            return """
            Software issue steps:
            1. Restart the application.
            2. Check for updates.
            3. Reinstall if required.
            4. Ensure system compatibility.
            """;
        }

        if (msg.contains("lock") || msg.contains("blocked")) {
            return """
            Application lock issue:
            1. Ensure you have proper access rights.
            2. Log out and log in again.
            3. Restart your system.
            4. Contact IT to unlock the application.
            """;
        }

        if (msg.contains("sap") || msg.contains("erp")) {
            return """
            SAP / ERP issue guidance:
            1. Verify login credentials.
            2. Check VPN or internet connection.
            3. Select correct SAP client.
            4. Contact SAP support team.
            """;
        }

        if (msg.contains("tablet") || msg.contains("ipad")) {
            return """
            Tablet issue steps:
            1. Restart the tablet.
            2. Check WiFi connection.
            3. Update the operating system.
            4. Charge the device fully.
            """;
        }

        if (msg.contains("display") || msg.contains("screen") || msg.contains("monitor")) {
            return """
            Display issue steps:
            1. Check display cable connection.
            2. Adjust screen resolution.
            3. Restart monitor and system.
            4. Try an external monitor.
            """;
        }

        return """
        I am not fully sure about this issue.
        Please explain the problem in more detail
        or select a help category.
        """;
    }
}

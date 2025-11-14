package com.pluralsight.deli.receipts;

import com.pluralsight.deli.orders.Order;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptFile {
    private static final DateTimeFormatter DT_FMT = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    public Path saveReceipt(Order order) {
        try {
            Path dir = Paths.get("receipts");
            Files.createDirectories(dir);

            String fileName = DT_FMT.format(LocalDateTime.now()) + ".txt";
            Path combined = dir.resolve(fileName);

        try (BufferedWriter bw = Files.newBufferedWriter(combined, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW)) {
            bw.write(order.printDisplay());
        }
            System.out.println("✅ Receipt saved: " + combined.toAbsolutePath());
            return combined;
        } catch (IOException e) {
            throw new RuntimeException("❌ Error saving the receipt", e);
        }
    }
}

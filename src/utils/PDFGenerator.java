/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
/**
 *
 * @author Naufal Charis
 */
public class PDFGenerator {
    public static void generateFromHtml(String htmlPath, String outputPath, Map<String, String> placeholders) throws Exception {
    System.out.println("📄 Membuka template HTML: " + htmlPath);
    File htmlFile = new File(htmlPath);
    if (!htmlFile.exists()) {
        throw new FileNotFoundException("Template tidak ditemukan: " + htmlPath);
    }

    String html = new String(java.nio.file.Files.readAllBytes(htmlFile.toPath()), StandardCharsets.UTF_8);

    // Tampilkan placeholder yang akan digantikan
    System.out.println("📌 Placeholder yang akan digantikan:");
    for (Map.Entry<String, String> entry : placeholders.entrySet()) {
        System.out.println("    {{ " + entry.getKey() + " }} → " + entry.getValue());
        html = html.replace("{{ " + entry.getKey() + " }}", entry.getValue());
    }

    try (OutputStream os = new FileOutputStream(outputPath)) {
        System.out.println("🖨️  Mencetak PDF ke: " + outputPath);
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        builder.withHtmlContent(html, htmlFile.getParentFile().toURI().toString());
        builder.toStream(os);
        builder.run();
        System.out.println("✅ PDF berhasil dibuat!");
    } catch (Exception e) {
        System.err.println("❌ Gagal membuat PDF:");
        e.printStackTrace();
        throw e;
    }
}

}

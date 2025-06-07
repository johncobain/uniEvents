package br.edu.ifba.inf0008.uniEvents.services.generators;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

import br.edu.ifba.inf0008.uniEvents.utils.Lines;

public class FileGenerator {
  private static final DateTimeFormatter FILE_TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
  private static final String FILE_EXTENSION = ".txt";

  public String saveTextToFile(String content, String baseFileName) {
    try {
      Path path = Paths.get("reports");
      if (!Files.exists(path)) {
        Files.createDirectories(path);
        System.out.println("Directory 'reports' created.");
      }

      String timestamp = java.time.LocalDateTime.now().format(FILE_TIMESTAMP_FORMATTER);
      String fileName = baseFileName + "_" + timestamp + FILE_EXTENSION;
      Path filePath = path.resolve(fileName);

      try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))){
        writer.write(content);
      }

      System.out.println(Lines.successLine("File saved successfully: " + filePath));
      return filePath.toAbsolutePath().toString();
    } catch (IOException e) {
      System.err.println(Lines.errorLine("Error saving file: " + e.getMessage()));
      return null;
    }
  }
}

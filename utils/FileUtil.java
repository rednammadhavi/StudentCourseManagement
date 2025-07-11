package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static void appendLine(String filePath, String line) {
        try (FileWriter fw = new FileWriter(filePath, true);
                BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
        }
    }

    public static List<String> readAllLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null)
                lines.add(line);
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
        }
        return lines;
    }
}

package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class AppUtility {

    private static String absolutePath = new File("").getAbsolutePath();

    public static String getContentFromResourceFile(String fileName) {
        try {
            InputStream inputStream = AppUtility.class.getClassLoader().getResourceAsStream(fileName);
            return readFromInputStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public static String getContentFromFile(String fullyQualifiedPath) {
        try {
            return new String(Files.readAllBytes(Paths.get(absolutePath + fullyQualifiedPath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

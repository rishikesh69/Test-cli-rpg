package org.auto1.rpg.common.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExternalIO {
    private static final String RESOURCES = "src/main/resources/";

    private ExternalIO() {
    }

    public static ObjectOutputStream objectOutputStream(String basePath, String filename) throws IOException {
        File file = createFileIfDoesNotExist(absolutePath(basePath, filename));
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        return new ObjectOutputStream(fileOutputStream);
    }

    public static void stringToFile(String content, String basePath, String filename) throws IOException {
        File file = createFileIfDoesNotExist(absolutePath(basePath, filename));
        Files.write(Paths.get(file.getPath()), content.getBytes());
    }

    public static ObjectInputStream objectInputStream(String basePath, String filename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(absolutePath(basePath, filename));
        return new ObjectInputStream(fileInputStream);
    }

    /**
     * @return
     */
    public static String resourcesPath() {
        return RESOURCES;
    }

    /**
     * @param basePath
     * @param pathToFile
     * @return
     */
    private static String absolutePath(String basePath, String pathToFile) {
        return Paths.get(basePath, pathToFile).toAbsolutePath().toString();
    }

    /**
     * @param absolutePath
     * @return
     * @throws IOException
     */
    private static File createFileIfDoesNotExist(String absolutePath) throws IOException {
        File file = new File(absolutePath);
        if (file.exists()) return file;
        file.getParentFile().mkdirs();
        file.createNewFile();
        return file;
    }
}

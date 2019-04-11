package org.auto1.rpg.common.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.stream.Collectors;

public class InternalIO {
    private static final String DIR_SEPARATOR = "/";

    private InternalIO() {
    }

    /**
     * @param basePath
     * @param filename
     * @return
     * @throws IOException
     */
    public static ObjectInputStream objectInputStream(String basePath, String filename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(absolutePathInsideArchive(basePath, filename));
        return new ObjectInputStream(fileInputStream);
    }

    /**
     * @param basePath
     * @param filename
     * @return
     */
    public static String readAsString(String basePath, String filename) {
        InputStream resourceAsStream = InternalIO.class.getResourceAsStream(absolutePathInsideArchive(basePath, filename));
        return new BufferedReader(new InputStreamReader(resourceAsStream)).lines().collect(Collectors.joining("\n"));
    }

    /**
     * @param basePath
     * @param filename
     * @return
     */
    private static String absolutePathInsideArchive(String basePath, String filename) {
        return DIR_SEPARATOR + basePath + DIR_SEPARATOR + filename;
    }
}

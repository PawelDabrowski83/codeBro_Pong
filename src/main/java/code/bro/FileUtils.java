package code.bro;

import java.io.*;

public class FileUtils {

    protected static void copy(InputStream input, OutputStream output, int bufferSize)
            throws IOException {
        if (bufferSize < 1) {
            bufferSize = 1;
        }
        if (bufferSize > 100_000) {
            bufferSize = 100_000;
        }
        byte[] buf = new byte[bufferSize];
        int bytesRead = input.read(buf);
        while (bytesRead != -1) {
            output.write(buf, 0, bytesRead);
            bytesRead = input.read(buf);
        }
        output.flush();
    }

    public static byte[] readFile(File file) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        copy(new FileInputStream(file), baos, 1000);
        return baos.toByteArray();
    }

    public static void writeFile(File file, byte[] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        copy(bais, new FileOutputStream(file), 1000);
    }
}

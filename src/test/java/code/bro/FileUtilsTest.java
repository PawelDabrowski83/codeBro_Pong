package code.bro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class FileUtilsTest {

    @Test
    public void shouldReadFileGivenProperDataWork() {
        String filepath = "src/test/resources/testhighscore11.txt";
        File file = new File(filepath);
        byte[] contents = new byte[0];
        try {
            contents = FileUtils.readFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertArrayEquals(new byte[]{'1', '1'}, contents);
    }

    @Test
    public void shouldWriteFileGiven13Work() {
        String filepath = "src/test/resources/testhighscore13.txt";
        File file = new File(filepath);
        byte[] contents = new byte[]{'1', '3'};
        byte[] actual = new byte[0];
        try {
            FileUtils.writeFile(file, contents);
            actual = FileUtils.readFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertArrayEquals(contents, actual);
    }



}

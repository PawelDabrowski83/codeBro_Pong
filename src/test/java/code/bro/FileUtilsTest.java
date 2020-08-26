package code.bro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

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

    @DisplayName("should copy writes bytestreams properly")
    @ParameterizedTest
    @MethodSource("copyArgumentsProvider")
    void copy(byte[] expected, byte[] source, int bufferSize) {
        ByteArrayInputStream input = new ByteArrayInputStream(source);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            FileUtils.copy(input, output, bufferSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertArrayEquals(expected, output.toByteArray());
    }
    private static Stream<Arguments> copyArgumentsProvider() {
        return Stream.of(
                Arguments.of(
                        new byte[]{'1', '1', '2', '3'},
                        new byte[]{'1', '1', '2', '3'},
                        100
                ),
                Arguments.of(
                        new byte[0],
                        new byte[0],
                        10
                ),
                Arguments.of(
                        new byte[]{0b0011_0000, 0b0010_1010},
                        new byte[]{0b0011_0000, 0b0010_1010},
                        4
                ),
                Arguments.of(
                        new byte[]{'1', '2', '3', '4', '5', '6', '7', '8', '8'},
                        new byte[]{'1', '2', '3', '4', '5', '6', '7', '8', '8'},
                        2
                ),
                Arguments.of(
                        new byte[]{'1', '2', '3', '4', '5', '6', '7', '8', '8'},
                        new byte[]{'1', '2', '3', '4', '5', '6', '7', '8', '8'},
                        0
                ),
                Arguments.of(
                        new byte[]{'1', '2', '3', '4', '5', '6', '7', '8', '8'},
                        new byte[]{'1', '2', '3', '4', '5', '6', '7', '8', '8'},
                        Integer.MIN_VALUE
                ),
                Arguments.of(
                        new byte[]{'1', '2', '3', '4', '5', '6', '7', '8', '8'},
                        new byte[]{'1', '2', '3', '4', '5', '6', '7', '8', '8'},
                        Integer.MAX_VALUE
                ),
                Arguments.of(
                        new byte[]{
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7'
                        },
                        new byte[]{
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7',
                                '1', 'a', 'z', '2', '?', ' ', '1', 'w', 'o', '[', 'p', ';', '!', 'r', 'R', '7'
                        },
                        1
                )
        );
    }

}

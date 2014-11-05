package com.thoughtworks.iamcoach.pos.util;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class FileProcessorTest {

    @Test
    public void should_get_a_list() {

        FileProcessor fileProcessor = mock(FileProcessor.class);

        String fileName = "cart.txt";
        assertThat(fileProcessor.readFile(fileName).get(0)).isEqualTo("ITEM000001");
    }

    @Test(expected = NullPointerException.class)
    public void should_throws_exception() throws NullPointerException {
        FileProcessor.readFile("i");
    }

}
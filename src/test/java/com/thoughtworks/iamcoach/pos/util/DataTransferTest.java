package com.thoughtworks.iamcoach.pos.util;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class DataTransferTest {

    @Test
    public void should_transfer_data() {
        DataTransfer dataTransfer = new DataTransfer();
        assertThat(dataTransfer.transfer(3.4543)).isEqualTo(3.45);
        assertThat(dataTransfer.transfer(3.458)).isEqualTo(3.46);
    }
}

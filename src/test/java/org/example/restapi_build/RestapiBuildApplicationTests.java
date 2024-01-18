package org.example.restapi_build;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class RestapiBuildApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertThat(42).isEqualTo(42);
    }

}

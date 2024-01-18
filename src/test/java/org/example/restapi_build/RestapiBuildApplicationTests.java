package org.example.restapi_build;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

@JsonTest
class RestapiBuildApplicationTests {

    @Autowired
    private JacksonTester<CashCard> json; // Jackson JSON 구문 분석 래퍼이며 직렬화, 역직렬화에 사용


    @Test
    void contextLoads() {
        Assertions.assertThat(42).isEqualTo(42);
    }

    @Test
    void cashCardSerializationTest() throws IOException {
        CashCard cashCard = new CashCard(99L, 123.45);
        //Assertions.assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
        Assertions.assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id"); // 타입을 구문하고, 대소문자도 구별함
        Assertions.assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id") // 해당 속성의 값을 확인
                .isEqualTo(99);
        Assertions.assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
        Assertions.assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
                .isEqualTo(123.45);
    }

    @Test
    void cashCardDeserializationTest() throws IOException {
        // 문자열 블럭을 통해 테스트용 JSON 을 만들 수 있고, 그 문자열과 객체의 값 비교 가능
        // JacksonTester을 통해 JSON에서 자바로 역직렬화 과정
        String expected = """
           {
               "id":99,
               "amount":123.45
           }
           """;
        Assertions.assertThat(json.parse(expected))
                .isEqualTo(new CashCard(99L, 123.45));
        Assertions.assertThat(json.parseObject(expected).id()).isEqualTo(99L);
        Assertions.assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);
    }





}

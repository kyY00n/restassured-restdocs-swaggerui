package com.rosie.restdocsswaggerui.restassured;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.resourceDetails;
import static com.epages.restdocs.apispec.Schema.schema;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import com.epages.restdocs.apispec.ResourceSnippetDetails;
import com.epages.restdocs.apispec.Schema;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.restassured.RestDocumentationFilter;

public class HelloAcceptanceTest extends AcceptanceTest {

    private ResourceSnippetDetails API_정보 = resourceDetails()
            .summary("로지월드 들어가기 API")
            .description("로지 월드에 와서 환영을 받습니다.");
    private Schema 성공_응답_형식 = schema("HelloResponse");

    @Test
    void 파라미터에_이름을_넣고_요청한다() {
        // given
        var 요청_준비 = given(spec)
                .contentType(JSON)
                .filter(성공_응답_문서_만들기("hello-rosie-world-성공"));

        // when
        var 응답 = 요청_준비.when()
                .pathParam("name", "김크루")
                .get("/rosie/{name}");

        // then
        응답.then()
                .assertThat().statusCode(OK.value());
    }

    private RestDocumentationFilter 성공_응답_문서_만들기(String 제목) {
        return document(제목, API_정보.responseSchema(성공_응답_형식),
                pathParameters(parameterWithName("name").description("놀러와주신 분의 성함")),
                responseFields(fieldWithPath("messages").description("환영의 단어들"),
                        fieldWithPath("ps").description("로지가 추가로 전하는 말").optional()));
    }

    @Test
    void 코치님이_요청한다() {
        // given
        var 요청_준비 = given(spec)
                .contentType(JSON)
                .filter(실패_응답_문서_만들기("hello-rosie-world-코치라서-실패"));

        // when
        var 응답 = 요청_준비.when()
                .pathParam("name", "윤코치")
                .get("/rosie/{name}");

        // then
        응답.then()
                .assertThat().statusCode(BAD_REQUEST.value());
    }

    private RestDocumentationFilter 실패_응답_문서_만들기(String name) {
        return document(name, API_정보.responseSchema(schema("ErrorResponse")));
    }

}


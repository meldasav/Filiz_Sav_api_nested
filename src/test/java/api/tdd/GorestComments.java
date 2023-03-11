package api.tdd;

import api.gorest.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GorestComments {
    /**
     * {
     *     "code": 408,
     *     "meta": {
     *         "pagination": {
     *             "total": 2000,
     *             "pages": 211,
     *             "page": 1,
     *             "limit": 5,
     *             "links": {
     *                 "previous": null,
     *                 "current": "https://gorest.co.in/public/v1/comments?page=1",
     *                 "next": "https://gorest.co.in/public/v1/comments?page=2"
     *             }
     *         }
     *     },
     *     "data": [
     *         {
     *             "id": 2220,
     *             "post_id": 5585,
     *             "name": "Tecch Global",
     *             "email": "random email",
     *             "body": "random text"
     *         },
     *         {
     *             "id": 2221,
     *             "post_id": 5586,
     *             "name": "Tecch Global",
     *             "email": "random email",
     *             "body": "random text"
     *         },
     *         {
     *             "id": 2222,
     *             "post_id": 5587,
     *             "name": "Tecch Global",
     *             "email": "random email",
     *             "body": "random text"
     *         }
     *     ]
     * }
     */
   Response response;
@BeforeTest
    public void beforeTest(){
    RestAssured.baseURI = ConfigReader.getProperty("baseUrl");
    }

    @Test
    public void createComment(){

        Datum datum1 = Datum.builder()
                .id(2220)
                .post_id(5585)
                .name("Tecch Global")
                .email("random email")
                .body("random text").build();
        Datum datum2 = Datum.builder()
                .id(2221)
                .post_id(5586)
                .name("Tecch Global")
                .email("random email")
                .body("random text").build();
        Datum datum3 = Datum.builder()
                .id(2222)
                .post_id(5587)
                .name("Tecch Global")
                .email("random email")
                .body("random text").build();
        ArrayList<Datum> datum = new ArrayList<>(Arrays.asList(datum1,datum2,datum3));
        Links links = Links.builder()
                .previous(null)
                .current("https://gorest.co.in/public/v1/comments?page=1")
                .next("https://gorest.co.in/public/v1/comments?page=2")
                .build();
        Pagination pagination = Pagination.builder()
                .total(2000)
                .pages(211)
                .page(1)
                .limit(5)
                .links(links)
                .build();
        Meta meta = Meta.builder()
                .pagination(pagination)
                .build();
        CreateCommentPost createCommentPost = CreateCommentPost.builder()
                .code(408)
                .meta(meta)
                .data(datum)
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization",ConfigReader.getProperty("token"))
                .body(createCommentPost)
                .when().post("/public-api/comments")
                .then().log().all().statusCode(200).extract().response();

    }
}

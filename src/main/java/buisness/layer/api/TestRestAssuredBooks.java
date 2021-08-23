package buisness.layer.api;

import dataProvider.models.books.BooksItem;
import dataProvider.models.books.ReqAddListOfBooks;
import dataProvider.models.books.ResponseBooks;
import dataProvider.models.user.ReqUserAccount;
import dataProvider.models.user.ResGenToken;
import dataProvider.models.user.ResUserAccount;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;



/*import org.testng.annotations.Test;*/

public class TestRestAssuredBooks {
    //-------------User's Account Tests--------------------
RequestSpecification requestSpecAccount =new RequestSpecBuilder()
//вариант RequestSpecification через RequestSpecBuilder
        .setBaseUri("https://demoqa.com/Account/v1")
        .setContentType(ContentType.JSON)
        .build();
ResponseSpecification responseSpecAccount = new ResponseSpecBuilder()
//вариант ResponseSpecification через ResponseSpecBuilder
        .expectStatusCode(200)
        .expectContentType(ContentType.JSON)
        .expectResponseTime(Matchers.lessThan(5000L))//ответное время не более 5сы
        .build();

    @Test //Авторизация юзера
    public void userAuthorized() {
            given()
            .spec(requestSpecAccount)
            .basePath("/Authorized")
            .when().body(ReqUserAccount.getDefaultRequest()).post()//получаем данные из ДТО-файла "ReqAuthUser"
            .then()
            .spec(responseSpecAccount)
            .body(Matchers.equalTo("true"))//проверка наличия TRUE/FALSE в ответе
            .log().all();//Выводит весь ответ с статус-лайн и хидеррами
}
    @Test //Генерация токена
    public void userGenerateToken() {
        ResGenToken resGenToken =
                given()
                .spec(requestSpecAccount)
                .basePath("/GenerateToken")
                .when().body(ReqUserAccount.getDefaultRequest()).post()//получаем данные из ДТО-файла "ReqAuthUser"
                .then()
                .spec(responseSpecAccount)
                .body("status", Matchers.equalTo("Success"))
                .body("result", Matchers.containsString("User authorized successfully."))
                //проверка наличия "result" в ключе ответа
                .log().all()
                        .extract().as(ResGenToken.class);//взяли весь ответ для экземпляра класса ResGenToken
        String token = resGenToken.getToken();//взяли для переменной token токен из ответа
        System.out.println("Getting generated token: "+token);
    }
    @Test //Регистрация/создание юзера
    public void userRegistration() {
        ResUserAccount resUserAccount = given()
                .spec(requestSpecAccount)
                .basePath("/User")
                .when().body(ReqUserAccount.getDefaultRequest()).post()//получаем данные из ДТО-файла "ReqUserAccount"
                .then()
                .body( "message", Matchers.equalTo("User exists!"))//проверка наличия TRUE в ответе
                .statusCode(406)//уже ранее я его зарегистрировал
                .log().all()//Выводит весь ответ
                .extract().as(ResUserAccount.class);//
        //проверка соответствия имен
        assertThat(resUserAccount.username, Matchers.equalTo(ReqUserAccount.getDefaultRequest().userName));
        String USERID = resUserAccount.getUserId();//взяли для переменной USERID из ответа
        System.out.println("New User's ID: "+USERID);

              /*assertThat(resUserAccount
                .isNotNull()
                .extracting(ResUserAccount::getUsername)
                .isEqualTo(ReqUserAccount.getDefaultRequest().userName);*/
    }
    @Test //Delete User by {UUID}
    public void userDelete() {
        given()
                .spec(requestSpecAccount)
                .basePath("/User")
                .param("054be32b-e184-496e-b109-87cf66d0b0c4")
                .when().delete()
                .then()
                //.spec(responseSpecAccount)
                .statusCode(404)//user отсутствует. только один раз удалять
                .log().all();
    }


    //-------------BookStore Tests--------------------
    private RequestSpecification requestSpecBook() {
        //вариант RequestSpecification через метод requestSpecBook()
        return given().baseUri("https://demoqa.com/BookStore/v1")
                .contentType(ContentType.JSON);
    };

    @Test // Вытягиваем все книги
    public void GetBookStoreBooks() {
        ResponseBooks responseBooks = requestSpecBook()//Вариант записи ответа в переменную responseBooks
                .basePath("/Books")
                .when().get()
                .then()
                .statusCode(200)
                .header("Content-Length", "4514")//соответстие значения в хедере
                .body("books[0].author", Matchers.equalTo("Richard E. Silverman"))
                .extract().as(ResponseBooks.class);
        System.out.println(responseBooks.getBooks().get(0).getAuthor());//вариант вывода первого автора
    }

    @Test // Вытягиваем одну книгу по нужному параметру ISBN
    public void GetBookStoreOneBook() {
        var responseBooks = requestSpecBook()
                .basePath("/Book")
                .param("ISBN", "9781449337711")
                .when().get()
                .then()
                .statusCode(200).contentType(ContentType.JSON)
                .body("author", Matchers.equalTo("Glenn Block et al."))
                .extract().headers();//вытягиваем в переменную только хидеры
        System.out.println(responseBooks);
    }

    @Test // Добавляем ЛистКниг
    public void addListOfBooks() {
        var responseBooks = requestSpecBook()
                .basePath("/Books")
                .when().body(ReqAddListOfBooks.getDefaultRequest())
                .post()
                .then()
                .statusCode(200).contentType(ContentType.JSON);
    }

}
/*@BeforeTest
public void setFilter() {//передать лисенеры  в фильтры аллюра
    RestAssured.filters(new AllureRestAssured());
}*/
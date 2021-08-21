package buisness.layer.api;

import dataProvider.models.books.BooksItem;
import dataProvider.models.books.ReqAddListOfBooks;
import dataProvider.models.user.ReqAuthUser;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;


import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


/*import org.testng.annotations.Test;*/

public class TestRestAssuredBooks {
    private RequestSpecification requestSpecBook() {
        return given().baseUri("https://demoqa.com/BookStore/v1")///Authorized
                .contentType(ContentType.JSON);
        }
    private RequestSpecification requestSpecAccount() {
        return given().baseUri("https://demoqa.com/Account/v1")
                .contentType(ContentType.JSON);
        }
    @Test // Вытягиваем все книги
    public void GetBookStoreBooks() {
        List<BooksItem> responseBooks = requestSpecBook()
                .basePath("/Books")
                .when().get()
                .then()
                .statusCode(200)
                .header("Content-Length", "4514")
                .body("books[0].author", Matchers.equalTo("Richard E. Silverman"))
                .extract().jsonPath().getList("books", BooksItem.class);
        System.out.println(responseBooks);//вывод всего тела ответа
        System.out.println(responseBooks.get(0).getAuthor());//вывод искомого автора
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

/*    @Test // Добавляем ЛистКниг
    public void addListOfBooks() {
        var responseBooks = requestSpecBook()
                .basePath("/Books")
                .when()//.body(ReqAddListOfBooks.getDefaultRequest()).post()
                .then()
                .statusCode(401).contentType(ContentType.JSON);

    }*/
@Test //Авторизация юзера
    public void userAuthorized() {
    var responseAuthAcount = requestSpecAccount()
            .basePath("/Authorized")
            .body(ReqAuthUser.getDefaultRequest())

            .when().post()
            .then()
            .statusCode(200).contentType(ContentType.JSON);
}


}
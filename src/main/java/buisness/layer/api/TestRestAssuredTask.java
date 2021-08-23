package buisness.layer.api;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


/*import org.testng.annotations.Test;*/

public class TestRestAssuredTask {
    @Test
    public void TaskCircuitsSize() {
        var responseTask = requestSpecTask()
                .when().get()
                .then().statusCode(200)//проверка кода ответа 200?
                .contentType(ContentType.JSON) //проверка типа ответа Json?
                .body("MRData.CircuitTable.Circuits.size()", Matchers.equalTo(20))
                .and().body("MRData.CircuitTable.Circuits", hasSize(20));//дублирование проверки 20ти шт другим способом
        System.out.println(responseTask);
    }

    @Test
    public void TaskCompareHeader() {
        var responseTask = requestSpecTask()
                .param("text", "test")
                .when().get()
                .then()
                .statusCode(200)//проверка кода ответа 200?
                .contentType("application/json")  //проверка типа ответа Json?
                .header("Content-Length", "4551");  //проверка наличие в ответе
        // такого хедера с этим значением
        System.out.println(responseTask);
    }

    @Test
    public void TaskCompareName() {
        var responseTask = requestSpecTask()
                .when().get()
                .then()
                .statusCode(200)
                .body("MRData.CircuitTable.Circuits.find{it.circuitId== 'americas'}.circuitName",
                        Matchers.equalTo("Circuit of the Americas"));//проверка соответствия "circuitName"
        //для "Circuit of the Americas" в  объекте, где (circuitId== 'americas')
        System.out.println(responseTask);
    }

    private RequestSpecification requestSpecTask() {
        return given()
                .baseUri("http://ergast.com/api")
                .basePath("/f1/2017/circuits.json")
                .contentType(ContentType.JSON);
    }
}
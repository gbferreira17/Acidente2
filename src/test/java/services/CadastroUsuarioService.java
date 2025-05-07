package services;

import br.com.fiap.Acidente.model.Role;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.UsuarioModel;


import static io.restassured.RestAssured.given;

public class CadastroUsuarioService {

    final UsuarioModel usuarioModel = new UsuarioModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080/auth";

    public void setFieldsDelivery(String field, String value){

        switch (field){
            case "usuarioId" -> usuarioModel.setUsuarioId(Long.valueOf(value));
            case "nome" -> usuarioModel.setNome(value);
            case "email" -> usuarioModel.setEmail(value);
            case "password" -> usuarioModel.setPassword(value);
            case "role" -> usuarioModel.setRole(Role.valueOf(value));
            default -> throw new IllegalStateException("Unexpected field" + field);
        }

    }

    public void createDelivery(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(usuarioModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }



}

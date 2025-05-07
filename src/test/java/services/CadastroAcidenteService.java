package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.AcidenteModel;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CadastroAcidenteService {

    final AcidenteModel acidenteModel = new AcidenteModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080";
    private String idDelivery;

    String schemasPath = "src/test/resources/schemas/";
    JsonNode jsonSchema; // ⬅️ Usando JsonNode agora
    private final ObjectMapper mapper = new ObjectMapper();

    public void setFieldsDelivery(String field, String value) {
        switch (field) {
            case "acidenteId" -> acidenteModel.setAcidenteId(value);
            case "modeloVeiculo" -> acidenteModel.setModeloVeiculo(value);
            case "placaVeiculo" -> acidenteModel.setPlacaVeiculo(value);
            case "dataOcorrencia" -> acidenteModel.setDataOcorrencia(value);
            case "endereco" -> acidenteModel.setEndereco(value);
            default -> throw new IllegalStateException("Unexpected field: " + field);
        }
    }

    public void createDelivery(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(acidenteModel);
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

    public void retrieveIdDelivery() {
        idDelivery = String.valueOf(gson.fromJson(response.jsonPath().prettify(), AcidenteModel.class).getAcidenteId());
    }

    public void deleteDelivery(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, idDelivery);
        response = given()
                .accept(ContentType.JSON)
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }


    private JsonNode loadJsonFromFile(String filePath) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            return mapper.readTree(inputStream);
        }
    }

    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Cadastro bem-sucedido de acidente" ->
                    jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-acidente.json");
            default -> throw new IllegalStateException("Unexpected contract: " + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema);

        JsonNode jsonResponseNode = mapper.readTree(response.getBody().asString());

        return schema.validate(jsonResponseNode);
    }
}

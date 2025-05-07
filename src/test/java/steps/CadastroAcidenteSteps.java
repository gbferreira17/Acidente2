package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErroMsgModel;
import org.junit.Assert;
import services.CadastroAcidenteService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroAcidenteSteps {

    CadastroAcidenteService cadastroAcidenteService = new CadastroAcidenteService();

    @Dado("que eu tenha os seguintes dados da ocorrência:")
    public void queEuTenhaOsSeguintesDadosDaOcorrência(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroAcidenteService.setFieldsDelivery(columns.get("campo"), columns.get("valor"));

        }
    }
    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de ocorrência")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeOcorrência (String endpoint){
        cadastroAcidenteService.createDelivery(endpoint);
    }

    @Então("O status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer ( int statuscode){
        Assert.assertEquals(statuscode, cadastroAcidenteService.response.statusCode());

    }

    @E("O corpo de resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
        ErroMsgModel erroMsgModel = cadastroAcidenteService.gson.fromJson(
                cadastroAcidenteService.response.jsonPath().prettify(), ErroMsgModel.class);
        Assert.assertEquals(message, erroMsgModel.getModeloVeiculo());
    }

    @Dado("que eu recupere o ID do acidente criada no contexto")
    public void queEuRecupereOIDDoAcidenteCriadaNoContexto() {

        cadastroAcidenteService.retrieveIdDelivery();

    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de entrega")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDeEntrega(String endpoint) {
        cadastroAcidenteService.deleteDelivery(endpoint);
    }

    @Então("o status code da resposta dev ser {int}")
    public void oStatusCodeDaRespostaDevSer(int statuscode) {
        Assert.assertEquals(statuscode, cadastroAcidenteService.response.statusCode());
    }

    @E("o arquivo de contrato esperado é o {string}")
    public void oArquivoDeContratoEsperadoÉO(String contract) throws IOException {
        cadastroAcidenteService.setContract(contract);

    }

    @Então("resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void respostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validationResponse = cadastroAcidenteService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validationResponse, validationResponse.isEmpty());
    }
}

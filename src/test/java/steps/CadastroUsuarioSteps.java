package steps;

import br.com.fiap.Acidente.service.UsuarioService;
import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErroMsgModel;
import org.json.JSONException;
import org.junit.Assert;
import services.CadastroUsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroUsuarioSteps {

    CadastroUsuarioService cadastroUsuarioService  = new CadastroUsuarioService();

    @Dado("que eu tenha os seguintes dados do usuário:")
    public void queEuTenhaOsSeguintesDadosDoUsuário(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows){
            cadastroUsuarioService.setFieldsDelivery(columns.get("campo"), columns.get("valor"));


        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de usuário")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeUsuário(String endpoint) {
        cadastroUsuarioService.createDelivery(endpoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statuscode) {
        Assert.assertEquals(statuscode, cadastroUsuarioService.response.statusCode());
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
        ErroMsgModel erroMsgModel = cadastroUsuarioService.gson.fromJson(
                cadastroUsuarioService.response.jsonPath().prettify(), ErroMsgModel.class);
        Assert.assertEquals(message, erroMsgModel.getEmail());

    }

}

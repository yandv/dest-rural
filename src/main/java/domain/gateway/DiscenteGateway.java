package domain.gateway;

import java.util.HashMap;
import java.util.Map;

import domain.model.Discente;

public class DiscenteGateway {

    private static DiscenteGateway instance;

    public static DiscenteGateway getInstance() {
        if (instance == null) {
            instance = new DiscenteGateway();
        }
        return instance;
    }

    private Map<String, Discente> discenteMap;

    protected DiscenteGateway() {
        discenteMap = new HashMap<>();
        criarDiscente("allan.marcelino@email.com", "Allan Marcelino");
        criarDiscente("gabriel.marinho@email.com", "Gabriel Marinho");
    }

    private void criarDiscente(String email, String nome) {
        Discente discente = new Discente(email, nome, "1234");
        discenteMap.put(String.valueOf(discenteMap.size() + 1), discente);
    }

    public Discente pegarDiscentePeloNumeroPedidoEstagio(int numeroPedidoEstagio) {
        return this.discenteMap.values().stream()
                .filter(discente -> discente.getNumeroPedidoEstagio() == numeroPedidoEstagio)
                .findFirst().orElse(null);
    }

    public Discente pegarDiscentePeloEmail(String email) {
        return this.discenteMap.values().stream().filter(discente -> discente.getEmail().equals(email)).findFirst()
                .orElse(null);
    }

    public void salvarDiscente(Discente discente) {
        this.discenteMap.put(discente.getId(), discente);
    }

}

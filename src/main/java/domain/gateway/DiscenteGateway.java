package domain.gateway;

import java.util.HashMap;
import java.util.Map;

import domain.model.DiscenteDto;

public class DiscenteGateway {

    private static DiscenteGateway instance;

    public static DiscenteGateway getInstance() {
        if (instance == null) {
            instance = new DiscenteGateway();
        }
        return instance;
    }

    private Map<String, DiscenteDto> discenteMap;

    protected DiscenteGateway() {
        discenteMap = new HashMap<>();
        criarDiscente("allan.marcelino@email.com", "Allan Marcelino");
        criarDiscente("gabriel.marinho@email.com", "Gabriel Marinho");
    }

    private void criarDiscente(String email, String nome) {
        DiscenteDto discente = new DiscenteDto(email, nome, "1234");
        discenteMap.put(String.valueOf(discenteMap.size() + 1), discente);
    }

    public DiscenteDto pegarDiscentePeloEmail(String email) {
        return this.discenteMap.values().stream().filter(discente -> discente.getEmail().equals(email)).findFirst()
                .orElse(null);
    }

    public DiscenteDto pegarDiscentePeloId(String discenteId) {
        return this.discenteMap.values().stream().filter(discente -> discente.getId().equals(discenteId)).findFirst()
                .orElse(null);
    }

    public void salvarDiscente(DiscenteDto discente) {
        this.discenteMap.put(discente.getId(), discente);
    }

}

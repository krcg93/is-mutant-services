package mutant.services.com.infrastructure.shared.dto;

import java.util.List;

public class MutantDto {
    private List<String> dna;

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }
}

package mutant.services.com.domain.model;

import mutant.services.com.domain.exception.Validate;
import reactor.core.publisher.Mono;

import java.util.List;

public class Mutant {
    private List<String> dna;

    public Mutant(){}

    private Mutant(List<String> dna) {
        this.dna = dna;
    }

    public static Mono<Mutant> create(List<String> dna){
        Mutant mutant = new Mutant(dna);
        return mutant.validate().then(Mono.just(mutant));
    }

    public Mono<Void> validate(){
        return Validate
                .nullOrEmptyValidate(dna,"dna");
    }
}

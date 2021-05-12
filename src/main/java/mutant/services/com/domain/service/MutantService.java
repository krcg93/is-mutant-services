package mutant.services.com.domain.service;

import mutant.services.com.domain.exception.Validate;
import mutant.services.com.domain.model.IsMutant;
import mutant.services.com.domain.model.Mutant;
import mutant.services.com.domain.model.MutantsCount;
import mutant.services.com.domain.service.dependency.MutantI;
import reactor.core.publisher.Mono;

public class MutantService {

    private MutantI mutantI;

    public MutantService(MutantI mutantI) {
        this.mutantI = mutantI;
    }

    public Mono<IsMutant> mutant(Mutant mutant){
        return  Validate.nullEntityValidate(mutant,"mutant")
                .then(Mono.defer(() -> mutantI.mutant(mutant)))
                .onErrorResume(Mono::error);
    }

    public Mono<MutantsCount> mutantsCounts(){
        return mutantI.mutantsCounts()
                .onErrorResume(Mono::error);
    }

}

package mutant.services.com.domain.service.dependency;

import mutant.services.com.domain.model.IsMutant;
import mutant.services.com.domain.model.MutantsCount;
import reactor.core.publisher.Mono;
import mutant.services.com.domain.model.Mutant;


public interface MutantI {

    Mono<IsMutant> mutant(Mutant mutant);

    Mono<MutantsCount> mutantsCounts();

}

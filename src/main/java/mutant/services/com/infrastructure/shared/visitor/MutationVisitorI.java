package mutant.services.com.infrastructure.shared.visitor;

import mutant.services.com.infrastructure.shared.dto.IsMutantDto;
import mutant.services.com.infrastructure.shared.dto.MutantDto;
import reactor.core.publisher.Mono;


public interface MutationVisitorI {

    Mono<IsMutantDto> isMutant (MutantDto mutantDto);

}

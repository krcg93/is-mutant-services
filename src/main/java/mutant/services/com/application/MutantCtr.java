package mutant.services.com.application;

import io.micronaut.http.annotation.*;
import mutant.services.com.domain.model.Mutant;
import mutant.services.com.domain.service.MutantService;
import io.micronaut.http.MediaType;
import mutant.services.com.infrastructure.shared.dto.IsMutantDto;
import mutant.services.com.infrastructure.shared.dto.MutantsCountDto;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;
import mutant.services.com.infrastructure.shared.dto.MutantDto;

import javax.inject.Inject;

@Controller("/mutant")
public class MutantCtr {

    @Inject
    private ModelMapper mapper;

    @Inject
    private MutantService mutantService;


    @Post(produces = MediaType.APPLICATION_JSON)
    public Mono<IsMutantDto> isMutant(@Body MutantDto mutantDto) {
        return Mono.just(mapper.map(mutantDto, Mutant.class))
                .flatMap(mutantService::mutant)
                .map(success -> mapper.map(success, IsMutantDto.class));
    }

    @Get(value = "stats", produces = MediaType.APPLICATION_JSON)
    public Mono<MutantsCountDto> stats() {
        return mutantService.mutantsCounts()
                .map(mutantsCount -> mapper.map(mutantsCount, MutantsCountDto.class));
    }
}

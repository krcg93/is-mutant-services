package mutant.services.com.infrastructure.service;

import mutant.services.com.domain.model.IsMutant;
import mutant.services.com.domain.model.Mutant;
import mutant.services.com.domain.model.MutantsCount;
import mutant.services.com.domain.service.dependency.MutantI;
import mutant.services.com.infrastructure.client.database.Firebase;
import mutant.services.com.infrastructure.shared.converter.ConverterFirebase;
import mutant.services.com.infrastructure.shared.dto.IsMutantDto;
import mutant.services.com.infrastructure.shared.dto.MutantRegisterDto;
import mutant.services.com.infrastructure.shared.dto.MutantsCountDto;
import mutant.services.com.infrastructure.shared.visitor.MutationVisitorI;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import reactor.core.publisher.Mono;
import mutant.services.com.infrastructure.shared.dto.MutantDto;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;
import java.util.List;


@Singleton
public class MutantService implements MutantI {

    @Inject
    Firebase firebase;

    @Inject
    private ModelMapper mapper;

    private MutationVisitorI mutationVisitorI;

    public MutantService(MutationVisitorI mutationVisitorI) {
        this.mutationVisitorI = mutationVisitorI;
    }

    @Override
    public Mono<IsMutant> mutant(Mutant mutant) {
        return Mono.just(mapper.map(mutant, MutantDto.class))
                .flatMap(mutantDto ->  {
                    Mono<IsMutantDto> isMutant = mutationVisitorI.isMutant(mutantDto);
                    MutantRegisterDto mutantRegisterDto = new MutantRegisterDto();
                    mutantRegisterDto.setDna(mutantDto.getDna().toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                    mutantRegisterDto.setRegisterDate(new Date().toString());
                    mutantRegisterDto.setMutant(isMutant.block().isMutant());
                    firebase.putMutant(mutantRegisterDto);
                    return isMutant;
                })
                .map(mutantDto ->  mapper.map(mutantDto, IsMutant.class));
    }

    @Override
    public Mono<MutantsCount> mutantsCounts() {
        return firebase.getMutant()
                .map(mutants ->mapper.map(ConverterFirebase.mapperMutants(mutants), MutantsCount.class));
    }

}

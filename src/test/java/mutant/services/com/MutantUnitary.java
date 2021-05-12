package mutant.services.com;

import io.micronaut.test.annotation.MicronautTest;
import mutant.services.com.domain.model.IsMutant;
import mutant.services.com.domain.model.Mutant;
import mutant.services.com.domain.model.MutantsCount;
import mutant.services.com.domain.service.MutantService;
import mutant.services.com.domain.service.dependency.MutantI;
import mutant.services.com.infrastructure.shared.dto.IsMutantDto;
import mutant.services.com.infrastructure.shared.dto.MutantDto;
import mutant.services.com.infrastructure.shared.dto.MutantsCountDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@MicronautTest
public class MutantUnitary {

    @Inject
    ModelMapper modelMapper;

    MutantI mutantI;
    MutantService mutantService;

    @BeforeEach
    public void setUp() {
        mutantI = mock(MutantI.class);
        mutantService = new MutantService(mutantI);
    }

    @Test
    public void isMutantTrueOk() throws Exception {
        IsMutantDto isMutantDto = new IsMutantDto();
        isMutantDto.setMutant(true);
        when(mutantI.mutant(any(Mutant.class))).thenReturn(isMutant(isMutantDto));

        MutantDto mutantDto = new MutantDto();
        mutantDto.setDna(dnaArray(true));
        Mutant mutant = modelMapper.map(mutantDto, Mutant.class);

        StepVerifier.create(mutantService.mutant(mutant))
                .consumeNextWith(isMutant -> {
                    Assertions.assertNotNull(isMutant);
                    IsMutantDto map = modelMapper.map(isMutant, IsMutantDto.class);
                    Assertions.assertTrue(map.isMutant());
                }).expectComplete()
                .verify();
    }

    @Test
    public void isMutantFalseOk() throws Exception {
        IsMutantDto isMutantDto = new IsMutantDto();
        isMutantDto.setMutant(false);
        when(mutantI.mutant(any(Mutant.class))).thenReturn(isMutant(isMutantDto));

        MutantDto mutantDto = new MutantDto();
        mutantDto.setDna(dnaArray(false));
        Mutant mutant = modelMapper.map(mutantDto, Mutant.class);

        StepVerifier.create(mutantService.mutant(mutant))
                .consumeNextWith(isMutant -> {
                    Assertions.assertNotNull(isMutant);
                    IsMutantDto map = modelMapper.map(isMutant, IsMutantDto.class);
                    Assertions.assertFalse(map.isMutant());
                }).expectComplete()
                .verify();
    }

    @Test
    public void statsOk() throws Exception {
        when(mutantI.mutantsCounts()).thenReturn(mutantCounts(mutantsCountDto()));

        StepVerifier.create(mutantService.mutantsCounts())
                .consumeNextWith(mutantsCount -> {
                    Assertions.assertNotNull(mutantsCount);
                    MutantsCountDto map = modelMapper.map(mutantsCount, MutantsCountDto.class);
                    Assertions.assertEquals(map.getRatio(), 0.4);
                }).expectComplete()
                .verify();
    }

    private Mono<IsMutant> isMutant(IsMutantDto isMutantDto) {
        return Mono.just(modelMapper.map(isMutantDto, IsMutant.class));
    }

    private Mono<MutantsCount> mutantCounts(MutantsCountDto mutantsCountDto) {
        return Mono.just(modelMapper.map(mutantsCountDto, MutantsCount.class));
    }

    private MutantsCountDto mutantsCountDto(){
        MutantsCountDto mutantsCountDto = new MutantsCountDto();
        mutantsCountDto.setCountMutantDna(40);
        mutantsCountDto.setCountHumanDna(100);
        mutantsCountDto.setRatio(0.4);
        return mutantsCountDto;
    }

    private List<String> dnaArray(boolean trueOrFalse){
        List<String> adn = new ArrayList<>();
        if (trueOrFalse){
            adn.add("ATGCEA");
            adn.add("CAGTGC");
            adn.add("TTATGT");
            adn.add("AGAAGG");
            adn.add("ECCCTA");
            adn.add("TCACTG");
        }else {
            adn.add("ETGCEA");
            adn.add("CAGTGC");
            adn.add("TTATGT");
            adn.add("AGAAGG");
            adn.add("ECCCTA");
            adn.add("TCACTG");
        }
        return adn;
    }
}

package mutant.services.com;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import mutant.services.com.infrastructure.shared.dto.IsMutantDto;
import mutant.services.com.infrastructure.shared.dto.MutantDto;
import mutant.services.com.infrastructure.shared.dto.MutantsCountDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;


@MicronautTest
public class MutantAcceptation {

    @Inject
    @Client("/mutant")
    HttpClient client;

    @Test
    public void isMutantTrueOK(){
        MutantDto mutantDto = new MutantDto();
        mutantDto.setDna(dnaArray(true));
        StepVerifier.create(client.retrieve(HttpRequest.POST("", mutantDto), IsMutantDto.class))
                .consumeNextWith(isMutantDto -> {
                    Assertions.assertNotNull(isMutantDto);
                    Assertions.assertTrue(isMutantDto.isMutant());
                }).expectComplete()
                .verify();
    }

    @Test
    public void isMutantFalseOK(){
        MutantDto mutantDto = new MutantDto();
        mutantDto.setDna(dnaArray(false));
        StepVerifier.create(client.retrieve(HttpRequest.POST("", mutantDto), IsMutantDto.class))
                .consumeNextWith(isMutantDto -> {
                    Assertions.assertNotNull(isMutantDto);
                    Assertions.assertFalse(isMutantDto.isMutant());
                }).expectComplete()
                .verify();
    }

    @Test
    public void statsOK(){
        StepVerifier.create(client.retrieve(HttpRequest.GET("/stats"), MutantsCountDto.class))
                .consumeNextWith(mutantsCountDto-> {
                    Assertions.assertNotNull(mutantsCountDto);
                }).expectComplete()
                .verify();
    }

    @Test
    public void statsError(){
        StepVerifier.create(client.retrieve(HttpRequest.GET("/stat")))
                .expectError()
                .verify();
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

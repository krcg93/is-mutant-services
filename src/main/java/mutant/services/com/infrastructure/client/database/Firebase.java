package mutant.services.com.infrastructure.client.database;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import mutant.services.com.infrastructure.shared.dto.MutantDto;
import mutant.services.com.infrastructure.shared.dto.MutantRegisterDto;
import reactor.adapter.rxjava.RxJava2Adapter;
import reactor.core.publisher.Mono;

import javax.inject.Singleton;

@Singleton
public class Firebase {

    protected RxHttpClient client = null;

    public Firebase(@Client("${firebase.path}") RxHttpClient client) {
        this.client = client;
    }

    @Put
    public Mono<MutantRegisterDto> putMutant(MutantRegisterDto mutantRegisterDto) {
        HttpRequest httpRequest = HttpRequest.PUT("/mutants/"+mutantRegisterDto.getDna().replaceAll(", ", "")+".json", mutantRegisterDto);
        Flowable<MutantRegisterDto> response = client.retrieve(httpRequest, MutantRegisterDto.class);
        Maybe<MutantRegisterDto> mutantRegisterDtoMaybe = response.firstElement();
        return RxJava2Adapter.maybeToMono(mutantRegisterDtoMaybe);
    }

    @Get
    public Mono<Object> getMutant() {
        HttpRequest httpRequest = HttpRequest.GET("/mutants.json");
        Flowable<Object> response = client.retrieve(httpRequest, Object.class);
        Maybe<Object> objectMaybe = response.firstElement();
        return RxJava2Adapter.maybeToMono(objectMaybe);
    }

}

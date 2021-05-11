package mutant.services.com.infrastructure.shared;

import mutant.services.com.infrastructure.service.MutantService;
import io.micronaut.context.annotation.Factory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class AppContext {

    @Inject
    MutantService mutantServiceRepository;

    @Singleton
    mutant.services.com.domain.service.MutantService mutantService(){
        return new mutant.services.com.domain.service.MutantService(this.mutantServiceRepository);
    }
}
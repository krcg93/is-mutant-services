package mutant.services.com.domain.exception;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

public class Validate {

    //SOLO VALIDACIONES GENERICAS//
    //VALIDACIONES ESPECIFICAS EN EL MODELO O SERVICIO//

    public static Mono<Void> nullOrEmptyValidate(String value, String field) {
        return Mono.justOrEmpty(value).filter(it-> (value!=null && !value.isEmpty()))
                .switchIfEmpty(Mono.error(ExceptionFactory.NULL_OR_EMPTY.get(field)))
                .onErrorResume(error -> Mono.error(error))
                .then(Mono.empty());
    }

    public static Mono<Void> nullOrEmptyValidate(List value, String field) {
        return Mono.justOrEmpty(value).filter(it-> (value!=null && !value.isEmpty()))
                .switchIfEmpty(Mono.error(ExceptionFactory.NULL_OR_EMPTY.get(field)))
                .onErrorResume(error -> Mono.error(error))
                .then(Mono.empty());
    }

    public static Mono<Void> nullEntityValidate(Object value, String field) {
        return Mono.justOrEmpty(value).filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(ExceptionFactory.NULL_ENTITY.get(field)))
                .onErrorResume(error -> Mono.error(error))
                .then(Mono.empty());
    }
}

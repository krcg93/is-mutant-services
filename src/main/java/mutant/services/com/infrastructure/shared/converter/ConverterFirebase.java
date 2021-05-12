package mutant.services.com.infrastructure.shared.converter;

import com.google.gson.Gson;
import mutant.services.com.infrastructure.shared.dto.MutantRegisterDto;
import mutant.services.com.infrastructure.shared.dto.MutantsCountDto;

import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConverterFirebase {
    public static MutantsCountDto mapperMutants(Object mutants)
    {
        Gson gson = new Gson();
        MutantsCountDto mutantsCountDto = new MutantsCountDto();
        LinkedHashMap linkedHashMap = (LinkedHashMap) mutants;
        AtomicInteger mutant = new AtomicInteger();
        AtomicInteger human = new AtomicInteger();
        linkedHashMap.forEach((key, value) -> {
            String json = gson.toJson(value,LinkedHashMap.class);
            MutantRegisterDto data = new Gson().fromJson(json, MutantRegisterDto.class);
            if (data.isMutant()) {
                mutant.getAndIncrement();
            } else {
                human.getAndIncrement();
            }
        });
        mutantsCountDto.setCountMutantDna(mutant.get());
        mutantsCountDto.setCountHumanDna(human.get());
        mutantsCountDto.setRatio((double) (mutant.get() / human.get()));
        return mutantsCountDto;
    }
}

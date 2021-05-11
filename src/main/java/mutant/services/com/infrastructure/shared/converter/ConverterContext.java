package mutant.services.com.infrastructure.shared.converter;

import io.micronaut.context.annotation.Factory;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.jackson.JsonNodeValueReader;

import javax.inject.Singleton;

@Factory
public class ConverterContext {

    @Singleton
    ModelMapper getMapper()
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setCollectionsMergeEnabled(true)
                .setImplicitMappingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .addValueReader(new JsonNodeValueReader())
                .setAmbiguityIgnored(true)
                .setSourceNameTokenizer(NameTokenizers.UNDERSCORE);

        return modelMapper;
    }
}
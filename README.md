## Micronaut 2.3.2 Documentation

- [User Guide](https://docs.micronaut.io/2.3.2/guide/index.html)
- [API Reference](https://docs.micronaut.io/2.3.2/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/2.3.2/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Ejecutar proyecto

- En gradle, is-mutant-services, expandir el menu de Tasks despues expandir application y ejecutar run.
despues de ejecutar run, editar la configuracion y agregar en environment las variables de entorno, las variables de entorno estan en 
application.yml estan al final del archivo comentadas

- Con docker, ejecutar los siguientes comandos: ./gradlew build, docker build . -t mutants, docker run --rm -p 8080:8080 mutants

## Diseño
- Arquitectura DDD
- Implementacion de SOLID
- Implementacion de Singleton, Factory, Visitor

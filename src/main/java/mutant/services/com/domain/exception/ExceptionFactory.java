package mutant.services.com.domain.exception;

public enum ExceptionFactory {

    NULL_OR_EMPTY("El campo <f0> no puede estar vacio",400),
    NULL_ENTITY("La entidad <f0> no puede ser nula",400);


    private String message;
    private int statusCode;
    ExceptionFactory(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
    public BusinessException get(Throwable throwable, String... fields){
        return new BusinessException(replaceMessage(fields),throwable,this.statusCode);
    }
    public BusinessException get(String... fields){
        return new BusinessException(replaceMessage(fields), this.statusCode);
    }
    private String replaceMessage(String... fields){
        String message = this.message;
        for (int i=0;i<= fields.length-1;i++){
            message = message.replaceAll("<f"+i+">",fields[i]);
        }
        return message;
    }

}
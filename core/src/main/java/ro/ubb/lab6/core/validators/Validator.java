package ro.ubb.lab6.core.validators;

public interface Validator<T> {

     void validate(T entity);
     void validateList(Iterable<T> list);

}

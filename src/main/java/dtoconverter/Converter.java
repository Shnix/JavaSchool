package dtoconverter;

public interface Converter<T, K> {

    T convertFromDto(K k);

    K convertIntoDto(T t);
}

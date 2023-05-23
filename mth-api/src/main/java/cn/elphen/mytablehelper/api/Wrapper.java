package cn.elphen.mytablehelper.api;

/**
 * An interface use for any Class, the defined methods can be used to get the real type instance when the implemented
 * type used as a generics type(type parameter).
 *
 * @author Elphen
 * @since 0.0.1
 */
public interface Wrapper {

    /**
     * Try to cast this object to specified class.
     *
     * @param targetClass specified class
     * @param <T>         any type
     * @return the instance of specified class
     */
    <T> T unwrapFor(Class<T> targetClass);

    /**
     * Judge the implementation is assigned from or instance of specified Class.
     *
     * @param targetClass specified class
     * @return Return true if the implementation is assigned from or is and instance of specified Class.
     */
    boolean isWrapFor(Class<?> targetClass);

}

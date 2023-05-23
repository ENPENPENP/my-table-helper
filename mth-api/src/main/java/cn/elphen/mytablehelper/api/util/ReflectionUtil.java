package cn.elphen.mytablehelper.api.util;

import cn.elphen.mytablehelper.api.exception.ObjectInstantException;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Objects;

/**
 * Reflection utils
 *
 * @author Elphen Liu
 * @since 0.0.1
 */
public class ReflectionUtil {

    /**
     * Get the parameterized type of specified class.
     *
     * @param targetClass target class
     * @param index       the index of parameterized type.
     * @return the parameterized type object
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getSuperclass(Class<?> targetClass, int index) {
        if (targetClass == null || index < 0) {
            return null;
        }
        Type curClazzType = targetClass.getGenericSuperclass();
        if (curClazzType instanceof ParameterizedType) {
            ParameterizedType p = (ParameterizedType) curClazzType;
            Type[] actualTypeArguments = p.getActualTypeArguments();
            for (int i = 0; i < actualTypeArguments.length; i++) {
                if (i == index) {
                    return (Class<T>) actualTypeArguments[i];
                }
            }
        }
        return null;
    }

    /**
     * Invoke the method of object.
     *
     * @param object     object to be invoked
     * @param methodName method name
     * @param args       arguments of method
     * @return the object return by the method, it will return null if the method invoked return null or void.
     */
    public static Object invokeMethod(Object object, String methodName, Object... args) {
        if (object == null || methodName == null) {
            return null;
        }
        try {
            Method method = object.getClass().getMethod(methodName);
            return method.invoke(object, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get specified interface which maybe implemented or extended by target class.
     *
     * @param clazz          target class
     * @param interfaceClass specified interface
     * @param <T>            the generic type of specified interface.
     * @return if target class has implemented or extended the provided interface, return the Class object of interface,
     * otherwise return null. If any parameter is null or second parameter is not an Interface, it will return null.
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getInterface(Class<?> clazz, Class<T> interfaceClass) {
        if (interfaceClass == null || clazz == null || !interfaceClass.isInterface()) {
            return null;
        }
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            if (interfaceClass.isAssignableFrom(anInterface) || interfaceClass.equals(anInterface)) {
                return (Class<T>) anInterface;
            }
        }
        return null;
    }

    /**
     * Get an instance of target Class safety. The method assuming the provided Class has a non-args constructor and use
     * the non-args constructor to get instance.
     *
     * @param targetType the Class to get instance
     * @param <T>        the generic type of provided Class
     * @return return the result of the non-args constructor of provided.
     */
    @SuppressWarnings("unchecked")
    public static <T> T safetyGetInstance(Class<T> targetType) {
        try {
            Constructor<?> noArgsConstructor = targetType.getConstructor();

            return (T) noArgsConstructor.newInstance();

        } catch (Exception e) {
            throw new ObjectInstantException(e);
        }
    }

    /**
     * Determines whether the provided object is an implementation of a specified Class.
     *
     * @param object provided object to be determined
     * @param tarCls specified class
     * @return return true if the provided implemented or extended or is an instance of the specified Class.
     */
    public static boolean isExtOrImplOf(Object object, Class<?> tarCls) {
        if (Objects.isNull(object) || Objects.isNull(tarCls)) {
            return false;
        }

        if (isClass(object)) {
            Class<?> cls = (Class<?>) object;

            return cls.isAssignableFrom(tarCls);
        } else {
            return object.getClass().isAssignableFrom(tarCls) || tarCls.isInstance(object);
        }
    }

    /**
     * Determine whether the provided object is a Class object.
     *
     * @param object provided object
     * @return true if the provided object is not null and is a Class object.
     */
    public static boolean isClass(Object object) {
        return !Objects.isNull(object) && object instanceof Class;
    }

    /**
     * Determine whether the provided object has declared the specified annotation.
     *
     * @param srcObj  the provided object
     * @param annoCls the specified annotation
     * @return true if the provided object has declared the specified annotation.
     */
    public static boolean hasAnnotation(Object srcObj, Class<? extends Annotation> annoCls) {
        if (Objects.isNull(srcObj) || Objects.isNull(annoCls)) {
            return false;
        }

        if (isExtOrImplOf(srcObj, AnnotatedElement.class)) {
            AnnotatedElement annotatedElement = (AnnotatedElement) srcObj;
            return annotatedElement.isAnnotationPresent(annoCls);
        } else {
            return false;
        }
    }
}

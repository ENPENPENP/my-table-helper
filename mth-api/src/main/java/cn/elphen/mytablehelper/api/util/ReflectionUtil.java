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
     * 获取目标类指定位置的泛型类型
     *
     * @param curClazz 目标类
     * @param index    泛型索引，从0开始
     * @return 泛型的类型
     */
    public static <T> Class<T> getSuperclass(Class<?> curClazz, int index) {
        if (curClazz == null || index < 0) {
            return null;
        }
        Type curClazzType = curClazz.getGenericSuperclass();
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

    public static <T> T safetyGetInstance(Class<T> targetType) {
        try {
            Constructor<?> noArgsConstructor = targetType.getConstructor();

            return (T) noArgsConstructor.newInstance();

        } catch (Exception e) {
            throw new ObjectInstantException(e);
        }
    }

    public static boolean isExtOrImplOf(Object object, Class<?> tarCls) {
        if (Objects.isNull(object) || Objects.isNull(tarCls)) {
            return false;
        }

        if (isClass(object)){
            Class<?> cls = (Class<?>) object;

            return cls.isAssignableFrom(tarCls);
        } else {
            return object.getClass().isAssignableFrom(tarCls) || tarCls.isInstance(object);
        }
    }

    public static boolean isClass(Object object) {
        return !Objects.isNull(object) && object instanceof Class;
    }

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

package org.fpm.di.example;

import org.fpm.di.Binder;

import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class DummyBinder implements Binder {
    private final ArrayList<Class<?>> classArrayList = new ArrayList<>();
    private final HashMap<Class<?>,Class<?>> injectionDependenciesMap = new HashMap<>();
    private final HashMap<Class<?>, Object> injectionSingletonMap = new HashMap<>();

    @Override
    public <T> void bind(Class<T> clazz) {
        if(CheckAndSetIfSingleton(clazz)){
            try {
                Constructor<T> constructor = clazz.getConstructor();
                bind (clazz, constructor.newInstance());
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        classArrayList.add(clazz);
    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        injectionDependenciesMap.put(clazz, implementation);
    }

    private <T> boolean CheckAndSetIfSingleton(Class<T> clazz){
        return clazz.getAnnotation(Singleton.class) != null;
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        injectionSingletonMap.put(clazz,instance);
    }

    public<T> Class<T> getClassFromDB(Class<T> initial){
        if(classArrayList.contains(initial))
            return initial;
        return null;
    }
    public <T> Class<? extends T> getDependenciesClassFromDB(Class<T> initial){
        if (injectionDependenciesMap.containsKey(initial))
            return (Class<? extends T>) injectionDependenciesMap.get(initial);
        return null;
    }

    public <T> T getSingletonClassFromDB(Class<T> initial){
        if (injectionSingletonMap.containsKey(initial))
            return (T) injectionSingletonMap.get(initial);
        return null;
    }
}

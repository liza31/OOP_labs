package org.fpm.di.example;

import org.fpm.di.Binder;
import org.fpm.di.Container;

import javax.inject.Inject;
import javax.swing.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class DummyContainer implements Container {
    DummyBinder binder;
    public DummyContainer(DummyBinder binder) {
        this.binder = binder;
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        if (binder.getClassFromDB(clazz)!=null)
            return MakeTFromConstructor(clazz);
        Class<? extends T> extendesClass;
        if((extendesClass = binder.getDependenciesClassFromDB(clazz))!=null)
            return MakeTFromConstructor(clazz, extendesClass);

        T singletonObject;
        if((singletonObject = binder.getSingletonClassFromDB(clazz))!=null)
            return MakeTFromConstructor(clazz, singletonObject);

        return null;
    }

    private <T> T MakeTFromConstructor(Class<T> clazz, T singletonObject) {
        return singletonObject;
    }

    private <T> T MakeTFromConstructor(Class<T> clazz, Class<? extends T> extendesClass) {
        T returnedObject;
        if ((returnedObject = getComponent(extendesClass))!=null) {
            return returnedObject;
        }
        return MakeTFromConstructor(extendesClass);
    }

    private <T> T MakeTFromConstructor(Class<T> clazz) {
        for (Constructor<?> a : clazz.getConstructors()) {
            if (a.getAnnotation(Inject.class)!=null){
                try {
                    return (T) a.newInstance(getComponent(a.getParameterTypes()[0]));
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        try {
            Constructor<T> constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

package io.github.support.util;

public abstract class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader loader = null;
        try {
            loader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable e) {
            //nothing
        }
        if (loader == null) {
            loader = ClassUtils.class.getClassLoader();
            if (loader == null) {
                try {
                    loader = ClassLoader.getSystemClassLoader();
                } catch (Throwable e) {
                    //nothing
                }
            }
        }
        return loader;
    }

}

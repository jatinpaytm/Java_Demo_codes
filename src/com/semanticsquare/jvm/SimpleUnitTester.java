package com.semanticsquare.jvm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SimpleUnitTester {

    //@Test
    public int execute(Class clazz) throws Exception {
        int failedCount = 0;
        Object object = null;
        try {
            object = clazz.newInstance(); // MUST HAVE DEFAULT CONSTRUCTOR
        } catch(InstantiationException e) {
            System.out.println("Can't instantiate ...");
//            failedCount++;
            // your code
        }
        Constructor<Reflection> reflectionConstructor = clazz.getDeclaredConstructor();
        @SuppressWarnings("unchecked")
        Reflection reflection = reflectionConstructor.newInstance();
        Method m = clazz.getDeclaredMethod("testA");
        Object result = m.invoke(reflection);
        if(((Boolean)result).booleanValue()==false)
            failedCount++;

        m = clazz.getDeclaredMethod("testB");
        result = m.invoke(reflection);
        if(((Boolean)result).booleanValue()==false)
            failedCount++;

        m = clazz.getDeclaredMethod("testC");
        result = m.invoke(reflection);
        if(((Boolean)result).booleanValue()==false)
            failedCount++;

        m = clazz.getDeclaredMethod("testD");
        result = m.invoke(reflection);
        if(((Boolean)result).booleanValue()==false)
            failedCount++;

        m = clazz.getDeclaredMethod("testE");
        result = m.invoke(reflection);
        if(((Boolean)result).booleanValue()==false)
            failedCount++;

        return failedCount;
    }

}

package com.github.sunghyuk.overwatch.page;

import com.github.sunghyuk.overwatch.processor.ElementsProcessor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by sunghyuk on 2016. 7. 20..
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Selector {
    String value();

    Class<? extends ElementsProcessor> handler();

    String key() default "";
}

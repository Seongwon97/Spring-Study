package com.example.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD}) // method에 사용할 것이며
@Retention(RetentionPolicy.RUNTIME) // Runtime에 사용할 것이다.
public @interface Timer {

}

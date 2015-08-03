package com.ttc.ch2.audit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Audit  {
    String applicationCode() default "";
    String action() default "";
    boolean twoPhase() default false;
    String resource() default "";
    String object() default "";
}

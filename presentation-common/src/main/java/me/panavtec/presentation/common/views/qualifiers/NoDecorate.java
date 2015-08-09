package me.panavtec.presentation.common.views.qualifiers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this class in case you want to no decorate any of the methods of your View Interfaces.
 * For example the init Ui methods that are you sure are in the same thread.
 */
@Target(ElementType.METHOD) @Retention(RetentionPolicy.SOURCE)
public @interface NoDecorate {
}

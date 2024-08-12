package pl.auroramc.commons.sql;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.intellij.lang.annotations.Language;

@Target(FIELD)
@Retention(CLASS)
@Language("MariaDB")
public @interface SqlQuery {}

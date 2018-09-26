package ru.exercise.webserver.common.converter;

public interface Converter<TSource, TTarget> {

    TTarget convert(final TSource source);
}

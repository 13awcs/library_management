package com.library.util;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamUtils {

    public static <T, R> List<R> mapApply(Collection<T> collection, Function<T, R> function) {
        return collection.stream()
                .map(function)
                .collect(Collectors.toList());
    }

    public static <T, R> Set<R> mapApplyToSet(Collection<T> collection, Function<T, R> function) {
        return collection.stream()
                .map(function)
                .collect(Collectors.toSet());
    }

    public static <T> List<T> filterApply(Collection<T> collection, Predicate<T> filter) {
        return collection
                .stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    public static <T, R> List<R> mapThenFilterApply(Collection<T> collection,
                                                    Function<T, R> function,
                                                    Predicate<R> predicate) {
        return collection.stream()
                .map(function)
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static <T, R> List<R> filterThenMapApply(Collection<T> collection,
                                                    Predicate<T> predicate,
                                                    Function<T, R> function) {
        return collection.stream()
                .filter(predicate)
                .map(function)
                .collect(Collectors.toList());
    }

    public static <T, R> Set<R> mapThenFilterApplyToSet(Collection<T> collection,
                                                        Function<T, R> function,
                                                        Predicate<R> predicate) {
        return collection.stream()
                .map(function)
                .filter(predicate)
                .collect(Collectors.toSet());
    }

    public static <V, K> Map<K, V> toMap(Collection<V> collection,
                                         Function<V, K> function) {
        return collection.stream()
                .collect(Collectors.toMap(function, Function.identity()));
    }

    public static <V, K, P> Map<K, P> toMap(Collection<V> collection,
                                            Function<V, K> functionKey,
                                            Function<V, P> functionVal) {
        return collection.stream()
                .collect(Collectors.toMap(functionKey, functionVal));
    }

    public static <V, K, P> Map<K, P> filterApplyThenToMap(Collection<V> collection,
                                                           Predicate<V> predicate,
                                                           Function<V, K> functionKey,
                                                           Function<V, P> functionVal) {
        return collection.stream()
                .filter(predicate)
                .collect(Collectors.toMap(functionKey, functionVal));
    }

    public static <T, K> Map<K, List<T>> groupingApply(Collection<T> collection, Function<T, K> function) {
        return collection
                .stream()
                .collect(Collectors.groupingBy(function));
    }

    public static <T, K, V> Map<K, List<V>> groupingApply(Collection<T> collection,
                                                          Function<T, K> functionKey,
                                                          Function<T, V> functionValue) {
        return collection
                .stream()
                .collect(Collectors.groupingBy(functionKey,
                        Collectors.mapping(functionValue, Collectors.toList())));
    }

    public static <T, R> Function<T, R> of(Function<T, R> function) {
        return function;
    }

    public static <T> Function<T, T> peek(Consumer<? super T> consumer) {
        return t -> {
            consumer.accept(t);
            return t;
        };
    }

    public static <K, V, K1, V1> Map<K1, V1> transformMap(Map<K, V> map,
                                                          Function<K, K1> functionKey,
                                                          Function<V, V1> functionValue) {
        Map<K1, V1> mapResult = Maps.newHashMapWithExpectedSize(map.size());
        map.forEach((key, value) -> mapResult.put(functionKey.apply(key), functionValue.apply(value)));
        return mapResult;
    }

}

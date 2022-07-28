package cn.fireround.common;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Collections {
    
    public static <T> Collection<List<T>> groupByFixedSize(Collection<T> collection, int chunkSize) {
        AtomicInteger counter = new AtomicInteger();
        return collection.stream().collect(Collectors.groupingBy(e -> counter.getAndIncrement() / chunkSize)).values();
    }
}

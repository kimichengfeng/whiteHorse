package com.wecash.guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2020-08-03
 * Time: 17:51
 */
public class CompletableFutures {

    public static <V> CompletableFuture<List<V>> successfulAsList(
            CompletableFuture<? extends V>... futures) {

        List<Present<V>> values =
                futures.length == 0
                        ? ImmutableList.of()
                        : Lists.newArrayListWithCapacity(futures.length);

        Arrays.stream(futures).forEach(i->values.add(getSucceedFuture(i)));


        return CompletableFuture.completedFuture(combine(values));
    }

    static <V> Present<V> getSucceedFuture(CompletableFuture<? extends V> future){
        try {
            return isSuccessful(future) ? new Present<>(((V)future.get())) : new Present<>((V)null);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();return null;
        }

    }
    static <V> boolean isSuccessful(CompletableFuture<V> future){
        return future.isDone() && !future.isCompletedExceptionally() && !future.isCancelled();
    }

    public static  <V> List<V> combine(List<Present<V>> values) {
        return values.stream().map(i -> i == null ? null : i.value).collect(Collectors.toList());

    }
    /** The result of a successful {@code Future}. */
    private static final class Present<V> {
        V value;

        Present(V value) {
            this.value = value;
        }

    }

    public static void main(String[] args) {
        Futures.successfulAsList();
    }
}

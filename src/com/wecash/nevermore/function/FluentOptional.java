package com.wecash.nevermore.function;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import com.wecash.nevermore.constant.MyConstant;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 提供流式运算，迭代出最终的值
 */
@Deprecated
public class FluentOptional<T> {
    // todo 记录中间状态，提供视图
    private Optional<T> optional;
    private String mainTracer;
    private Map pipeMap;

    private Map getPipeMap() {
        return pipeMap;
    }

    private void setPipeMap(Map pipeMap) {
        this.pipeMap = pipeMap;
    }

    private String getMainTracer() {
        return mainTracer;
    }

    private FluentOptional(Optional<T> optional) {
        this.optional = optional;
    }

    public FluentOptional<T> pipeMap(Map pipeMap) {
        this.setPipeMap(pipeMap);
        return this;
    }

    @SuppressWarnings("rawtypes")
    public FluentOptional<T> putPipeMap(String key, Object value) {
        this.getPipeMap().put(key, value);
        return this;
    }

    private void setMainTracer(String mainTracer) {
        this.mainTracer = mainTracer;
    }

    public FluentOptional<T> trace(String mainTracer) {
        this.setMainTracer(mainTracer);
        return this;
    }

    public static <T> FluentOptional<T> from(Optional<T> optional, Map pipeMap) {
        FluentOptional<T> fluentOptional = new FluentOptional<T>(optional);
        fluentOptional.setMainTracer("fluent" + "-" + UUID.randomUUID().toString());
        fluentOptional.setPipeMap(pipeMap);
        return fluentOptional;
    }

    public <U> FluentOptional<U> bind(Function<T, Optional<U>> function) {
        if (isPresent()) {
            // todo MDC埋点
            System.out.println("pipe" + this.pipeMap);
            return function.apply(get()) == null
                    ? from(Optional.<U> absent(), this.pipeMap).trace(this.mainTracer).pipeMap(this.pipeMap)
                    : from(function.apply(get()), this.pipeMap).trace(this.mainTracer).pipeMap(this.pipeMap);
        }

        return from(Optional.<U> absent(), this.pipeMap).trace(this.mainTracer).pipeMap(this.pipeMap);
    }

    public <U> FluentOptional<U> bindThrowNPE(Function<T, Optional<U>> function) throws FluentNPE {
        if (CheckChain.start().ORALL(function.apply(get()) == null, this.optional == Optional.absent()).result()) {
            throw new FluentNPE("流式计算中出现NPE" + this.toString());
        }
        if (isPresent()) {
            return function.apply(get()) == null ? from(Optional.<U> absent(), this.pipeMap).trace(this.mainTracer)
                    : from(function.apply(get()), this.pipeMap).trace(this.mainTracer);
        }

        return from(Optional.<U> absent(), this.pipeMap).trace(this.mainTracer);
    }

    public boolean isPresent() {
        return optional.isPresent();
    }

    public T get() {
        return optional.get();
    }

    public T or(T defaultValue) {
        return optional.or(defaultValue);
    }

    public Optional<T> or(Optional<? extends T> secondChoice) {
        return optional.or(secondChoice);
    }

    public T or(Supplier<? extends T> supplier) {
        return optional.or(supplier);
    }

    public T orNull() {
        return optional.orNull();
    }

    public Set<T> asSet() {
        return optional.asSet();
    }

    public <V> Optional<V> transform(Function<? super T, V> function) {
        return optional.transform(function);
    }

    public static void main(String[] args) throws FluentNPE {
        final String pipeSql = "select * from * where   version>-1   limit 0,10     select * from * where   base_info.dep=PEK   limit 0,10  select * from * where base_info.arr=CAN \n";
        List<String> sqlList = MyConstant.STAND_LINE_SPLITTER.splitToList(pipeSql);
        final FluentOptional<String> fluentOptional = FluentOptional.from(Optional.of("sds"), Maps.newHashMap());
        fluentOptional.bind(new Function<String, Optional<String>>() {
            @Override
            public Optional<String> apply(String sql) {
                // 追加in id
                fluentOptional.getPipeMap().put("ss", "1");
                System.out.println(fluentOptional.get());
                fluentOptional.getPipeMap().put("sdsa", 2312);
                fluentOptional.getPipeMap().put("dsas", 2312);
                return Optional.of("in field");
            }
        }).bind(new Function<String, Optional<String>>() {
            @Override
            public Optional<String> apply(String sql) {
                // 根据sql查出结果
                fluentOptional.getPipeMap().put("sss", "2");
                return Optional.of("");
            }
        }).bind(new Function<String, Optional<String>>() {
            @Override
            public Optional<String> apply(String sql) {
                // 根据结果查出id列表
                fluentOptional.getPipeMap().put("ss", "2");
                fluentOptional.getPipeMap().put("sdas", 123);
                fluentOptional.getPipeMap().put("dsas", 2312);
                return Optional.of("");
            }
        }).getPipeMap();
        final String idStr = "";
        for (int i = 0; i < 1; i++) {
            System.out.println(fluentOptional.bind(new Function<String, Optional<String>>() {
                @Override
                public Optional<String> apply(String sql) {
                    // 追加in id
                    if ("".equals(idStr)) {
                    }
                    fluentOptional.getPipeMap().put("ss", "1");
                    fluentOptional.getPipeMap().put("sdsa", 2312);
                    fluentOptional.getPipeMap().put("dsas", 2312);
                    return Optional.of("in field");
                }
            }).bind(new Function<String, Optional<String>>() {
                @Override
                public Optional<String> apply(String sql) {
                    // 根据sql查出结果
                    fluentOptional.getPipeMap().put("sss", "2");
                    return Optional.of("");
                }
            }).bind(new Function<String, Optional<String>>() {
                @Override
                public Optional<String> apply(String sql) {
                    // 根据结果查出id列表
                    fluentOptional.getPipeMap().put("ss", "2");
                    fluentOptional.getPipeMap().put("sdas", 123);
                    fluentOptional.getPipeMap().put("dsas", 2312);
                    return Optional.of("");
                }
            }).getPipeMap());
        }
        // String result = fluentOptional.bind(new Function<String, Optional<String>>() {
        // @Override
        // public Optional<String> apply(String input) {
        // // select * from * where id in (input)
        // return Optional.of("result");
        // }
        // }).get();
        // Object optional1 = FluentOptional.from(Optional.of("s")).mainTracer("1")
        // .bind(new Function<String, Optional<String>>() {
        // @Override public Optional<String> apply(String input) {
        // return Optional.of("test");
        // }
        // }).bind(new Function<String, Optional<Integer>>() {
        // @Override
        // public Optional<Integer> apply(String input) {
        // return Optional.of(1);
        // }
        // }).bind(new Function<Integer, Optional<ArrayList<Integer>>>() {
        // @Override
        // public Optional<ArrayList<Integer>> apply(Integer input) {
        // return Optional.of(Lists.newArrayList(1, 2, 3, 4));
        // }
        // }).bind(new Function<ArrayList<Integer>, Optional<String>>() {
        // @Override
        // public Optional<String> apply(ArrayList<Integer> input) {
        // return Optional.of("");
        // }
        // }).get();
        // System.out.println(optional1);
        // System.out.println(CheckChain.start().ANDALL(true, true, false).result());
    }
}

package com.wecash.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chengtong on 2018/3/9.
 */
public class StreamTest {
    public static void main(String[] args) {
        List<String> lines = Arrays.asList("spring", "node", "mkyong");
        List<String> result = lines.stream()                // convert list to stream
                .filter(line -> !"mkyong".equals(line))     // we dont like mkyong
                .collect(Collectors.toList());              // collect the output and convert streams to a List
        result.forEach(System.out::println);                //output : spring, node

        /** 用stream.filter() 过滤List .findAny().orElse (null) 返回一个对象的条件**/
        List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );

        Person result1 = persons.stream()                        // Convert to steam
                .filter(x -> "jack".equals(x.getName()))        // we want "jack" only
                .findAny()                                      // If 'findAny' then return found
                .orElse(null);                                  // If not found, return null

        System.out.println(result1);

        Person result2 = persons.stream()
                .filter(x -> "ahmook".equals(x.getName()))
                .findAny()
                .orElse(null);

        System.out.println(result2);

        /**多个条件**/
        Person result3 = persons.stream()
                .filter((p) -> "jack".equals(p.getName()) && 20 == p.getAge())
                .findAny()
                .orElse(null);

        System.out.println("result 3 :" + result3);

        //or like this
        Person result4 = persons.stream()
                .filter(p -> {
                    if ("jack".equals(p.getName()) && 20 == p.getAge()) {
                        return true;
                    }
                    return false;
                }).findAny()
                .orElse(null);

        System.out.println("result 4 :" + result4);

        /**Streams filter() and map()**/
        String name = persons.stream()
                .filter(x -> "jack".equals(x.getName()))
                .map(Person::getName)                        //convert stream to String
                .findAny()
                .orElse("");

        System.out.println("name : " + name);

        List<String> collect = persons.stream()
                .map(Person::getName)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);
    }
}

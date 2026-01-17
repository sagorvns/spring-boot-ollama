package Basic;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI {

    public static void main(String[] args) {

        List<Integer> l = Arrays.asList(10, 20, 30, 40, 50, 60)
                .stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(l);
        // create a list of integers
        List<Integer> number = Arrays.asList(2, 3, 4, 5, 4);

        // demonstration of map method
        List<Integer> square
                = number.stream()
                .map(x -> x * x)
                .collect(Collectors.toList());

        // create a list of String
        List<String> names = Arrays.asList(
                "Reflection", "Collection", "Stream");

        // demonstration of filter method
        List<String> result
                = names.stream()
                .filter(s -> s.startsWith("S"))
                .collect(Collectors.toList());

        System.out.println(result);

        // demonstration of sorted method
        List<String> show
                = names.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(show);

        // create a list of integers
        List<Integer> numbers
                = Arrays.asList(2, 3, 4, 5, 2);

        // collect method returns a set
        Set<Integer> squareSet
                = numbers.stream()
                .map(x -> x * x)
                .collect(Collectors.toSet());

        System.out.println(squareSet);

        // demonstration of forEach method
        number.stream()
                .map(x -> x * x)
                .forEach(y -> System.out.println(y));

        // demonstration of reduce method
        int even
                = number.stream()
                .filter(x -> x % 2 == 0)
                .reduce(0, (ans, i) -> ans + i);

        System.out.println(even);

        // Converting Product List into a Map
        //  Map<Integer,String> productPriceMap =
        //        productsList.stream().collect(Collectors.toMap(p->p.id, p->p.name));

        //blank
        Stream<Object> emptyStream = Stream.empty();
        emptyStream.forEach(i -> System.out.print(i));
        //array
        String name[] = {"asas", "dadwesdf"};
        Stream<String> strStrem = Stream.of(name);
        //builder method
        number.stream().sorted().forEach(System.out::println);
        Integer min=number.stream().min((x,y)->x.compareTo(y)).get();

        //java8 optional
        String str="namemm",newSTR=null;
        Optional<String> stropt=Optional.ofNullable(str);
       // System.out.println(Optional.ofNullable(newSTR).isPresent());
       // System.out.println(stropt.get());
        //System.out.println(stropt.orElse("Not present"));
        Optional<String> nameOptiona=getname();
        System.out.println(nameOptiona.orElse("Not present nameOptiona"));
    }
    public static Optional<String> getname(){
        String n=null;
        return Optional.ofNullable(n);
    }
}
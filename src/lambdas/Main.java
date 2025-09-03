package lambdas;

import java.util.*;

public class Main {

    record Person(String firstName, String lastName) {
        @Override
        public String toString() {
            return lastName + " " + firstName;
        }
    }
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(Arrays.asList(new Person("John", "Doe"),
                new Person("Jane", "Doe"),
                new Person("Jorge", "Garcia")));

        var comparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastName.compareTo(o2.lastName);
            }
        };


        Collections.sort(people, comparator);
        people.sort(Comparator.comparing(o -> o.lastName));
        process(v->(int) v*8);
        process(v->(int) v*16);
        System.out.println();
        System.out.println(people);
    }

    public static void process(Deposit deposit) {
        System.out.println(deposit.getAmount(8));
    }
}

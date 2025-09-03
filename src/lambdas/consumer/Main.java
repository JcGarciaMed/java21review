package lambdas.consumer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("ALPHA", "BETA", "GAMMA"));

        list.forEach(e->System.out.println(e));

        list.forEach(element->{
            char first = element.charAt(0);
            System.out.println(element+ " means " + first);
        });
    }
}

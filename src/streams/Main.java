package streams;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

record Car(String type, String make, String model, Integer engineCapacity, List<String> devices) {
    @Override
    public String toString() {
        return "[ " + type + " " + make + " " + model + " " + engineCapacity + " ]" ;
    }
}



public class Main {
    public static void main(String[] args) {
        List<Car> cars = List.of(
                new Car("sedan", "BMW", "530", 1998, List.of("Device2", "Device3")),
                new Car("sedan", "Audi", "A5", 1998, List.of("Device4", "Device5")),
                new Car("sedan", "Mercedes", "E-class", 2500, List.of("Device6", "Device7")),
                new Car("hatchback", "Skoda", "Octavia", 1600, List.of("Device2", "Device8")),
                new Car("hatchback", "Toyota", "TypeR", 1450, List.of("Device9", "Device20"))
        );

        System.out.println("=====Filter======");
        List<Car> sedanCars = cars.stream().filter(car->car.type().equals("sedan")).toList();
        sedanCars.forEach(System.out::println);

        System.out.println("=======map======");
        List<String> carMakeList = cars.stream().map(car->car.make()).toList();
        carMakeList.forEach(System.out::println);

        System.out.println("=====flatMap====");
        List<String> carMakeModelList = cars.stream().flatMap(car-> Stream.of(car.model(), car.make())).toList();
        carMakeModelList.forEach(System.out::println);

        System.out.println("=====flatMapDevices====");
        List<String> devices = cars.stream().flatMap(car-> car.devices().stream()).toList();
        devices.forEach(System.out::println);

       /* cars = cars.stream().filter(car->car.type().equals("sedan")).toList();
        cars.forEach(System.out::println);*/

        System.out.println("=====partitionBy====");
        Map<Boolean, List<Car>> partitionedCars = cars.stream().collect(Collectors.partitioningBy(car->car.type().equals("sedan")));
        System.out.println(partitionedCars);


        System.out.println("=====groupedBy====");
        Map<String, Map<String, Integer>> groupedCars = cars.stream()
                .collect(Collectors.groupingBy(Car::type, Collectors.toMap(
                        Car::make, Car::engineCapacity
                )));
        System.out.println(groupedCars);

        Map<String, Integer> combinedEngineCapacityPerCar = cars.stream()
                .collect(Collectors.groupingBy(Car::type, Collectors.summingInt(Car::engineCapacity)));

        System.out.println(combinedEngineCapacityPerCar);
    }
}

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static Collection<Person> persons;

    public static long getKids() {
        long countKids = persons.stream()
                .filter(p -> p.getAge() < 18)
                .count();
        return countKids;
    }

    public static List<String> getArmy() {
        List<String> soldiers = persons.stream()
                .filter(p -> (p.getAge() > 17 && p.getAge() < 28))
                .map(p -> p.getFamily())
                .collect(Collectors.toList());
        return soldiers;
    }

    public static List<Person> getEmployees() {
        List<Person> employees = persons.stream()
                .filter(p -> p.getEducation() == Education.HIGHER && p.getAge() > 17 && (p.getSex() == Sex.MAN ? p.getAge() < 65 : p.getAge() < 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        return employees;
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John", "Jeremy", "Martin");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown", "Piterson", "Johnson");
        persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }

        long countKids = getKids();
        System.out.println("Количество несовершеннолетних = " + countKids);
        List<String> soldiers = getArmy();
        List<Person> employees = getEmployees();
        System.out.println(employees.get(0) instanceof Person);
    }
}

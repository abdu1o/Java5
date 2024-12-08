import java.util.*;

class Fine {
    private String type;
    private double amount;
    private String city;

    public Fine(String type, double amount, String city) {
        this.type = type;
        this.amount = amount;
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getCity() {
        return city;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Fine{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", city='" + city + '\'' +
                '}';
    }
}

class Person {
    private String name;
    private String city;
    private List<Fine> fines;

    public Person(String name, String city) {
        this.name = name;
        this.city = city;
        this.fines = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public List<Fine> getFines() {
        return fines;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void addFine(Fine fine) {
        fines.add(fine);
    }

    public void removeFine(Fine fine) {
        fines.remove(fine);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", fines=" + fines +
                '}';
    }
}

class TaxFinesDatabase {
    private final Map<String, Person> database;

    public TaxFinesDatabase() {
        this.database = new HashMap<>();
    }

    public void addPerson(String id, String name, String city) {
        database.putIfAbsent(id, new Person(name, city));
    }

    public void addFine(String id, Fine fine) {
        if (database.containsKey(id)) {
            database.get(id).addFine(fine);
        }
    }

    public void removeFine(String id, Fine fine) {
        if (database.containsKey(id)) {
            database.get(id).removeFine(fine);
        }
    }

    public void replacePerson(String id, Person newPerson) {
        database.put(id, newPerson);
    }

    public void printDatabase() {
        database.forEach((id, person) -> {
            System.out.println("ID: " + id);
            System.out.println(person);
        });
    }

    public void printById(String id) {
        if (database.containsKey(id)) {
            System.out.println("ID: " + id);
            System.out.println(database.get(id));
        } else {
            System.out.println("No record found for ID: " + id);
        }
    }

    public void printByFineType(String type) {
        database.values().stream()
                .filter(person -> person.getFines().stream().anyMatch(fine -> fine.getType().equals(type)))
                .forEach(System.out::println);
    }

    public void printByCity(String city) {
        database.values().stream()
                .filter(person -> person.getCity().equals(city))
                .forEach(System.out::println);
    }
}
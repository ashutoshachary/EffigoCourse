public class Main {

    public static void main(String[] args) {
        Person person = new Person("Rayan Slim", "Canadian", "01/01/1111", 5);

        if (person.applyPassport()) {
            person.printPerson(person);
            System.out.println("Congratulations " + person.getName() + ". Your passport was approved!");
        } else {
            person.printPerson(person);
            System.out.println("We are sorry " + person.getName() + ". We cannot process your application.");
        }

    }

}

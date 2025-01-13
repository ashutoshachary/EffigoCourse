public class Airline {

    private Person[] people;

    // array that stores Person objects...

    public Airline() {
        this.people = new Person[11];

    }

    public Person getPerson(int index) {
        return new Person(this.people[index]);
    }

    public void setPerson(Person person) {
        int index = person.getSeatNumber() - 1;
        this.people[index] = new Person(person);

    }

}

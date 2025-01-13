public class ContactManager {
    private Contact[] contacts;

    public ContactManager(Contact[] contacts) {
        // 1. set field equal to a new array with the same length as the parameter
        // 2. Set each element in the field equal to a deep copy of an object in the
        // contacts parameter
        this.contacts = new Contact[contacts.length];
        for (int i = 0; i < contacts.length; i++) {
            this.contacts[i] = new Contact(contacts[i]);
        }

    }

    public Contact getContact(int index) {
        return new Contact(contacts[index]);
    }

    public void setContact(Contact contact, int index) {
        this.contacts[index] = new Contact(contact);
    }

}

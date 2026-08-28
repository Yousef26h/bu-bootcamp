import java.util.ArrayList;
import java.util.HashMap;

public class ContactManager {
    public static void main(String[] args) {
        HashMap<String, Contact> contacts = new HashMap<>();

        Contact contact1 = new Contact("John Doe", "123-456-7890");
        Contact contact2 = new Contact("Jane Smith", "987-654-3210");
        Contact contact3 = new Contact("Alice Johnson", "555-123-4567");
        Contact contact4 = new Contact("Bob Brown", "444-555-6666");
        Contact contact5 = new Contact("Charlie Davis", "111-222-3333");

        contacts.put("John Doe", contact1);
        contacts.put("Jane Smith", contact2);
        contacts.put("Alice Johnson", contact3);
        contacts.put("Bob Brown", contact4);
        contacts.put("Charlie Davis", contact5);

        if(contacts.containsKey("Alice Johnson")) {
            System.out.println("Found Alice Johnson: " + contacts.get("Alice Johnson"));
        } else {
            System.out.println("Contact not found.");
        }
    
        if(contacts.containsKey("Yousef Salem")) {
            System.out.println("Found Yousef Salem: " + contacts.get("Yousef Salem"));
        } else {
            System.out.println("Contact not found.");
        }

        ArrayList<Contact> sortedContacts = new ArrayList<>(contacts.values());
        sortedContacts.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
        System.out.println("===Sorted Contacts===");
        for (Contact contact : sortedContacts) {
            System.out.println(contact);
        }
    }
}

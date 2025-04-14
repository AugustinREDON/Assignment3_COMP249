// ---------------------------------------------------------------
// Assignment 3
// Question: Part 2 - TariffList Class Implementation
// Written by: Augustin Redon 40240986 - Jacob Paterak 40268958
// ---------------------------------------------------------------



import java.util.NoSuchElementException;

// TariffList class implements TariffPolicy and represents a linked list of Tariff objects
public class TariffList implements TariffPolicy {
    private TariffNode head; // Reference to the first node in the list
    private int size; // Number of nodes in the list

    // Default constructor - initializes an empty list
    public TariffList() {
        head = null;
        size = 0;
    }

    // Copy constructor - creates a deep copy of another TariffList
    public TariffList(TariffList Tl) {
        if (Tl.head == null) { // If the source list is empty
            this.head = Tl.head;
            this.size = Tl.size;
        } else {
            // Clone the head node
            this.head = new TariffNode(Tl.head);
            TariffNode currentNew = this.head; // Pointer for the new list
            TariffNode currentOld = Tl.head.next; // Pointer for the source list

            // Traverse the source list and clone each node
            while (currentOld != null) {
                TariffNode newNode = new TariffNode(currentOld);
                currentNew.next = newNode; // Link the new node to the new list
                currentOld = currentOld.next; // Move to the next node in the source list
            }

            this.size = Tl.size; // Copy the size of the source list
        }
    }

    // Adds a new Tariff to the start of the list
    public void addToStart(Tariff tariff) {
        head = new TariffNode(tariff, head); // Create a new node and link it to the current head
        size++; // Increment the size of the list
    }

    // Inserts a Tariff at a specific index in the list
    public void insertAtIndex(Tariff tariff, int index) {
        // Check if the index is valid
        if (index < 0 || index > size)
            throw new NoSuchElementException("Invalid Index");

        // If inserting at the start, use addToStart
        if (index == 0) {
            addToStart(tariff);
            return;
        }

        // Traverse the list to find the position before the index
        TariffNode position = head;
        for (int i = 0; i < index - 1; i++) {
            position = position.next;
        }

        // Create a new node and link it at the specified index
        TariffNode newNode = new TariffNode(tariff, position.getNext());
        position.next = newNode;
        size++; // Increment the size of the list
    }

    // Deletes the first node in the list
    public void deleteFromStart() {
        if (head == null) { // If the list is empty
            System.out.println("Empty List");
            return;
        }
        head = head.next; // Move the head to the next node
        size--; // Decrement the size of the list
    }

    // Deletes a node at a specific index in the list
    public void deleteAtIndex(int index) {
        // Check if the index is valid
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("Invalid Index");
        }

        // If deleting the first node, use deleteFromStart
        if (index == 0) {
            deleteFromStart();
            return;
        }

        // Traverse the list to find the position before the index
        TariffNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        // Remove the node at the specified index
        current.next = current.next.next;
        size--; // Decrement the size of the list
    }

    // Replaces the Tariff at a specific index with a new Tariff
    public void replaceAtIndex(Tariff tariff, int index) {
        if (index < 0 || index >= size) // Check if the index is valid
            return;

        // Traverse the list to find the node at the specified index
        TariffNode position = head;
        for (int i = 0; i < index; i++) {
            position = position.next;
        }

        // Replace the Tariff in the node
        position.setTariff(new Tariff(tariff));
    }

    // Creates a deep copy of the list
    public TariffList clone() {
        return new TariffList(this); // Use the copy constructor
    }

    // Checks if two TariffLists are equal
    public boolean equals(TariffList Tl) {
        if (this == Tl) return true; // Same reference
        if (Tl == null) return false; // Null check
        if (this.size != Tl.size) return false; // Fast fail if sizes differ

        // Compare each node in the two lists
        TariffNode position = head;
        TariffNode position2 = Tl.head;

        while (position != null && position2 != null) {
            if (!position.equals(position2)) {
                return false; // Nodes are not equal
            }
            position = position.getNext();
            position2 = position2.getNext();
        }
        return position == null && position2 == null; // Both lists should end at the same time
    }

    // Finds a TariffNode based on origin, destination, and category
    public TariffNode find(String origin, String destination, String category) {
        TariffNode position = head;
        int iteration = 0;

        // Traverse the list to find the matching node
        while (position != null) {
            iteration++;
            Tariff t = position.getTariff();
            if (t.getOriginCountry().trim().equalsIgnoreCase(origin.trim()) &&
                t.getDestinationCountry().trim().equalsIgnoreCase(destination.trim()) &&
                t.getProductCategory().trim().equalsIgnoreCase(category.trim())) {
                System.out.println("It was found after " + iteration + " iterations");
                return position;
            }
            position = position.getNext();
        }
        System.out.println("It was not found after " + iteration + " iterations");
        return null; // Return null if no match is found
    }

    // Checks if the list contains a Tariff with the specified details
    public boolean contains(String origin, String destination, String category) {
        TariffNode position = head;

        // Traverse the list to check for a match
        while (position != null) {
            Tariff t = position.getTariff();
            if (t.getOriginCountry().trim().equalsIgnoreCase(origin.trim()) &&
                t.getDestinationCountry().trim().equalsIgnoreCase(destination.trim()) &&
                t.getProductCategory().trim().equalsIgnoreCase(category.trim())) {
                return true; // Match found
            }
            position = position.getNext();
        }
        return false; // No match found
    }

    // Evaluates a trade based on the proposed and minimum tariffs
    @Override
    public String evaluateTrade(double proposedTariff, double minimumTariff) {
        if (proposedTariff >= minimumTariff)
            return "Accepted";
        else if (proposedTariff >= (minimumTariff - minimumTariff * 0.20))
            return "Conditionally Accepted";
        else
            return "Rejected";
    }

    // Displays the list of Tariffs
    public void displayList() {
        if (head == null) { // Check if the list is empty
            System.out.println("List is empty");
            return;
        }

        TariffNode current = head;
        int index = 0;

        // Traverse the list and print each node
        while (current != null) {
            System.out.println("[" + index + "] " + current.getTariff());
            current = current.next;
            index++;
        }
    }

    // Inner class representing a node in the TariffList
    public class TariffNode {
        private Tariff tariff; // Tariff object stored in the node
        private TariffNode next; // Reference to the next node

        // Default constructor
        public TariffNode() {
            this.tariff = null;
            this.next = null;
        }

        // Parameterized constructor
        public TariffNode(Tariff tariff, TariffNode next) {
            this.tariff = tariff;
            this.next = next;
        }

        // Copy constructor
        public TariffNode(TariffNode Tn) {
            this.tariff = new Tariff(Tn.tariff); // Deep copy of the Tariff
            this.next = (Tn.next != null) ? Tn.next : null; // Shallow copy of the next node
        }

        // Clones the current node
        public TariffNode clone() {
            return new TariffNode(this); // Use the copy constructor
        }

        // Checks if two nodes are equal
        public boolean equals(TariffNode Tn) {
            if (Tn == null) {
                return false; // Return false if the other node is null
            }
            // Compare the Tariff objects and recursively check the next nodes
            return this.tariff.equals(Tn.tariff) &&
                   ((this.next == null && Tn.next == null) ||
                    (this.next != null && this.next.equals(Tn.next)));
        }

        // Getters and setters for TariffNode
        public Tariff getTariff() {
            return this.tariff;
        }

        public TariffNode getNext() {
            return this.next;
        }

        public void setNext(TariffNode next) {
            this.next = next;
        }

        public void setTariff(Tariff tariff) {
            this.tariff = tariff;
        }
    }
}

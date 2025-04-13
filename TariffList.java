 import java.util.NoSuchElementException;

public class TariffList implements TariffPolicy{
    private TariffNode head;
    private int size;

    public TariffList(){
        head = null;
        size = 0;
    }

    public TariffList(TariffList Tl) {
        //Tweak this mabye incase head is not null
        if(Tl.head == null)
            this.head = Tl.head;
        this.size = Tl.size;
    }  

    public void addToStart(Tariff tariff){
        head = new TariffNode(tariff,head);
        size++;
    }

    public void insertAtIndex(Tariff tariff, int index){
        //checks right index
        if(index < 0 || index > size)
            throw new NoSuchElementException("Invalid Index");

        //if index is at the start call add to start method
        if(index == 0){
            addToStart(tariff);
            return;
        }

        //Start from the beginning of the list
        TariffNode position = head;

        //Goes through until the before index value
        for(int i = 0; i < index -1; i++){
            //moves position one step forward in the list
            position = position.next;
        }

        TariffNode newNode = new TariffNode(tariff,position.getNext());
        position.next = newNode;
        size++;

    }

    public void deleteFromStart (){
        if(head == null) {
            System.out.println("Empty List");
            return;
        }
        head = head.next;
        size--;

    }

    public void deleteAtIndex(int index){
        if(index < 0 || index >= size){
            throw new NoSuchElementException("Invalid Index");
        }

        if(index == 0){
            deleteFromStart();
            return;
        }

        TariffNode current = head;
        for(int i = 0; i < index-1; i++){
            current = current.next;
        }

        current.next = current.next.next; 
        size--;
    }

    public void replaceAtIndex(Tariff tariff, int index){
        if(index<0 || index > size)
            return;
        TariffNode position = head;
        for(int i = 0; i < index -1; i++){
            position = position.next;
        }
        position.setTariff(new Tariff(tariff));

    }

    //Need to edit to make a deep copy
    public boolean equals(TariffList Tl){
        TariffNode position = head;
        TariffNode position2 = Tl.head;
        while(position != null && position2 != null){
            if(!position.equals(position2))
            {
                return false;
            }
            position = position.getNext();
            position2 = position2.getNext();
        }
        return position == null && position2 == null;

    }
    
    public TariffNode find(String origin, String destination, String category){
        TariffNode position = head;
        int iteration = 0;
        while(position != null){
            iteration++;
            Tariff t = position.getTariff();
            if(t.getOriginCountry().trim().equalsIgnoreCase(origin.trim()) && 
            t.getDestinationCountry().trim().toLowerCase().equalsIgnoreCase(destination.trim()) 
            && t.getProductCategory().trim().toLowerCase().equalsIgnoreCase(category.trim())) {
                System.out.println("It was found after "  + iteration + " iterations");
                return position;
            }
            position = position.getNext();
        }
        System.out.println("It was not found after " + iteration + " iterations");
        return null;
    }


    public boolean contains(String origin, String destination, String category){
        TariffNode position = head;
        while(position != null){
            Tariff t = position.getTariff();
            if(t.getOriginCountry().trim().equalsIgnoreCase(origin.trim()) && 
            t.getDestinationCountry().trim().equalsIgnoreCase(destination.trim()) && 
            t.getProductCategory().trim().equalsIgnoreCase(category.trim())) {
                return true;
            }
            position = position.getNext();
        }
         return false;
    }

    @Override
    public String evaluateTrade(double proposedTariff, double minimumTariff) {
        System.out.println(minimumTariff + proposedTariff * 0.20);
        if(proposedTariff >= minimumTariff)
            return "Accepeted";
        else if(proposedTariff >= (minimumTariff - minimumTariff * 0.20))
            return "Accepted Conditionally";
        else
            return "Rejected";
    }
    
    //Displays the list
    public void displayList(){
        //check if list is empty
        if(head == null){
            System.out.println("List is empty");
        }

        TariffNode current = head;
        int index = 0;

        while(current != null){
            System.out.println("[" + index + "]" + current.getTariff());
            current = current.next;
            index++;
        }
        

    }

    //Inner class node 
    public class TariffNode {

        private Tariff tariff;
        private TariffNode next;

        //Default constructor
        public TariffNode() {
            this.tariff = null;
            this.next = null;
        }

        //Parametrized constructor
        public TariffNode(Tariff tariff, TariffNode next) {
            this.tariff = tariff;
            this.next = next;
        }

        //Copy constructor
        public TariffNode(TariffNode Tn){
            this.tariff = new Tariff(Tn.tariff);
            this.next = (Tn.next != null) ? Tn.next : null;
        }

        public TariffNode clone(){
            return new TariffNode(this);
        }

        //Work on this
        public boolean equals(TariffNode Tn){
            if(Tn == null){
                return false; // return false if the other node is null
            }
            //Compares the tariff objects and recursively checks the next nodes
            return this.tariff.equals(Tn.tariff) && ((this.next == null && Tn.next == null || (this.next != null && this.next.equals(Tn.next))));
        }

        //Getters and setter for TariffNode
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

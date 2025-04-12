 import java.util.NoSuchElementException;

public class TariffList implements TariffPolicy{
    private TariffNode head;
    private int size;

    public TariffList(){
        head = null;
        size = 0;
    }
    public TariffList(TariffList Tl)
    {
        //Tweak this mabye incase head is not null
        if(Tl.head == null)
            this.head = Tl.head;
        this.size = Tl.size;
    }
    public void addToStart(Tariff tariff)
    {
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
    public void deleteFromStart(Tariff tariff){
        if(head == null) {
            System.out.println("Empty List");
            return;
        }
        head = head.next;
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
            if(t.getOriginCountry().equals(origin) && t.getDestinationCountry().equals(destination) && t.getProductCategory().equals(category)) {
                System.out.println("It was found after "  + iteration + " iterations");
                return position;
            }
            position = position.getNext();
        }
        System.out.println("It was not found after " + iteration + " iterations");
        return null;
    }
    public boolean contains(String origin, String destination, String category)
    {
        TariffNode position = head;
        while(position != null){
            Tariff t = position.getTariff();
            if(t.getOriginCountry().equals(origin) && t.getDestinationCountry().equals(destination) && t.getProductCategory().equals(category)) {
                return true;
            }
            position = position.getNext();
        }
         return false;
    }
    @Override
    public String evaluateTrade(double proposedTariff, double minimumTariff) {
        if(proposedTariff >= minimumTariff)
            return "Accepeted";
        else if(proposedTariff >= minimumTariff + minimumTariff * 0.20)
            return "Accepted Conditionally";
        //Surcharge = Trade value * ((Minimum Tariff - ProposedTariff) / 100
        else
            return "Rejected";

        
//        throw new UnsupportedOperationException("Unimplemented method 'evaluateTrade'");
    }
    public class TariffNode {
        private Tariff tariff;
        private TariffNode next;
        public TariffNode() {
            this.tariff = null;
            this.next = null;
        }
        public TariffNode(Tariff tariff, TariffNode next) {
            this.tariff = tariff;
            this.next = next;
        }
        public TariffNode(TariffNode Tn){
            this.tariff = new Tariff(Tn.tariff);
            this.next = (Tn.next != null) ? Tn.next : null;
        }
        public TariffNode clone(){
            return new TariffNode(this);
        }
        //Work on this
        public boolean equals(TariffNode Tn){
            return this.tariff.equals(Tn.tariff);
        }
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

    //inner class TariffNode
    
}

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
    @Override
    public String evaluateTrade(double proposedTariff, double minimumTariff) {
        
        throw new UnsupportedOperationException("Unimplemented method 'evaluateTrade'");
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

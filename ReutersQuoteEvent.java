public class ReutersQuoteEvent extends TradeEvent 
{

    private double bidPrice;
    private double askPrice;

    public ReutersQuoteEvent(String senderId, long timestamp, double basePrice,double bidPrice, double askPrice) 
    {
        super(senderId, timestamp, basePrice);
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
    }

    @Override
    public void dispatch() {
        double spread = askPrice - bidPrice;

        System.out.println("Reuters Market Quote");
        System.out.println("Sender ID: " + senderId);
        System.out.println("Bid Price: " + bidPrice);
        System.out.println("Ask Price: " + askPrice);
        System.out.println("Bid-Ask Spread: " + spread);
    }
}
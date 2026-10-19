public class TradingEngineApp 
{

    public static void main(String[] args) 
    {

        TradeEvent event1 = new FIXOrderEvent(
            "FIX-Gateway", System.currentTimeMillis(),
            10000.0, "ORD-101"
        );

        TradeEvent event2 = new ReutersQuoteEvent(
            "Reuters-Gateway", System.currentTimeMillis(),
            100.0, 99.5, 100.0
        );

        System.out.println("FIX Order Event ");
        event1.dispatch();

        System.out.println("\nReuters Quote Event ");
        event2.dispatch();
    }
}
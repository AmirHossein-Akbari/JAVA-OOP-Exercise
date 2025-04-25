/**
 * کلاس مدیریت فروش و قیمت‌گذاری بلیط‌ها
 */
public class TicketCounter {
    private static final double BASE_PRICE = 10.00; // قیمت پایه بلیط
    
    /**
     * صدور بلیط جدید
     * @param visitor بازدیدکننده
     * @param ride جاذبه
     * @return بلیط صادر شده
     */
    public Ticket issueTicket(Visitor visitor, Ride ride) {
        double price = calculatePrice(visitor, ride);
        Ticket ticket = new Ticket(visitor, ride, price);
        visitor.addTicket(ticket);
        return ticket;
    }
    
    /**
     * محاسبه قیمت بلیط بر اساس عوامل مختلف
     * @param visitor بازدیدکننده
     * @param ride جاذبه
     * @return قیمت نهایی
     */
    private double calculatePrice(Visitor visitor, Ride ride) {
        double price = BASE_PRICE;
        
        // تنظیم قیمت بر اساس نوع جاذبه
        switch(ride.getType()) {
            case THRILL: price *= 1.3; break;
            case WATER: price *= 1.2; break;
            case FAMILY: price *= 1.0; break;
            case KIDS: price *= 0.7; break;
        }
        
        // اعمال تخفیف برای کاربران VIP
        if(visitor instanceof VIPVisitor) {
            price *= (1 - ((VIPVisitor)visitor).getDiscount());
        }
        
        return price;
    }
}

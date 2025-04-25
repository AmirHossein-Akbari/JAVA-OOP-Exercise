/**
 * کلاس نماینده بلیط جاذبه‌های پارک
 */
public class Ticket {
    private String id;          // شماره یکتا بلیط
    private Visitor visitor;    // بازدیدکننده مالک بلیط
    private Ride ride;         // جاذبه مربوطه
    private double price;      // قیمت بلیط
    private boolean used;      // وضعیت استفاده
    
    /**
     * سازنده کلاس بلیط
     * @param visitor بازدیدکننده
     * @param ride جاذبه
     * @param price قیمت
     */
    public Ticket(Visitor visitor, Ride ride, double price) {
        this.id = "TKT-" + System.currentTimeMillis();
        this.visitor = visitor;
        this.ride = ride;
        this.price = price;
        this.used = false;
    }
    
    /**
     * ثبت استفاده از بلیط
     */
    public void useTicket() {
        this.used = true;
    }
    
    @Override
    public String toString() {
        return String.format("بلیط #%s برای %s روی %s - قیمت: %.2f تومان - وضعیت: %s",
                id, visitor.getName(), ride.getName(), price, 
                used ? "استفاده شده" : "معتبر");
    }
    
    // متدهای دسترسی (getters)
    public String getId() { return id; }
    public Visitor getVisitor() { return visitor; }
    public Ride getRide() { return ride; }
    public double getPrice() { return price; }
    public boolean isUsed() { return used; }
}

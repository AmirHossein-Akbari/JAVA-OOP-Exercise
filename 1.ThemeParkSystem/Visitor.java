import java.util.ArrayList;
import java.util.List;

/**
 * کلاس نماینده بازدیدکننده پارک
 * شامل اطلاعات شخصی و مدیریت بلیط‌ها
 */
public class Visitor {
    private String name;    // نام بازدیدکننده
    private int age;        // سن بازدیدکننده
    private List<Ticket> tickets; // لیست بلیط‌های خریداری شده
    
    /**
     * سازنده کلاس بازدیدکننده
     * @param name نام بازدیدکننده
     * @param age سن بازدیدکننده
     */
    public Visitor(String name, int age) {
        this.name = name;
        this.age = age;
        this.tickets = new ArrayList<>();
    }
    
    /**
     * اضافه کردن بلیط جدید به لیست بازدیدکننده
     * @param ticket بلیط خریداری شده
     */
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
    
    /**
     * نمایش تمام بلیط‌های بازدیدکننده
     */
    public void showTickets() {
        System.out.println("\nبلیط‌های " + name + ":");
        for(Ticket t : tickets) {
            System.out.println("- " + t);
        }
    }
    
    // متدهای دسترسی (getters)
    public String getName() { return name; }
    public int getAge() { return age; }
    public List<Ticket> getTickets() { return tickets; }
}

import java.util.ArrayList;
import java.util.List;

/**
 * کلاس مدیریت پارک و جاذبه‌ها
 */
public class Admin {
    private List<Ride> rides; // لیست جاذبه‌های پارک
    
    /**
     * سازنده کلاس مدیریت
     */
    public Admin() {
        this.rides = new ArrayList<>();
    }
    
    /**
     * اضافه کردن جاذبه جدید
     * @param ride جاذبه جدید
     */
    public void addRide(Ride ride) {
        rides.add(ride);
        System.out.println("جاذبه جدید اضافه شد: " + ride.getName());
    }
    
    /**
     * حذف جاذبه از پارک
     * @param rideName نام جاذبه
     */
    public void removeRide(String rideName) {
        for(Ride ride : rides) {
            if(ride.getName().equalsIgnoreCase(rideName)) {
                rides.remove(ride);
                System.out.println("جاذبه حذف شد: " + rideName);
                return;
            }
        }
        System.out.println("جاذبه یافت نشد: " + rideName);
    }
    
    /**
     * نمایش برنامه زمانی یک جاذبه
     * @param rideName نام جاذبه
     */
    public void printSchedule(String rideName) {
        for(Ride ride : rides) {
            if(ride.getName().equalsIgnoreCase(rideName)) {
                System.out.println("\nبرنامه زمانی " + rideName + ":");
                for(String time : ride.getSchedule()) {
                    System.out.println("- " + time);
                }
                return;
            }
        }
        System.out.println("جاذبه یافت نشد!");
    }
    
    // متدهای دسترسی (getters)
    public List<Ride> getAllRides() { return rides; }
}

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

/**
 * کلاس نماینده یک جاذبه در پارک تفریحی
 * شامل قابلیت‌های مدیریت صف و زمان‌بندی
 */
public class Ride {
    private String name;        // نام جاذبه
    private int capacity;       // ظرفیت حداکثر نفرات در هر بار
    private Queue<Visitor> queue; // صف بازدیدکنندگان
    private RideType type;      // نوع جاذبه
    private List<String> schedule; // برنامه زمانی کارکرد جاذبه
    
    /**
     * انواع مختلف جاذبه‌های پارک
     */
    public enum RideType {
        THRILL,      // جاذبه‌های پرسرعت و هیجان‌انگیز
        FAMILY,      // جاذبه‌های خانوادگی
        KIDS,        // جاذبه‌های مخصوص کودکان
        WATER        // جاذبه‌های آبی
    }
    
    /**
     * سازنده کلاس جاذبه
     * @param name نام جاذبه
     * @param capacity ظرفیت جاذبه
     * @param type نوع جاذبه
     */
    public Ride(String name, int capacity, RideType type) {
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.queue = new LinkedList<>();
        this.schedule = new ArrayList<>();
        initializeDefaultSchedule();
    }
    
    /**
     * تنظیم برنامه زمانی پیش‌فرض برای جاذبه
     */
    private void initializeDefaultSchedule() {
        schedule.add("10:00 صبح");
        schedule.add("12:00 ظهر");
        schedule.add("2:00 بعدازظهر");
        schedule.add("4:00 عصر");
    }
    
    /**
     * اضافه کردن بازدیدکننده به صف جاذبه
     * @param visitor بازدیدکننده
     */
    public void addVisitor(Visitor visitor) {
        if(queue.size() < capacity) {
            queue.add(visitor);
            System.out.println(visitor.getName() + " به صف " + name + " اضافه شد");
        } else {
            System.out.println("صف " + name + " تکمیل است!");
        }
    }
    
    /**
     * شروع به کار جاذبه و سوار کردن بازدیدکنندگان
     */
    public void startRide() {
        System.out.println("\n=== " + name + " در حال شروع ===");
        System.out.println("نوع جاذبه: " + type);
        
        int ridersCount = Math.min(capacity, queue.size());
        for(int i = 0; i < ridersCount; i++) {
            Visitor v = queue.poll();
            System.out.println(v.getName() + " سوار جاذبه شد!");
        }
        
        System.out.println("=== جاذبه به کار خود پایان داد ===\n");
    }
    
    // متدهای دسترسی (getters)
    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public RideType getType() { return type; }
    public List<String> getSchedule() { return schedule; }
    public int getQueueSize() { return queue.size(); }
}

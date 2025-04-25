/**
 * کلاس نماینده بازدیدکننده ویژه (VIP)
 * شامل امتیازات ویژه نسبت به بازدیدکنندگان عادی
 */
public class VIPVisitor extends Visitor {
    /**
     * سازنده کلاس بازدیدکننده VIP
     * @param name نام بازدیدکننده
     * @param age سن بازدیدکننده
     */
    public VIPVisitor(String name, int age) {
        super(name, age);
    }
    
    /**
     * استفاده از حق تقدم در صف
     * @param ride جاذبه مورد نظر
     */
    public void usePriorityAccess(Ride ride) {
        System.out.println(getName() + " (VIP) از حق تقدم در صف " + ride.getName() + " استفاده کرد");
    }
    
    /**
     * محاسبه تخفیف ویژه برای کاربران VIP
     * @return درصد تخفیف
     */
    public double getDiscount() {
        return 0.15; // 15% تخفیف برای کاربران ویژه
    }
}

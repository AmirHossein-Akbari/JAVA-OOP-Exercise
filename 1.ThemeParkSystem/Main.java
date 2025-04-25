
    import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 کلاس اصلی برنامه با سیستم منوی فارسی*/
 public class Main{
    private static Scanner scanner = new Scanner(System.in);
    private static Admin admin = new Admin();
    private static TicketCounter ticketCounter = new TicketCounter();
    private static List<Visitor> visitors = new ArrayList<>();
    
    public static void main(String[] args) {
        initializeSampleData();
        
        while(true) {
            showMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // مصرف خط جدید
            
            switch(choice) {
                case 1: manageVisitors(); break;
                case 2: manageRides(); break;
                case 3: manageTickets(); break;
                case 4: runParkOperations(); break;
                case 5: viewReports(); break;
                case 6: 
                    System.out.println("در حال خروج از سیستم...");
                    return;
                default: 
                    System.out.println("گزینه نامعتبر!");
            }
        }
    }
    
    /**
     * نمایش منوی اصلی
     */
    private static void showMainMenu() {
        System.out.println("\n=== سیستم مدیریت پارک تفریحی ===");
        System.out.println("1. مدیریت بازدیدکنندگان");
        System.out.println("2. مدیریت جاذبه‌ها" );
        System.out.println("3. مدیریت بلیط‌ها");
        System.out.println("4. عملیات پارک");
        System.out.println("5. گزارش‌ها");
        System.out.println("6. خروج");
        System.out.print("انتخاب خود را وارد کنید: ");
    }
    
    /**
     * مقداردهی اولیه داده‌های نمونه
     */
    private static void initializeSampleData() {
        // اضافه کردن جاذبه‌های نمونه
        admin.addRide(new Ride("قطار اژدها", 5, Ride.RideType.THRILL));
        admin.addRide(new Ride("چرخ فلک", 8, Ride.RideType.FAMILY));
        
        // اضافه کردن بازدیدکنندگان نمونه
        visitors.add(new Visitor("علی", 25));
        visitors.add(new VIPVisitor("سارا", 30));
    }

/**
 * مدیریت عملیات مربوط به بازدیدکنندگان
 */
private static void manageVisitors() {
    while(true) {
        System.out.println("\n--- مدیریت بازدیدکنندگان ---");
        System.out.println("1. ثبت بازدیدکننده جدید");
        System.out.println("2. ثبت بازدیدکننده VIP");
        System.out.println("3. نمایش لیست بازدیدکنندگان");
        System.out.println("4. نمایش بلیط‌های بازدیدکننده");
        System.out.println("5. بازگشت به منوی اصلی");
        System.out.print("لطفاً انتخاب کنید: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // مصرف خط جدید
        
        switch(choice) {
            case 1:
                registerVisitor(false);
                break;
            case 2:
                registerVisitor(true);
                break;
            case 3:
                listAllVisitors();
                break;
            case 4:
                showVisitorTickets();
                break;
            case 5:
                return;
            default:
                System.out.println("گزینه نامعتبر!");
        }
    }
}

/**
 * ثبت نام بازدیدکننده جدید
 * @param isVip آیا بازدیدکننده VIP است؟
 */
private static void registerVisitor(boolean isVip) {
    System.out.print("نام بازدیدکننده: ");
    String name = scanner.nextLine();
    
    System.out.print("سن بازدیدکننده: ");
    int age = scanner.nextInt();
    scanner.nextLine();
    
    Visitor visitor;
    if(isVip) {
        visitor = new VIPVisitor(name, age);
        System.out.println("بازدیدکننده VIP با موفقیت ثبت شد!");
    } else {
        visitor = new Visitor(name, age);
        System.out.println("بازدیدکننده عادی با موفقیت ثبت شد!");
    }
    
    visitors.add(visitor);
}

/**
 * نمایش لیست تمام بازدیدکنندگان
 */
private static void listAllVisitors() {
    if(visitors.isEmpty()) {
        System.out.println("هنوز بازدیدکننده‌ای ثبت نشده است!");
        return;
    }
    
    System.out.println("\nلیست بازدیدکنندگان:");
    for(int i = 0; i < visitors.size(); i++) {
        Visitor v = visitors.get(i);
        String type = (v instanceof VIPVisitor) ? "VIP" : "عادی";
        System.out.printf("%d. %s - سن: %d - نوع: %s%n", 
            i+1, v.getName(), v.getAge(), type);
    }
}

/**
 * نمایش بلیط‌های یک بازدیدکننده
 */
private static void showVisitorTickets() {
    listAllVisitors();
    if(visitors.isEmpty()) return;
    
    System.out.print("شماره بازدیدکننده: ");
    int index = scanner.nextInt() - 1;
    scanner.nextLine();
    
    if(index >= 0 && index < visitors.size()) {
        visitors.get(index).showTickets();
    } else {
        System.out.println("شماره بازدیدکننده نامعتبر!");
    }
}
/**
 * مدیریت عملیات مربوط به جاذبه‌ها
 */
private static void manageRides() {
    while(true) {
        System.out.println("\n--- مدیریت جاذبه‌ها ---");
        System.out.println("1. اضافه کردن جاذبه جدید");
        System.out.println("2. حذف جاذبه");
        System.out.println("3. نمایش لیست جاذبه‌ها");
        System.out.println("4. مدیریت زمان‌بندی جاذبه");
        System.out.println("5. بازگشت به منوی اصلی");
        System.out.print("لطفاً انتخاب کنید: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch(choice) {
            case 1:
                addNewRide();
                break;
            case 2:
                removeExistingRide();
                break;
            case 3:
                listAllRides();
                break;
            case 4:
                manageRideSchedule();
                break;
            case 5:
                return;
            default:
                System.out.println("گزینه نامعتبر!");
        }
    }
}

/**
 * اضافه کردن جاذبه جدید به سیستم
 */
private static void addNewRide() {
    System.out.print("نام جاذبه: ");
    String name = scanner.nextLine();
    
    System.out.print("ظرفیت جاذبه: ");
    int capacity = scanner.nextInt();
    scanner.nextLine();
    
    System.out.println("نوع جاذبه:");
    System.out.println("1. هیجان‌انگیز");
    System.out.println("2. خانوادگی");
    System.out.println("3. کودکانه");
    System.out.println("4. آبی");
    System.out.print("لطفاً انتخاب کنید: ");
    
    int typeChoice = scanner.nextInt();
    scanner.nextLine();
    
    Ride.RideType type;
    switch(typeChoice) {
        case 1: type = Ride.RideType.THRILL; break;
        case 2: type = Ride.RideType.FAMILY; break;
        case 3: type = Ride.RideType.KIDS; break;
        case 4: type = Ride.RideType.WATER; break;
        default: 
            System.out.println("انتخاب نامعتبر! به طور پیش‌فرض خانوادگی در نظر گرفته می‌شود");
            type = Ride.RideType.FAMILY;
    }
    
    Ride newRide = new Ride(name, capacity, type);
    admin.addRide(newRide);
}

/**
 * حذف یک جاذبه از سیستم
 */
private static void removeExistingRide() {
    listAllRides();
    if(admin.getAllRides().isEmpty()) return;
    
    System.out.print("نام جاذبه برای حذف: ");
    String rideName = scanner.nextLine();
    
    admin.removeRide(rideName);
}

/**
 * نمایش لیست تمام جاذبه‌ها
 */
private static void listAllRides() {
    List<Ride> rides = admin.getAllRides();
    if(rides.isEmpty()) {
        System.out.println("هنوز جاذبه‌ای ثبت نشده است!");
        return;
    }
    
    System.out.println("\nلیست جاذبه‌ها:");
    for(int i = 0; i < rides.size(); i++) {
        Ride r = rides.get(i);
        System.out.printf("%d. %s - نوع: %s - ظرفیت: %d - افراد در صف: %d%n",
            i+1, r.getName(), r.getType(), r.getCapacity(), r.getQueueSize());
    }
}

/**
 * مدیریت برنامه زمانی جاذبه‌ها
 */
private static void manageRideSchedule() {
    listAllRides();
    if(admin.getAllRides().isEmpty()) return;
    
    System.out.print("نام جاذبه برای مدیریت زمان‌بندی: ");
    String rideName = scanner.nextLine();
    
    System.out.println("\n1. نمایش برنامه زمانی");
    System.out.println("2. اضافه کردن زمان جدید");
    System.out.print("لطفاً انتخاب کنید: ");
    
    int choice = scanner.nextInt();
    scanner.nextLine();
    
    if(choice == 1) {
        admin.printSchedule(rideName);
    } else if(choice == 2) {
        System.out.print("زمان جدید (مثال: 5:00 عصر): ");
        String newTime = scanner.nextLine();
        
        for(Ride ride : admin.getAllRides()) {
            if(ride.getName().equalsIgnoreCase(rideName)) {
                ride.addTimeSlot(newTime);
                return;
            }
        }
        System.out.println("جاذبه پیدا نشد!");
    } else {
        System.out.println("گزینه نامعتبر!");
    }
}
/**
 * مدیریت عملیات مربوط به بلیط‌ها
 */
private static void manageTickets() {
    while(true) {
        System.out.println("\n--- مدیریت بلیط‌ها ---");
        System.out.println("1. صدور بلیط جدید");
        System.out.println("2. استفاده از بلیط");
        System.out.println("3. بازگشت به منوی اصلی");
        System.out.print("لطفاً انتخاب کنید: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch(choice) {
            case 1:
                issueNewTicket();
                break;
            case 2:
                useExistingTicket();
                break;
            case 3:
                return;
            default:
                System.out.println("گزینه نامعتبر!");
        }
    }
}

/**
 * صدور بلیط جدید برای بازدیدکننده
 */
private static void issueNewTicket() {
    listAllVisitors();
    if(visitors.isEmpty()) return;
    
    System.out.print("شماره بازدیدکننده: ");
    int visitorIndex = scanner.nextInt() - 1;
    scanner.nextLine();
    
    if(visitorIndex < 0 || visitorIndex >= visitors.size()) {
        System.out.println("شماره بازدیدکننده نامعتبر!");
        return;
    }
    
    listAllRides();
    if(admin.getAllRides().isEmpty()) return;
    
    System.out.print("شماره جاذبه: ");
    int rideIndex = scanner.nextInt() - 1;
    scanner.nextLine();
    
    if(rideIndex < 0 || rideIndex >= admin.getAllRides().size()) {
        System.out.println("شماره جاذبه نامعتبر!");
        return;
    }
    
    Visitor visitor = visitors.get(visitorIndex);
    Ride ride = admin.getAllRides().get(rideIndex);
    
    Ticket ticket = ticketCounter.issueTicket(visitor, ride);
    System.out.println("بلیط با موفقیت صادر شد:");
    System.out.println(ticket);
}

/**
 * ثبت استفاده از بلیط
 */
private static void useExistingTicket() {
    listAllVisitors();
    if(visitors.isEmpty()) return;
    
    System.out.print("شماره بازدیدکننده: ");
    int visitorIndex = scanner.nextInt() - 1;
    scanner.nextLine();
    
    if(visitorIndex < 0 || visitorIndex >= visitors.size()) {
        System.out.println("شماره بازدیدکننده نامعتبر!");
        return;
    }
    
    Visitor visitor = visitors.get(visitorIndex);
    if(visitor.getTickets().isEmpty()) {
        System.out.println("این بازدیدکننده بلیطی ندارد!");
        return;
    }
    
    visitor.showTickets();
    System.out.print("شماره بلیط برای استفاده: ");
    int ticketIndex = scanner.nextInt() - 1;
    scanner.nextLine();
    
    if(ticketIndex >= 0 && ticketIndex < visitor.getTickets().size()) {
        Ticket ticket = visitor.getTickets().get(ticketIndex);
        if(ticket.isUsed()) {
            System.out.println("این بلیط قبلاً استفاده شده است!");
        } else {
            ticket.useTicket();
            System.out.println("استفاده از بلیط با موفقیت ثبت شد!");
        }
    } else {
        System.out.println("شماره بلیط نامعتبر!");
    }
}
/**
 * مدیریت عملیات روزانه پارک
 */
private static void runParkOperations() {
    while(true) {
        System.out.println("\n--- عملیات پارک ---");
        System.out.println("1. اضافه کردن بازدیدکننده به صف جاذبه");
        System.out.println("2. شروع به کار جاذبه");
        System.out.println("3. نمایش وضعیت جاذبه‌ها");
        System.out.println("4. بازگشت به منوی اصلی");
        System.out.print("لطفاً انتخاب کنید: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch(choice) {
            case 1:
                addVisitorToRideQueue();
                break;
            case 2:
                startRideOperation();
                break;
            case 3:
                showRidesStatus();
                break;
            case 4:
                return;
            default:
                System.out.println("گزینه نامعتبر!");
        }
    }
}

/**
 * اضافه کردن بازدیدکننده به صف یک جاذبه
 */
private static void addVisitorToRideQueue() {
    listAllVisitors();
    if(visitors.isEmpty()) return;
    
    System.out.print("شماره بازدیدکننده: ");
    int visitorIndex = scanner.nextInt() - 1;
    scanner.nextLine();
    
    if(visitorIndex < 0 || visitorIndex >= visitors.size()) {
        System.out.println("شماره بازدیدکننده نامعتبر!");
        return;
    }
    
    listAllRides();
    if(admin.getAllRides().isEmpty()) return;
    
    System.out.print("شماره جاذبه: ");
    int rideIndex = scanner.nextInt() - 1;
    scanner.nextLine();
    
    if(rideIndex < 0 || rideIndex >= admin.getAllRides().size()) {
        System.out.println("شماره جاذبه نامعتبر!");
        return;
    }
    
    Visitor visitor = visitors.get(visitorIndex);
    Ride ride = admin.getAllRides().get(rideIndex);
    
    if(visitor instanceof VIPVisitor) {
        ((VIPVisitor)visitor).usePriorityAccess(ride);
    }
    
    ride.addVisitor(visitor);
}

/**
 * شروع به کار یک جاذبه
 */
private static void startRideOperation() {
    listAllRides();
    if(admin.getAllRides().isEmpty()) return;
    
    System.out.print("شماره جاذبه برای شروع: ");
    int rideIndex = scanner.nextInt() - 1;
    scanner.nextLine();
    
    if(rideIndex >= 0 && rideIndex < admin.getAllRides().size()) {
        Ride ride = admin.getAllRides().get(rideIndex);
        ride.startRide();
    } else {
        System.out.println("شماره جاذبه نامعتبر!");
    }
}

/**
 * نمایش وضعیت فعلی جاذبه‌ها
 */
private static void showRidesStatus() {
    List<Ride> rides = admin.getAllRides();
    if(rides.isEmpty()) {
        System.out.println("هیچ جاذبه‌ای ثبت نشده است!");
        return;
    }
    
    System.out.println("\nوضعیت جاذبه‌ها:");
    for(Ride ride : rides) {
        System.out.printf("%s - نوع: %s - ظرفیت: %d - افراد در صف: %d%n",
            ride.getName(), ride.getType(), ride.getCapacity(), ride.getQueueSize());
    }
}

/**
 * نمایش گزارش‌های مدیریتی
 */
private static void viewReports() {
    while(true) {
        System.out.println("\n--- گزارش‌های مدیریتی ---");
        System.out.println("1. گزارش بازدیدکنندگان");
        System.out.println("2. گزارش جاذبه‌ها");
        System.out.println("3. گزارش مالی");
        System.out.println("4. بازگشت به منوی اصلی");
        System.out.print("لطفاً انتخاب کنید: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch(choice) {
            case 1:
                showVisitorsReport();
                break;
            case 2:
                showRidesReport();
                break;
            case 3:
                showFinancialReport();
                break;
            case 4:
                return;
            default:
                System.out.println("گزینه نامعتبر!");
        }
    }
}

/**
 * نمایش گزارش بازدیدکنندگان
 */
private static void showVisitorsReport() {
    System.out.println("\n--- گزارش بازدیدکنندگان ---");
    System.out.println("تعداد کل بازدیدکنندگان: " + visitors.size());
    
    long vipCount = visitors.stream()
                          .filter(v -> v instanceof VIPVisitor)
                          .count();
    
    System.out.println("تعداد بازدیدکنندگان عادی: " + (visitors.size() - vipCount));
    System.out.println("تعداد بازدیدکنندگان VIP: " + vipCount);
}

/**
 * نمایش گزارش جاذبه‌ها
 */
private static void showRidesReport() {
    System.out.println("\n--- گزارش جاذبه‌ها ---");
    List<Ride> rides = admin.getAllRides();
    System.out.println("تعداد کل جاذبه‌ها: " + rides.size());
    
    System.out.println("\nتوزیع انواع جاذبه‌ها:");
    System.out.println("- هیجان‌انگیز: " + 
        rides.stream().filter(r -> r.getType() == Ride.RideType.THRILL).count());
    System.out.println("- خانوادگی: " + 
        rides.stream().filter(r -> r.getType() == Ride.RideType.FAMILY).count());
    System.out.println("- کودکانه: " + 
        rides.stream().filter(r -> r.getType() == Ride.RideType.KIDS).count());
    System.out.println("- آبی: " + 
        rides.stream().filter(r -> r.getType() == Ride.RideType.WATER).count());
    
    System.out.println("\nجاذبه‌های پرترافیک:");
    rides.stream()
         .sorted((r1, r2) -> r2.getQueueSize() - r1.getQueueSize())
         .limit(3)
         .forEach(r -> System.out.printf("%s: %d نفر در صف%n", 
             r.getName(), r.getQueueSize()));
}

/**
 * نمایش گزارش مالی
 */
private static void showFinancialReport() {
    System.out.println("\n--- گزارش مالی ---");
    
    double totalIncome = visitors.stream()
                               .flatMap(v -> v.getTickets().stream())
                               .mapToDouble(Ticket::getPrice)
                               .sum();
    
    System.out.printf("درآمد کل: %.2f تومان%n", totalIncome);
    
    System.out.println("\nدرآمد بر اساس نوع جاذبه:");
    for(Ride.RideType type : Ride.RideType.values()) {
        double typeIncome = visitors.stream()
                                  .flatMap(v -> v.getTickets().stream())
                                  .filter(t -> t.getRide().getType() == type)
                                  .mapToDouble(Ticket::getPrice)
                                  .sum();
        
        System.out.printf("- %s: %.2f تومان%n", type, typeIncome);
    }
    
    System.out.println("\nدرآمد از بازدیدکنندگان VIP:");
    double vipIncome = visitors.stream()
                             .filter(v -> v instanceof VIPVisitor)
                             .flatMap(v -> v.getTickets().stream())
                             .mapToDouble(Ticket::getPrice)
                             .sum();
    
    System.out.printf("درآمد از VIPها: %.2f تومان%n", vipIncome);
    System.out.printf("درآمد از بازدیدکنندگان عادی: %.2f تومان%n", 
        totalIncome - vipIncome);
}
 }

    


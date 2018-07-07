package application;

import company.AutoService;
import company.documents.Bill;
import company.services.Service;
import company.services.categories.Category;
import company.strategies.DiscountStrategy;
import company.strategies.EachBillDiscount;
import company.strategies.InOneBillDiscount;
import company.strategies.OnWeekdayDiscount;
import person.Client;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Application {
  private static AutoService autoService = new AutoService("Auto Servicing by Joshua");

  private static Service partsOrdering = new Service(30000, "Order new transmission", new Category("PARTS ORDERING"));
  private static Service carWashing = new Service(100, "Car washing", new Category("CAR WASHING"));
  private static Service repairingEngine = new Service(30000, "Reringing", new Category("REPAIRING ENGINE"));
  private static Service repairingShaftLine = new Service(15000, "Switch compensating gear", new Category("REPAIRING SHAFT LINE"));
  private static Service shaftLineHealthChecking = new Service(1000, "Shaft line elements diagnostic", new Category("SHAFT LINE HEALTH CHECKING"));
  private static Service engineHealthChecking = new Service(1000, "", new Category("ENGINE HEALTH CHECKING"));
  private static Service fluidChangeout = new Service(300,"Oil changing"  ,new Category("FLUID CHANGEOUT"));
  private static Service detailsChangeout = new Service(2000, "Front brake block changing", new Category("DETAILS CHANGEOUT"));

  private static DiscountStrategy eachThirdDiscount = new EachBillDiscount(3, repairingEngine, 15);
  private static DiscountStrategy onSaturdayDiscount = new OnWeekdayDiscount(DayOfWeek.SATURDAY, fluidChangeout, 100);
  private static DiscountStrategy inOneBillThreeServices = new InOneBillDiscount(3, shaftLineHealthChecking, 50);

  private static List<DiscountStrategy> discounts = new ArrayList<>();
  static {
    discounts.add(eachThirdDiscount);
    discounts.add(onSaturdayDiscount);
    discounts.add(inOneBillThreeServices);
  }

  public static void main(String[] args) {
    eachThirdDiscount.setActive(true);
    onSaturdayDiscount.setActive(true);
    inOneBillThreeServices.setActive(true);

    Client John = new Client("John Williams");

    Bill JohnBill  = autoService.createBillFor(John);
    JohnBill.addService(detailsChangeout);
    JohnBill.addService(fluidChangeout);
    autoService.closeBill(JohnBill, discounts);
    JohnBill.print();

    Bill JohnBillNumberTwo  = autoService.createBillFor(John);
    JohnBillNumberTwo.addService(repairingEngine);
    JohnBillNumberTwo.addService(fluidChangeout);
    JohnBillNumberTwo.addService(shaftLineHealthChecking);
    autoService.closeBill(JohnBillNumberTwo, discounts);
    JohnBillNumberTwo.print();

    Bill JohnBillNumberThree  = autoService.createBillFor(John);
    JohnBillNumberThree.addService(repairingEngine);
    autoService.closeBill(JohnBillNumberThree , discounts);
    JohnBillNumberThree.print();
  }
}

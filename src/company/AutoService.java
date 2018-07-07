package company;

import company.documents.Bill;
import company.documents.Journal;
import company.strategies.DiscountStrategy;
import person.Client;

import java.util.*;

public class AutoService {
  private Journal servicesLog;
  private String name;

  public AutoService(String name) {
    this.name = name;
    this.servicesLog = new Journal();
  }

  public Bill createBillFor(Client client) {
    return new Bill(client);
  }

  public void closeBill(Bill bill, List<DiscountStrategy> discounts) {
    bill.calculateTotalServicesSum();
    applyDiscounts(bill, discounts);
    servicesLog.addToJournal(bill);
  }

  private void applyDiscounts(Bill bill, List<DiscountStrategy> discounts){
    Map<DiscountStrategy, Integer> discountsEffects = new HashMap<>();
    for(DiscountStrategy discount: discounts){
      if(check(bill, discount)){
        Bill clone = bill.clone();
        clone.applyDiscount(discount);
        discountsEffects.put(discount, bill.getTotalPrice() - clone.getTotalPrice());
      }
    }
    if(!discountsEffects.isEmpty()){
      bill.applyDiscount(calculateBestDiscount(discountsEffects));
    }
  }

  private DiscountStrategy calculateBestDiscount(Map<DiscountStrategy, Integer> map){
    Map.Entry<DiscountStrategy, Integer> temp = Collections.max(
            map.entrySet(),
            Comparator.comparing(Map.Entry::getValue)
    );
    return temp.getKey();
  }

  private boolean check(Bill bill, DiscountStrategy discount){
    return bill.getServices().contains(discount.getService()) && discount.isActive() && discount.checkCondition(servicesLog, bill);
  }
}

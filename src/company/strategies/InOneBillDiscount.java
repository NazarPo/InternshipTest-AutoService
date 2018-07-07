package company.strategies;

import company.documents.Bill;
import company.documents.Journal;
import company.services.Service;

public class InOneBillDiscount implements DiscountStrategy {
  private Service service;
  private boolean isActive;
  private int number;
  private int percent;

  public InOneBillDiscount(int number, Service service, int percent) {
    this.number = number;
    this.service = service;
    this.percent = percent;
    this.isActive = false;
  }

  @Override
  public boolean checkCondition(Journal journal, Bill bill){
    return bill.getServices().size() >= getNumber();
  }

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public int getPercent() {
    return percent;
  }

  public void setPercent(int percent) {
    this.percent = percent;
  }

  public int getNumber() {
    return number;
  }
}

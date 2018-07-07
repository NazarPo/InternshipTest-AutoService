package company.strategies;

import company.documents.Bill;
import company.documents.Journal;
import company.services.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class OnWeekdayDiscount implements DiscountStrategy{
  private Service service;
  private boolean isActive;
  private DayOfWeek day;
  private int percent;

  public OnWeekdayDiscount(DayOfWeek dayOfWeek, Service service, int percent){
    this.service = service;
    this.day = dayOfWeek;
    this.percent = percent;
    this.isActive = false;
  }

  @Override
  public boolean checkCondition(Journal journal, Bill bill){
    return LocalDate.now().getDayOfWeek() == getDay();
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

  public DayOfWeek getDay() {
    return day;
  }

  public int getPercent() {
    return percent;
  }

  public void setPercent(int percent) {
    this.percent = percent;
  }
}

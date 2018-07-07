package company.strategies;

import company.documents.Bill;
import company.documents.Journal;
import company.services.Service;

import java.util.List;
import java.util.stream.Collectors;

public class EachBillDiscount implements DiscountStrategy {
  private Service service;
  private boolean isActive;
  private int number;
  private int percent;

  public EachBillDiscount(int number, Service service, int percent){
    this.number = number;
    this.service = service;
    this.percent = percent;
    this.isActive = false;
  }

  @Override
  public boolean checkCondition(Journal journal, Bill bill) {
    List<Bill> filteredJournal = filterLogBy(bill, journal);
    return isStandingBillIn(filteredJournal, getNumber());
  }

  private List<Bill> filterLogBy(Bill bill, Journal journal) {
    return journal.getJournal().stream().filter(b -> b.getClient().equals(bill.getClient())).collect(Collectors.toList());
  }

  private boolean isStandingBillIn(List<Bill> log, int number) {
    return (log.size() != 0) && ((getIndexOfLastElementIn(log) + 1) % number == 0);
  }

  private int getIndexOfLastElementIn(List<Bill> list) {
    return list.indexOf(list.get(list.size() - 1)) + 1;
  }


  public int getNumber() {
    return number;
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
}

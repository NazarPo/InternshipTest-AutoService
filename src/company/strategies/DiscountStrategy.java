package company.strategies;

import company.documents.Bill;
import company.documents.Journal;
import company.services.Service;

public interface DiscountStrategy {
  void setActive(boolean active);
  boolean isActive();
  Service getService();
  int getPercent();
  boolean checkCondition(Journal journal, Bill bill);
}

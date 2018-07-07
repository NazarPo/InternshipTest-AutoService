package company.documents;

import java.util.ArrayList;
import java.util.List;

public class Journal {
  private List<Bill> journal;

  public Journal(){
    journal = new ArrayList<>();
  }

  public void addToJournal(Bill bill) {
    journal.add(bill);
  }

  public void removeFromJournal(Bill bill) {
    journal.remove(bill);
  }

  public List<Bill> getJournal(){
    return journal;
  }
}
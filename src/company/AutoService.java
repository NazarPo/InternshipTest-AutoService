package company;

import company.documents.Journal;

public class AutoService {
  private Journal servicesLog;
  private String name;

  public AutoService(String name) {
    this.name = name;
    this.servicesLog = new Journal();
  }
}

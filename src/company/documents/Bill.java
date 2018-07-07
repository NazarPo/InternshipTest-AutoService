package company.documents;

import company.services.Service;
import person.Client;
import java.util.ArrayList;
import java.util.List;

public class Bill {
  private List<Service> services;
  private Client client;

  public Bill(Client client){
    this.client = client;
    this.services = new ArrayList<>();
  }
}

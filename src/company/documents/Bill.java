package company.documents;

import company.services.Service;
import company.strategies.DiscountStrategy;
import person.Client;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bill {
  private List<Service> services;
  private Client client;
  private int totalPrice;

  public Bill(Client client){
    this.client = client;
    this.services = new ArrayList<>();
  }

  public void addService(Service service){
    services.add(service);
  }

  public Bill clone(){
    Bill clone = new Bill(getClient());
    clone.setServices(getServices());
    clone.setTotalPrice(getTotalPrice());
    return clone;
  }

  public void applyDiscount(DiscountStrategy discount){
    if(discount.getService() == null){
      totalPrice *= (1.0 - discount.getPercent() / 100.0);
    } else {
      totalPrice = 0;
      for(Service service: services) {
        if(service.equals(discount.getService()))
          totalPrice += service.getPrice() * (1.0 - discount.getPercent() / 100.0);
        else
          totalPrice += service.getPrice();
      }
    }
  }

  public void calculateTotalServicesSum(){
    Optional<Integer> sum = this.services.stream().map(Service::getPrice).reduce((price1, price2) -> price1 + price2);
    if(sum.isPresent())
      totalPrice =  sum.get();
    else
      totalPrice =  0;
  }

  public void print() {
    System.out.println("--BILL--");
    System.out.println("--------------------------------------------------------------------------------");
    for (Service s : this.getServices()) {
      System.out.printf("%-70s%10s%n", this.getServices().indexOf(s) + 1 + ". " + s.getName() + "[" + s.getCategory() +"]", s.getPrice() + "$");
    }
    System.out.println("--------------------------------------------------------------------------------");
    System.out.printf("%-70s%10s%n", "TOTAL",  this.getTotalPrice() + " $");
    System.out.println("--------------------------------------------------------------------------------");
    System.out.println("Single-payment cheque to " +this.getClient().getName() + "\n\n");
  }

  public List<Service> getServices(){
    return services;
  }

  public Client getClient(){
    return client;
  }

  public void setServices(List<Service> services) {
    this.services = services;
  }

  public int getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(int totalPrice) {
    this.totalPrice = totalPrice;
  }
}

package company.services;

import company.services.categories.Category;

public class Service {
  private int price;
  private String name;
  private Category category;

  public Service(int price, String name, Category category){
    this.price = price;
    this.name = name;
    this.category = category;
  }

  public int getPrice(){
    return price;
  }

  public String getName(){
    return name;
  }

  public Category getCategory(){
    return category;
  }
}
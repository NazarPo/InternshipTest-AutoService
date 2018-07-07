package company.services.categories;

public class Category {
  String name;
  public Category(String name) {
    this.name = name;
  }

  public String getCategory() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }
}

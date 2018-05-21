import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class CarRentalAgency {
  private HashMap<String, Person> persons = new HashMap<String, Person>();
  private HashMap<String, Car> cars = new HashMap<String, Car>();
  private HashMap<Integer, RentalAgreement> rentalAgreements = 
      new HashMap<Integer, RentalAgreement>();
  private int lastId = 1;

  /**
   * Methode um eine Person hinzuzufügen.
   * 
   * @param name der name der Person
   * @param drivingLicence Fahrerlaubnis der Person als String
   */
  public boolean addPerson(final String name, final String drivingLicence) {
    if (this.persons.containsKey(name)) {
      return false;
    }
    Person p = new Person(name, drivingLicence);
    this.persons.put(name, p);
    return true;
  }

  /**
   * Methode um ein Auto hinzuzufügen.
   * 
   * @param manufacturer hersteller des Autos
   * @param type Model des Autos
   * @return boolean zeigt an ob das Auto hinzugefügt wurde
   */
  public boolean addCar(final String manufacturer, final String type, final String licencePlate,
      final String neededDrivingLicence) {
    if (this.cars.containsKey(licencePlate)) {
      return false;
    }
    this.cars.put(licencePlate, new Car(manufacturer, type, neededDrivingLicence));
    return true;
  }

  /**
   * Methode um zu Prüfen ob die Person die benötigte Fahrerlaubnis hat.
   * 
   * @param carzu prüfendes auto
   * @param person zu pürfende person
   * @return boolean zeigt an ob die benötigte Fahrerlaubnis vorhanden ist
   */
  public boolean checkDrivingLicence(Car car, Person person) {
    if (person.getDrivingLicences().contains(car.getNeededDrivingLicence())) {
      return true;
    }
    return false;
  }

  public int rent(final String name, final String licencePlate, final int days) {
    if (this.cars.get(licencePlate) != null && this.persons.get(name) != null
        && !this.cars.get(licencePlate).isCommissioned()
        && checkDrivingLicence(this.cars.get(licencePlate), this.persons.get(name))) {
      RentalAgreement r = new RentalAgreement(this.lastId + 1, this.cars.get(licencePlate),
          this.persons.get(name));
      this.rentalAgreements.put(this.lastId, r);
      return this.lastId + 1;
    } else if (this.persons.get(name) == null) {
      return -1;
    } else if (this.cars.get(licencePlate) == null) {
      return -2;
    } else if (this.cars.get(licencePlate).isCommissioned()) {
      return -3;
    } else {
      return -4;
    }
  }

  public int rent(final String name, final String licencePlate) {
    return rent(name, licencePlate, 1);
  }

  public boolean returnCar(final int id) {
    if (this.rentalAgreements.containsKey(id)) {
      this.rentalAgreements.get(id).finish();
      return true;
    }
    return false;
  }

  public Set<Integer> nextDay() {
    Set<Integer> s = new HashSet<Integer>();
    for (int i = 0; i < this.rentalAgreements.size(); i++) {
      this.rentalAgreements.get(i).nextDay();
      if (this.rentalAgreements.get(i).isLastday() && this.rentalAgreements.get(i).isActive()) {
        s.add(this.rentalAgreements.get(i).getId());
      }
    }
    return s;
  }

}

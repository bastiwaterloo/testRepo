import java.util.HashSet;

class Person {

  private String name;
  private HashSet<String> drivingLicences;
  private int activeRentals;

  public Person(String name, String drivingLicence) {
    this.name = name;
    this.drivingLicences.add(drivingLicence);
    this.activeRentals = 1;
  }

  public void addActiveRental() {
    this.activeRentals = this.activeRentals + 1;
  }

  public void removeActiveRental() {
    if (this.activeRentals > 0) {
      this.activeRentals = this.activeRentals - 1;
    }
  }

  public void addDrivingLicence(final String drivingLicence) {
    this.drivingLicences.add(drivingLicence);
  }

  public void removeDrivingLicence(final String drivingLicence) {
    this.drivingLicences.remove(drivingLicence);
  }

  public String getName() {
    return name;
  }

  public HashSet<String> getDrivingLicences() {
    return drivingLicences;
  }

  public int getActiveRentals() {
    return activeRentals;
  }
}

class Car {
  private String manufacturer;
  private String type;
  private String licencePlate;
  private String neededDrivingLicence;
  private boolean commissioned = false;

  public Car(String manufacturer, String type, String neededDrivingLicence) {
    this.manufacturer = manufacturer;
    this.type = type;
    this.neededDrivingLicence = neededDrivingLicence;
  }

  public Car(String manufacturer, String type, String licencePlate, String neededDrivingLicence) {
    this.manufacturer = manufacturer;
    this.type = type;
    this.licencePlate = licencePlate;
    this.neededDrivingLicence = neededDrivingLicence;
  }

  public void setLicencePlate(final String licencePlate) {
    this.licencePlate = licencePlate;
  }

  public void commissionIt() {
    this.commissioned = true;
  }

  public void resetCommission() {
    this.commissioned = false;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public String getType() {
    return type;
  }

  public String getLicencePlate() {
    return licencePlate;
  }

  public String getNeededDrivingLicence() {
    return neededDrivingLicence;
  }

  public boolean isCommissioned() {
    return commissioned;
  }

}

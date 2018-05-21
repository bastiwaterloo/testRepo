
class RentalAgreement {
  private int id;
  private Car car;
  private Person person;
  private int duration;
  private boolean lastday;
  private boolean active;

  public RentalAgreement(final int id, final Car car, final Person person) {
    this.id = id;
    this.person = person;
    this.duration = 1;
    setCar(car);
    this.active = true;
    person.addActiveRental();
  }

  public RentalAgreement(final int id, final Car car, final Person person, final int duration) {
    this(id, car, person);
    this.duration = duration;
  }

  public void setCar(final Car car) {
    if (car != null && !car.isCommissioned()) {
      for (String s : this.person.getDrivingLicences()) {
        if (s == car.getNeededDrivingLicence()) {
          this.car = car;
        }
      }
    }
  }

  public boolean nextDay() {
    this.duration = this.duration - 1;
    if (this.duration == 1) {
      this.lastday = true;
    } else if (this.duration < 1) {
      this.lastday = false;
    }
    return this.lastday;
  }

  public void finish() {
    this.active = false;
  }

  public void cancel() {
    finish();
  }

  public int getId() {
    return id;
  }

  public Car getCar() {
    return car;
  }

  public Person getPerson() {
    return person;
  }

  public int getDuration() {
    return duration;
  }

  public boolean isLastday() {
    return lastday;
  }

  public boolean isActive() {
    return active;
  }

}

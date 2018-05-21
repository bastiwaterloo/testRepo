import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class CarRentalAgencyTest {

  private CarRentalAgency agency;

  @Before
  public void init() {
    agency = new CarRentalAgency();
  }

  @Test
  public void addPersonTwice1() {
    assertTrue(agency.addPerson("David Yow", "B"));
    assertFalse(agency.addPerson("David Yow", "B"));
  }

  @Test
  public void addPersonTwice2() {
    assertTrue(agency.addPerson("David Yow", "A"));
    assertFalse(agency.addPerson("David Yow", "B"));
  }

  @Test
  public void addPersonTwoPersons() {
    assertTrue(agency.addPerson("David Yow", "B"));
    assertTrue(agency.addPerson("Jon Spencer", "B"));
  }

  @Test
  public void addCarTwice() {
    assertTrue(agency.addCar("Jeep", "Wrangler", "M UAS 1", "B"));
    assertFalse(agency.addCar("Jeep", "Wrangler", "M UAS 1", "B"));
  }

  @Test
  public void addCarTwoCars() {
    assertTrue(agency.addCar("Jeep", "Wrangler", "M UAS 1", "B"));
    assertTrue(agency.addCar("Jeep", "Wrangler", "M UAS 2", "B"));
  }

  @Test
  public void addCarSamePlate() {
    assertTrue(agency.addCar("Jeep", "Wrangler", "M UAS 1", "B"));
    assertFalse(agency.addCar("Unimog", "", "M UAS 1", "BCE"));
  }

  @Test
  public void rentPersonNotFound() {
    assertEquals(-1, agency.rent("David Yow", "M UAS 1"));
  }

  @Test
  public void rentCarNotFound() {
    agency.addPerson("David Yow", "B");
    assertEquals(-2, agency.rent("David Yow", "M UAS 1"));
  }

  @Test
  public void rentCarAlreadyCommissioned() {
    agency.addPerson("David Yow", "B");
    agency.addCar("Jeep", "Wrangler", "M UAS 1", "B");
    assertEquals(1, agency.rent("David Yow", "M UAS 1"));
    assertEquals(-3, agency.rent("David Yow", "M UAS 1"));
  }

  @Test
  public void rentDrivingLicenceProblem() {
    agency.addPerson("David Yow", "A");
    agency.addCar("Jeep", "Wrangler", "M UAS 1", "B");
    assertEquals(-4, agency.rent("David Yow", "M UAS 1"));
  }

  @Test
  public void rent13Days() {
    agency.addPerson("David Yow", "B");
    agency.addCar("Jeep", "Wrangler", "M UAS 1", "B");
    assertEquals(1, agency.rent("David Yow", "M UAS 1", 13));
  }

  @Test
  public void nextDay1() {
    agency.addPerson("David Yow", "B");
    agency.addPerson("David W. Sims", "B");
    agency.addCar("Jeep", "Wrangler", "M UAS 1", "B");
    agency.addCar("Land Rover", "Defender", "M UAS 2", "B");
    Set<Integer> endingTomorow = new HashSet<>();
    endingTomorow.add(agency.rent("David Yow", "M UAS 1"));
    agency.rent("David W. Sims", "M UAS 2", 2);
    assertEquals(endingTomorow, agency.nextDay());
  }

  @Test
  public void nextDay2() {
    agency.addPerson("David Yow", "B");
    agency.addPerson("David W. Sims", "B");
    agency.addCar("Jeep", "Wrangler", "M UAS 1", "B");
    agency.addCar("Land Rover", "Defender", "M UAS 2", "B");
    Set<Integer> endingTomorow = new HashSet<>();
    endingTomorow.add(agency.rent("David Yow", "M UAS 1"));
    endingTomorow.add(agency.rent("David W. Sims", "M UAS 2"));
    assertEquals(endingTomorow, agency.nextDay());
  }

  @Test
  public void nextDay3() {
    agency.addPerson("David Yow", "B");
    agency.addPerson("David W. Sims", "B");
    agency.addCar("Jeep", "Wrangler", "M UAS 1", "B");
    agency.addCar("Land Rover", "Defender", "M UAS 2", "B");
    Set<Integer> endingInThreeDays = new HashSet<>();
    endingInThreeDays.add(agency.rent("David Yow", "M UAS 1", 3));
    endingInThreeDays.add(agency.rent("David W. Sims", "M UAS 2", 3));
    Set<Integer> emptySet = new HashSet<>();
    assertEquals(emptySet, agency.nextDay());
    assertEquals(emptySet, agency.nextDay());
    assertEquals(endingInThreeDays, agency.nextDay());
  }

  @Test
  public void nextDay4() {
    agency.addPerson("David Yow", "B");
    agency.addPerson("David W. Sims", "B");
    agency.addCar("Jeep", "Wrangler", "M UAS 1", "B");
    agency.addCar("Land Rover", "Defender", "M UAS 2", "B");
    Set<Integer> endingInThreeDays = new HashSet<>();
    endingInThreeDays.add(agency.rent("David Yow", "M UAS 1", 3));
    Set<Integer> endingInFiveDays = new HashSet<>();
    endingInFiveDays.add(agency.rent("David W. Sims", "M UAS 2", 5));
    Set<Integer> emptySet = new HashSet<>();
    assertEquals(emptySet, agency.nextDay());
    assertEquals(emptySet, agency.nextDay());
    assertEquals(endingInThreeDays, agency.nextDay());
    assertEquals(emptySet, agency.nextDay());
    assertEquals(endingInFiveDays, agency.nextDay());
  }

  @Test
  public void nextDay5AndReturn() {
    agency.addPerson("David Yow", "B");
    agency.addPerson("David W. Sims", "B");
    agency.addCar("Jeep", "Wrangler", "M UAS 1", "B");
    agency.addCar("Land Rover", "Defender", "M UAS 2", "B");
    Set<Integer> endingInThreeDays = new HashSet<>();
    endingInThreeDays.add(agency.rent("David Yow", "M UAS 1", 3));
    int raNo = agency.rent("David W. Sims", "M UAS 2", 5);
    Set<Integer> endingInFiveDays = new HashSet<>();
    endingInFiveDays.add(raNo);
    Set<Integer> emptySet = new HashSet<>();
    assertEquals(emptySet, agency.nextDay());
    assertEquals(emptySet, agency.nextDay());
    assertEquals(endingInThreeDays, agency.nextDay());
    assertEquals(emptySet, agency.nextDay());
    assertTrue(agency.returnCar(raNo));
    assertEquals(emptySet, agency.nextDay());
  }
}

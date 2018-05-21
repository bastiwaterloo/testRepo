import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class RentalAgreementTest {

  private Car carA;
  private Car carB;
  private Person personWithB;
  private Person personWithoutB;

  /**
   * Init everything.
   */
  @Before
  public void init() {
    carA = new Car("Land Rover", "Defender", "A");
    carB = new Car("Land Rover", "Defender", "B");
    personWithB = new Person("Elektra Natchios", "B");
    personWithoutB = new Person("Jessica Jones", "A");
  }

  @Test
  public void rentalAgreementConstrAndGetter1() {
    RentalAgreement ra = new RentalAgreement(1, carB, personWithB);
    assertEquals(1, ra.getId());
    assertEquals(carB, ra.getCar());
    assertTrue(ra.getCar().isCommissioned());
    assertEquals(personWithB, ra.getPerson());
    assertEquals(1, ra.getPerson().getActiveRentals());
    assertEquals(1, ra.getDuration());
    assertTrue(ra.isActive());
    assertFalse(ra.isLastday());
  }

  @Test
  public void rentalAgreementConstrAndGetter13() {
    RentalAgreement ra = new RentalAgreement(1, carB, personWithB, 13);
    assertEquals(1, ra.getId());
    assertEquals(carB, ra.getCar());
    assertTrue(ra.getCar().isCommissioned());
    assertEquals(personWithB, ra.getPerson());
    assertEquals(1, ra.getPerson().getActiveRentals());
    assertEquals(13, ra.getDuration());
    assertTrue(ra.isActive());
    assertFalse(ra.isLastday());
  }

  @Test
  public void rentalAgreementConstrNotCorrectLicenceSetCar() {
    RentalAgreement ra = new RentalAgreement(1, carB, personWithoutB);
    assertEquals(1, ra.getId());
    assertNull(ra.getCar());
    assertFalse(carB.isCommissioned());
    assertEquals(personWithoutB, ra.getPerson());
    assertEquals(1, ra.getPerson().getActiveRentals());
    assertEquals(1, ra.getDuration());
    ra.setCar(carA);
    assertEquals(carA, ra.getCar());
    assertTrue(ra.getCar().isCommissioned());
    assertEquals(1, ra.getPerson().getActiveRentals());
  }

  @Test
  public void nextDay1() {
    RentalAgreement ra = new RentalAgreement(1, carB, personWithoutB);
    assertFalse(ra.isLastday());
    assertTrue(ra.isActive());
    assertTrue(ra.nextDay());
    assertTrue(ra.isLastday());
    assertTrue(ra.isActive());
    assertFalse(ra.nextDay());
    assertFalse(ra.isLastday());
  }

  @Test
  public void nextDay13() {
    RentalAgreement ra = new RentalAgreement(1, carB, personWithB, 13);
    for (int i = 0; i < 13; i++) {
      assertFalse(ra.isLastday());
      assertTrue(ra.isActive());
      ra.nextDay();
    }
    assertTrue(ra.isLastday());
    assertTrue(ra.isActive());
  }

  @Test
  public void cancel1() {
    RentalAgreement ra = new RentalAgreement(1, carB, personWithB, 13);
    assertTrue(ra.isActive());
    ra.cancel();
    assertFalse(ra.isActive());
    assertFalse(ra.getCar().isCommissioned());
    assertEquals(0, ra.getPerson().getActiveRentals());
  }

  @Test
  public void cancel2() {
    RentalAgreement ra1 = new RentalAgreement(1, carB, personWithB, 13);
    RentalAgreement ra2 = new RentalAgreement(2, carB, personWithB, 13);
    assertTrue(ra1.isActive());
    assertTrue(ra2.isActive());
    ra1.cancel();
    assertFalse(ra1.isActive());
    assertTrue(ra2.isActive());
    assertFalse(ra1.getCar().isCommissioned());
    assertEquals(1, ra1.getPerson().getActiveRentals());
  }

  @Test
  public void finish1() {
    RentalAgreement ra = new RentalAgreement(1, carB, personWithB, 13);
    assertTrue(ra.isActive());
    ra.finish();
    assertFalse(ra.isActive());
    assertFalse(ra.getCar().isCommissioned());
    assertEquals(0, ra.getPerson().getActiveRentals());
  }

  @Test
  public void finish2() {
    RentalAgreement ra1 = new RentalAgreement(1, carB, personWithB, 13);
    RentalAgreement ra2 = new RentalAgreement(2, carB, personWithB, 13);
    assertTrue(ra1.isActive());
    assertTrue(ra2.isActive());
    ra1.finish();
    assertFalse(ra1.isActive());
    assertTrue(ra2.isActive());
    assertFalse(ra1.getCar().isCommissioned());
    assertEquals(1, ra1.getPerson().getActiveRentals());
  }
}

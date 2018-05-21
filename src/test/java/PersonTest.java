import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class PersonTest {

  @Test
  public void personConstrAndGetter1() {
    Person p = new Person("Berta", "A");
    assertEquals("Berta", p.getName());
    Set<String> expectedSet = new HashSet<>();
    expectedSet.add("A");
    assertEquals(expectedSet, p.getDrivingLicences());
    assertEquals(0, p.getActiveRentals());
  }

  @Test
  public void personConstrAndGetter2() {
    Person p = new Person("Anton", "B");
    assertEquals("Anton", p.getName());
    Set<String> expectedSet = new HashSet<>();
    expectedSet.add("B");
    assertEquals(expectedSet, p.getDrivingLicences());
    assertEquals(0, p.getActiveRentals());
  }

  @Test
  public void addDrivingLicence() {
    Person p = new Person("Berta", "A");
    p.addDrivingLicence("F");
    assertEquals("Berta", p.getName());
    Set<String> expectedSet = new HashSet<>();
    expectedSet.add("A");
    expectedSet.add("F");
    assertEquals(expectedSet, p.getDrivingLicences());
    assertEquals(0, p.getActiveRentals());
  }

  @Test
  public void removeDrivingLicence1() {
    Person p = new Person("Berta", "A");
    p.removeDrivingLicence("A");
    assertEquals("Berta", p.getName());
    Set<String> expectedSet = new HashSet<>();
    assertEquals(expectedSet, p.getDrivingLicences());
    assertEquals(0, p.getActiveRentals());
  }

  @Test
  public void removeDrivingLicence2() {
    Person p = new Person("Berta", "A");
    p.addDrivingLicence("F");
    p.removeDrivingLicence("A");
    assertEquals("Berta", p.getName());
    Set<String> expectedSet = new HashSet<>();
    expectedSet.add("F");
    assertEquals(expectedSet, p.getDrivingLicences());
    assertEquals(0, p.getActiveRentals());
  }

  @Test
  public void addActiceRentals() {
    Person p = new Person("Berta", "A");
    for (int i = 0; i < 100; i++) {
      assertEquals(i, p.getActiveRentals());
      p.addActiveRental();
    }
  }

  @Test
  public void addAndRemoveActiceRentals1() {
    Person p = new Person("Berta", "A");
    for (int i = 0; i < 100; i++) {
      assertEquals(i, p.getActiveRentals());
      p.addActiveRental();
    }
    for (int i = 100; i > 0; i--) {
      assertEquals(i, p.getActiveRentals());
      p.removeActiveRental();
    }
  }

  @Test
  public void addAndRemoveActiceRentals2() {
    Person p = new Person("Berta", "A");
    for (int i = 0; i < 100; i++) {
      assertEquals(0, p.getActiveRentals());
      p.addActiveRental();
      p.removeActiveRental();
    }
  }

  @Test
  public void removeActiceRentalsNotNegative() {
    Person p = new Person("Berta", "A");
    for (int i = 0; i < 100; i++) {
      assertEquals(0, p.getActiveRentals());
      p.removeActiveRental();
    }
  }

}

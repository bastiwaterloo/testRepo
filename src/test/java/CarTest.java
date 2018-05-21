import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class CarTest {

  @Test
  public void carConstrAndGetter1() {
    Car c = new Car("Land Rover", "Defender", "M UAS 1", "B");
    assertEquals("Land Rover", c.getManufacturer());
    assertEquals("Defender", c.getType());
    assertEquals("M UAS 1", c.getLicencePlate());
    assertEquals("B", c.getNeededDrivingLicence());
    assertFalse(c.isCommissioned());
  }

  @Test
  public void carConstrAndGetter2() {
    Car c = new Car("Land Rover", "Defender", "B");
    assertEquals("Land Rover", c.getManufacturer());
    assertEquals("Defender", c.getType());
    assertNull(c.getLicencePlate());
    assertEquals("B", c.getNeededDrivingLicence());
    assertFalse(c.isCommissioned());
  }

  @Test
  public void setLicencePlate() {
    Car c = new Car("Land Rover", "Defender", "B");
    c.setLicencePlate("M UAS 1");
    assertEquals("M UAS 1", c.getLicencePlate());
  }

  @Test
  public void commissionIt() {
    Car c = new Car("Land Rover", "Defender", "B");
    c.commissionIt();
    assertTrue(c.isCommissioned());
  }

  @Test
  public void commissionItAndReset() {
    Car c = new Car("Land Rover", "Defender", "B");
    c.commissionIt();
    assertTrue(c.isCommissioned());
    c.resetCommission();
    assertFalse(c.isCommissioned());
  }

}

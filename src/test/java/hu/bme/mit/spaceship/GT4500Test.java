package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;

  private TorpedoStore mockStore1;
  private TorpedoStore mockStore2;

  @BeforeEach
  public void init(){
    mockStore1 = mock(TorpedoStore.class);
    mockStore2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mockStore1, mockStore2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockStore1.fire(1)).thenReturn(true);
    when(mockStore2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    verify(mockStore1, times(1)).fire(1);
    verify(mockStore2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockStore1.fire(1)).thenReturn(true);
    when(mockStore2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);

    verify(mockStore1, times(1)).fire(1);
    verify(mockStore2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Param() {
    ship.fireTorpedo(FiringMode.SINGLE);

    InOrder inOrder = Mockito.inOrder(mockStore1);
    inOrder.verify(mockStore1).isEmpty();
    inOrder.verify(mockStore1).fire(1);
  }

  @Test
  public void fireTorpedo_All_Param() {
    ship.fireTorpedo(FiringMode.ALL);

    InOrder inOrder1 = Mockito.inOrder(mockStore1);
    inOrder1.verify(mockStore1).isEmpty();
    inOrder1.verify(mockStore1).fire(1);

    InOrder inOrder2 = Mockito.inOrder(mockStore2);
    inOrder2.verify(mockStore2).isEmpty();
    inOrder2.verify(mockStore2).fire(1);
  }

  @Test
  public void fireTorpedo_Single_With_Param() {
    ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockStore1).fire(1);
  }

  @Test
  public void fireTorpedo_All_With_Param() {
    ship.fireTorpedo(FiringMode.ALL);

    verify(mockStore1).fire(1);
    verify(mockStore2).fire(1);
  }

  @Test
  public void fireTorpedo_Single() {
    ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockStore1).isEmpty();
    verify(mockStore2, never()).isEmpty();
  }

  @Test
  public void fireTorpedo_All() {
    ship.fireTorpedo(FiringMode.ALL);

    verify(mockStore1).isEmpty();
    verify(mockStore2).isEmpty();
  }

}

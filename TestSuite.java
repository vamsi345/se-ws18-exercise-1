import TinyTestJ.Test;
import TinyTestJ.RunTests;

public class TestSuite {

  @Test public static void ImageTest1() {
    Image i = new Image(100,100);
    assert (i.data.length == 30000);
  }

  @Test public static void ImageTest2() {
    Image i = new Image(100,100);
    i.set(0, 0,0x123456);
    assert (i.data[0] == (byte)0x12);
    assert (i.data[1] == (byte)0x34);
    assert (i.data[2] == (byte)0x56);
    assert (i.data[3] == (byte)0x00);
  }

  @Test public static void ImageTest3() {
    Image i = new Image(100,100);
    i.set(99,99,0x123456);
    int len = i.data.length;
    assert (i.data.length == 30000);
    assert (i.data[99 * 100 * 3 + 99 * 3] == (byte)0x12);
    assert (i.data[99 * 100 * 3 + 99 * 3 + 1] == (byte)0x34);
    assert (i.data[99 * 100 * 3 + 99 * 3 + 2] == (byte)0x56);
  }

  @Test public static void ImageTest4() throws java.io.FileNotFoundException,java.io.IOException {
    String filename = "test.ppm";
    Image i = new Image(100,100);
    // for visual test we create a red diagonal
    i.set(99,99,0xff00000);
    i.set(98,98,0xff00000);
    i.set(97,97,0xff00000);
    i.set(96,96,0xff00000);
    i.set(95,95,0xff00000);
    i.set(94,94,0xff00000);
    i.set(93,93,0xff00000);
    i.set(92,92,0xff00000);
    i.set(91,91,0xff00000);
    i.set(90,90,0xff00000);
    i.write(filename);
    java.io.File f = new java.io.File(filename);
    boolean exists = f.exists() && f.isFile();
    assert (exists);
  }

}

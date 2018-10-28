import java.io.IOException;
import java.io.FileOutputStream;

public class Image {
    int _width;
    int _height;

    public byte [] data;

    public Image(int width, int height) {
        _width = width;
        _height = height;
        // width*height*3 for image pixels
        data = new byte [width*3*height];
    }

    public void set(int x, int y, int val) {
        // this is the formula to get location in the image buffer
        data[x*_width*3 + y*3] = (byte)(val >> 16); // R bits
        data[x*_width*3 + y*3 + 1] = (byte)((val >> 8 ) & 0xff); // G bits
        data[x*_width*3 + y*3 + 2] = (byte)(val & 0xff); // B bits
    }

    public void write(String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            // P6 is ppm magic number as characters
            fos.write('P');
            fos.write('6');
            fos.write(' '); // a whitespace
            String width_str = Integer.toString(_width);
            int l = width_str.length();

            for(int i = 0; i < l; i++) {
                fos.write(Character.getNumericValue(width_str.charAt(i)) + 48); // string as ascii decimal
            }

            fos.write(' '); // a whitespace
            String height_str = Integer.toString(_height);
            l = height_str.length();

            for(int i = 0; i < l; i++) {
                fos.write(Character.getNumericValue(width_str.charAt(i)) + 48); // string as ascii decimal
            }

            fos.write(' '); // a whitespace
            fos.write(50); // max color value 255 as ascii decimal
            fos.write(53);
            fos.write(53);
            fos.write('\n'); // a whitespace which is usually newline
            fos.write(data);
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    String[] array = new String[2];
    int index = 0;
    String r = "\r";
    String n = "\n";
    if (lines.indexOf(r) != -1) {
      if (lines.indexOf(r + n) != -1) {
        index = lines.indexOf(r + n);
        array[0] = lines.substring(0, index + 2);
        array[1] = lines.substring(index + 2);
      }else{
        index = lines.indexOf(r);
        array[0] = lines.substring(0, index + 1);
        array[1] = lines.substring(index + 1);
      }
    } else if (lines.indexOf(n) != -1) {
      index = lines.indexOf(n);
      array[0] = lines.substring(0, index + 1);
      array[1] = lines.substring(index + 1);
    } else {
      array[0] = "";
      array[1] = lines;
    }
    return array;
  }
}

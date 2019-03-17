package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  //we want to be counting lines to label them, there's 1 line at least
  //we initialize each line
  //we want to know wether we've already encountered a new line
  private int lineCounter;
  private int lastEncounteredChar = 0;
  private boolean hasEncounteredFirstNewLine = false;


  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    for(int i = off; i < off + len; ++i){
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    //no need to add the checks wether we have encountered a new line, since there's only 1 character
    if(!hasEncounteredFirstNewLine){
      //first encountered lign, writing that
      super.write(  lineCounter++ + '\t');
      hasEncounteredFirstNewLine = true;
    }else if(lastEncounteredChar == '\r' && c != '\n'){
      super.write(  lineCounter++ + '\t');
    }
    //writing whatever char
    super.write(c);

    if(c == '\n'){
      //before the next char, we'll have to indicate the lign
      super.write(  lineCounter++ + '\t');
    }
    //remembering the char
    lastEncounteredChar = c;
  }

}

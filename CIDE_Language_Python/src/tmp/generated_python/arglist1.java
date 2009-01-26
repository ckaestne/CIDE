package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class arglist1 extends arglist {
  public arglist1(normalargs normalargs, arglist1End arglist1End, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<normalargs>("normalargs", normalargs),
      new PropertyZeroOrOne<arglist1End>("arglist1End", arglist1End)
    }, firstToken, lastToken);
  }
  public arglist1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new arglist1(cloneProperties(),firstToken,lastToken);
  }
  public normalargs getNormalargs() {
    return ((PropertyOne<normalargs>)getProperty("normalargs")).getValue();
  }
  public arglist1End getArglist1End() {
    return ((PropertyZeroOrOne<arglist1End>)getProperty("arglist1End")).getValue();
  }
}

package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class arglist2 extends arglist {
  public arglist2(arglist1EndEnd arglist1EndEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<arglist1EndEnd>("arglist1EndEnd", arglist1EndEnd)
    }, firstToken, lastToken);
  }
  public arglist2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new arglist2(cloneProperties(),firstToken,lastToken);
  }
  public arglist1EndEnd getArglist1EndEnd() {
    return ((PropertyZeroOrOne<arglist1EndEnd>)getProperty("arglist1EndEnd")).getValue();
  }
}

package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class atomtrailerEnd2 extends atomtrailerEnd {
  public atomtrailerEnd2(arglist arglist, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<arglist>("arglist", arglist)
    }, firstToken, lastToken);
  }
  public atomtrailerEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new atomtrailerEnd2(cloneProperties(),firstToken,lastToken);
  }
  public arglist getArglist() {
    return ((PropertyOne<arglist>)getProperty("arglist")).getValue();
  }
}

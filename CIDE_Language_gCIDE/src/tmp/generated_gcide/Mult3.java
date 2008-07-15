package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Mult3 extends Mult {
  public Mult3(Wrappee wrappee, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Wrappee>("wrappee", wrappee)
    }, firstToken, lastToken);
  }
  public Mult3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Mult3(cloneProperties(),firstToken,lastToken);
  }
  public Wrappee getWrappee() {
    return ((PropertyOne<Wrappee>)getProperty("wrappee")).getValue();
  }
}

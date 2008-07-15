package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Mult4 extends Mult {
  public Mult4(OneOrZero oneOrZero, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<OneOrZero>("oneOrZero", oneOrZero)
    }, firstToken, lastToken);
  }
  public Mult4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Mult4(cloneProperties(),firstToken,lastToken);
  }
  public OneOrZero getOneOrZero() {
    return ((PropertyOne<OneOrZero>)getProperty("oneOrZero")).getValue();
  }
}

package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class type1 extends type {
  public type1(naam naam, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<naam>("naam", naam)
    }, firstToken, lastToken);
  }
  public type1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new type1(cloneProperties(),firstToken,lastToken);
  }
  public naam getNaam() {
    return ((PropertyOne<naam>)getProperty("naam")).getValue();
  }
}

package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Unit2 extends Unit {
  public Unit2(Ident ident, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Ident>("ident", ident)
    }, firstToken, lastToken);
  }
  public Unit2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Unit2(cloneProperties(),firstToken,lastToken);
  }
  public Ident getIdent() {
    return ((PropertyOne<Ident>)getProperty("ident")).getValue();
  }
}

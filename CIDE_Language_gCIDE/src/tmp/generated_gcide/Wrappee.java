package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Wrappee extends GenASTNode {
  public Wrappee(Unit unit, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Unit>("unit", unit)
    }, firstToken, lastToken);
  }
  public Wrappee(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Wrappee(cloneProperties(),firstToken,lastToken);
  }
  public Unit getUnit() {
    return ((PropertyOne<Unit>)getProperty("unit")).getValue();
  }
}

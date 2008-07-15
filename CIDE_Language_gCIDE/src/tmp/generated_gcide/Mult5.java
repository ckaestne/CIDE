package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Mult5 extends Mult {
  public Mult5(Unit unit, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Unit>("unit", unit)
    }, firstToken, lastToken);
  }
  public Mult5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Mult5(cloneProperties(),firstToken,lastToken);
  }
  public Unit getUnit() {
    return ((PropertyOne<Unit>)getProperty("unit")).getValue();
  }
}

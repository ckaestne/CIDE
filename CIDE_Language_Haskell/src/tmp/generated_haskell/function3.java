package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class function3 extends function {
  public function3(patroon patroon3, operator operator, patr patr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<patroon>("patroon3", patroon3),
      new PropertyOne<operator>("operator", operator),
      new PropertyOne<patr>("patr", patr)
    }, firstToken, lastToken);
  }
  public function3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new function3(cloneProperties(),firstToken,lastToken);
  }
  public patroon getPatroon3() {
    return ((PropertyOne<patroon>)getProperty("patroon3")).getValue();
  }
  public operator getOperator() {
    return ((PropertyOne<operator>)getProperty("operator")).getValue();
  }
  public patr getPatr() {
    return ((PropertyOne<patr>)getProperty("patr")).getValue();
  }
}

package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class function3 extends function {
  public function3(patroon patroon2, operator operator, patr patr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<patroon>("patroon2", patroon2),
      new PropertyOne<operator>("operator", operator),
      new PropertyOne<patr>("patr", patr)
    }, firstToken, lastToken);
  }
  public function3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new function3(cloneProperties(),firstToken,lastToken);
  }
  public patroon getPatroon2() {
    return ((PropertyOne<patroon>)getProperty("patroon2")).getValue();
  }
  public operator getOperator() {
    return ((PropertyOne<operator>)getProperty("operator")).getValue();
  }
  public patr getPatr() {
    return ((PropertyOne<patr>)getProperty("patr")).getValue();
  }
}

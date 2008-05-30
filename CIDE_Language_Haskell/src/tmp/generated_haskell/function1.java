package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class function1 extends function {
  public function1(var var, ArrayList<patroon> patroon, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<var>("var", var),
      new PropertyOneOrMore<patroon>("patroon", patroon)
    }, firstToken, lastToken);
  }
  public function1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new function1(cloneProperties(),firstToken,lastToken);
  }
  public var getVar() {
    return ((PropertyOne<var>)getProperty("var")).getValue();
  }
  public ArrayList<patroon> getPatroon() {
    return ((PropertyOneOrMore<patroon>)getProperty("patroon")).getValue();
  }
}

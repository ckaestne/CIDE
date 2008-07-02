package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class function2 extends function {
  public function2(function function, ArrayList<patroon> patroon2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<function>("function", function),
      new PropertyOneOrMore<patroon>("patroon2", patroon2)
    }, firstToken, lastToken);
  }
  public function2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new function2(cloneProperties(),firstToken,lastToken);
  }
  public function getFunction() {
    return ((PropertyOne<function>)getProperty("function")).getValue();
  }
  public ArrayList<patroon> getPatroon2() {
    return ((PropertyOneOrMore<patroon>)getProperty("patroon2")).getValue();
  }
}

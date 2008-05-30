package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class function2 extends function {
  public function2(function function, ArrayList<patroon> patroon1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<function>("function", function),
      new PropertyOneOrMore<patroon>("patroon1", patroon1)
    }, firstToken, lastToken);
  }
  public function2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new function2(cloneProperties(),firstToken,lastToken);
  }
  public function getFunction() {
    return ((PropertyOne<function>)getProperty("function")).getValue();
  }
  public ArrayList<patroon> getPatroon1() {
    return ((PropertyOneOrMore<patroon>)getProperty("patroon1")).getValue();
  }
}

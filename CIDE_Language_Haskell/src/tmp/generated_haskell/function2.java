package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyOneOrMore;
import cide.gparser.Token;

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

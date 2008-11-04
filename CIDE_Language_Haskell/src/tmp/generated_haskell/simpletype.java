package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class simpletype extends GenASTNode {
  public simpletype(naam naam, ArrayList<var> var, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<naam>("naam", naam),
      new PropertyZeroOrMore<var>("var", var)
    }, firstToken, lastToken);
  }
  public simpletype(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new simpletype(cloneProperties(),firstToken,lastToken);
  }
  public naam getNaam() {
    return ((PropertyOne<naam>)getProperty("naam")).getValue();
  }
  public ArrayList<var> getVar() {
    return ((PropertyZeroOrMore<var>)getProperty("var")).getValue();
  }
}

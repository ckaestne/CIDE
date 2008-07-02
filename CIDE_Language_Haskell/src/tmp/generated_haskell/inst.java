package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class inst extends GenASTNode {
  public inst(naam naam, ArrayList<type> type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<naam>("naam", naam),
      new PropertyOneOrMore<type>("type", type)
    }, firstToken, lastToken);
  }
  public inst(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new inst(cloneProperties(),firstToken,lastToken);
  }
  public naam getNaam() {
    return ((PropertyOne<naam>)getProperty("naam")).getValue();
  }
  public ArrayList<type> getType() {
    return ((PropertyOneOrMore<type>)getProperty("type")).getValue();
  }
}

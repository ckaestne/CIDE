package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyOneOrMore;
import cide.gparser.Token;

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

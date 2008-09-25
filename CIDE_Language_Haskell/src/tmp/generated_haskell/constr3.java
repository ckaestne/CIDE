package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrMore;
import cide.gparser.Token;

public class constr3 extends constr {
  public constr3(naam naam1, ArrayList<type> type2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<naam>("naam1", naam1),
      new PropertyZeroOrMore<type>("type2", type2)
    }, firstToken, lastToken);
  }
  public constr3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new constr3(cloneProperties(),firstToken,lastToken);
  }
  public naam getNaam1() {
    return ((PropertyOne<naam>)getProperty("naam1")).getValue();
  }
  public ArrayList<type> getType2() {
    return ((PropertyZeroOrMore<type>)getProperty("type2")).getValue();
  }
}

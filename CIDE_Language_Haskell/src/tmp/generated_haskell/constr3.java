package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

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

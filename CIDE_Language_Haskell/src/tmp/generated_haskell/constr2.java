package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class constr2 extends constr {
  public constr2(ArrayList<type> type, operator operator, ArrayList<type> type1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<type>("type", type),
      new PropertyOne<operator>("operator", operator),
      new PropertyOneOrMore<type>("type1", type1)
    }, firstToken, lastToken);
  }
  public constr2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new constr2(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<type> getType() {
    return ((PropertyOneOrMore<type>)getProperty("type")).getValue();
  }
  public operator getOperator() {
    return ((PropertyOne<operator>)getProperty("operator")).getValue();
  }
  public ArrayList<type> getType1() {
    return ((PropertyOneOrMore<type>)getProperty("type1")).getValue();
  }
}

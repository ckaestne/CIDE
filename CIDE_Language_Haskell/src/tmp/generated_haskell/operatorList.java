package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class operatorList extends GenASTNode {
  public operatorList(ArrayList<operator> operator, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<operator>("operator", operator)
    }, firstToken, lastToken);
  }
  public operatorList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new operatorList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<operator> getOperator() {
    return ((PropertyList<operator>)getProperty("operator")).getValue();
  }
}

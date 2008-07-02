package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class declaration7 extends declaration {
  public declaration7(ASTStringNode integer1, operatorList operatorList1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTStringNode>("integer1", integer1),
      new PropertyOne<operatorList>("operatorList1", operatorList1)
    }, firstToken, lastToken);
  }
  public declaration7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new declaration7(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getInteger1() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("integer1")).getValue();
  }
  public operatorList getOperatorList1() {
    return ((PropertyOne<operatorList>)getProperty("operatorList1")).getValue();
  }
}

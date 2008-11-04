package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class declaration8 extends declaration {
  public declaration8(ASTStringNode integer2, operatorList operatorList2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTStringNode>("integer2", integer2),
      new PropertyOne<operatorList>("operatorList2", operatorList2)
    }, firstToken, lastToken);
  }
  public declaration8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new declaration8(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getInteger2() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("integer2")).getValue();
  }
  public operatorList getOperatorList2() {
    return ((PropertyOne<operatorList>)getProperty("operatorList2")).getValue();
  }
}

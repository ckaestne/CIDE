package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class declaration6 extends declaration {
  public declaration6(ASTStringNode integer, operatorList operatorList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTStringNode>("integer", integer),
      new PropertyOne<operatorList>("operatorList", operatorList)
    }, firstToken, lastToken);
  }
  public declaration6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new declaration6(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getInteger() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("integer")).getValue();
  }
  public operatorList getOperatorList() {
    return ((PropertyOne<operatorList>)getProperty("operatorList")).getValue();
  }
}

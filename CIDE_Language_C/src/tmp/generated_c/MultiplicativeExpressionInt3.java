package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MultiplicativeExpressionInt3 extends MultiplicativeExpressionInt {
  public MultiplicativeExpressionInt3(MultiplicativeExpression multiplicativeExpression2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MultiplicativeExpression>("multiplicativeExpression2", multiplicativeExpression2)
    }, firstToken, lastToken);
  }
  public MultiplicativeExpressionInt3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MultiplicativeExpressionInt3(cloneProperties(),firstToken,lastToken);
  }
  public MultiplicativeExpression getMultiplicativeExpression2() {
    return ((PropertyOne<MultiplicativeExpression>)getProperty("multiplicativeExpression2")).getValue();
  }
}

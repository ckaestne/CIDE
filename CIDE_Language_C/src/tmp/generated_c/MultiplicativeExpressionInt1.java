package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MultiplicativeExpressionInt1 extends MultiplicativeExpressionInt {
  public MultiplicativeExpressionInt1(MultiplicativeExpression multiplicativeExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MultiplicativeExpression>("multiplicativeExpression", multiplicativeExpression)
    }, firstToken, lastToken);
  }
  public MultiplicativeExpressionInt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MultiplicativeExpressionInt1(cloneProperties(),firstToken,lastToken);
  }
  public MultiplicativeExpression getMultiplicativeExpression() {
    return ((PropertyOne<MultiplicativeExpression>)getProperty("multiplicativeExpression")).getValue();
  }
}

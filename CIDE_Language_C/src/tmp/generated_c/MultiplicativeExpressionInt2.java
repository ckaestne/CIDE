package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MultiplicativeExpressionInt2 extends MultiplicativeExpressionInt {
  public MultiplicativeExpressionInt2(MultiplicativeExpression multiplicativeExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MultiplicativeExpression>("multiplicativeExpression1", multiplicativeExpression1)
    }, firstToken, lastToken);
  }
  public MultiplicativeExpressionInt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MultiplicativeExpressionInt2(cloneProperties(),firstToken,lastToken);
  }
  public MultiplicativeExpression getMultiplicativeExpression1() {
    return ((PropertyOne<MultiplicativeExpression>)getProperty("multiplicativeExpression1")).getValue();
  }
}

package tmp.generated_simple;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Expression1 extends Expression {
  public Expression1(BinaryExpression binaryExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BinaryExpression>("binaryExpression", binaryExpression)
    }, firstToken, lastToken);
  }
  public Expression1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Expression1(cloneProperties(),firstToken,lastToken);
  }
  public BinaryExpression getBinaryExpression() {
    return ((PropertyOne<BinaryExpression>)getProperty("binaryExpression")).getValue();
  }
}

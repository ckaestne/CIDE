package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimaryExpression5 extends PrimaryExpression {
  public PrimaryExpression5(ArrayLiteral arrayLiteral, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ArrayLiteral>("arrayLiteral", arrayLiteral)
    }, firstToken, lastToken);
  }
  public PrimaryExpression5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimaryExpression5(cloneProperties(),firstToken,lastToken);
  }
  public ArrayLiteral getArrayLiteral() {
    return ((PropertyOne<ArrayLiteral>)getProperty("arrayLiteral")).getValue();
  }
}

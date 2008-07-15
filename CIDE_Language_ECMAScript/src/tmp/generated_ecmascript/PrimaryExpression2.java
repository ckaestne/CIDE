package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimaryExpression2 extends PrimaryExpression {
  public PrimaryExpression2(ObjectLiteral objectLiteral, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ObjectLiteral>("objectLiteral", objectLiteral)
    }, firstToken, lastToken);
  }
  public PrimaryExpression2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimaryExpression2(cloneProperties(),firstToken,lastToken);
  }
  public ObjectLiteral getObjectLiteral() {
    return ((PropertyOne<ObjectLiteral>)getProperty("objectLiteral")).getValue();
  }
}

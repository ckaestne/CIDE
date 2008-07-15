package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimaryExpression6 extends PrimaryExpression {
  public PrimaryExpression6(Literal literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Literal>("literal", literal)
    }, firstToken, lastToken);
  }
  public PrimaryExpression6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimaryExpression6(cloneProperties(),firstToken,lastToken);
  }
  public Literal getLiteral() {
    return ((PropertyOne<Literal>)getProperty("literal")).getValue();
  }
}

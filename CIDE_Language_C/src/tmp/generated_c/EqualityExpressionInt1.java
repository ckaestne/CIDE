package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EqualityExpressionInt1 extends EqualityExpressionInt {
  public EqualityExpressionInt1(ASTStringNode eqeq, EqualityExpression equalityExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("eqeq", eqeq),
      new PropertyOne<EqualityExpression>("equalityExpression", equalityExpression)
    }, firstToken, lastToken);
  }
  public EqualityExpressionInt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EqualityExpressionInt1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getEqeq() {
    return ((PropertyOne<ASTStringNode>)getProperty("eqeq")).getValue();
  }
  public EqualityExpression getEqualityExpression() {
    return ((PropertyOne<EqualityExpression>)getProperty("equalityExpression")).getValue();
  }
}

package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimaryExpression6 extends PrimaryExpression {
  public PrimaryExpression6(CastExpression castExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<CastExpression>("castExpression", castExpression)
    }, firstToken, lastToken);
  }
  public PrimaryExpression6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimaryExpression6(cloneProperties(),firstToken,lastToken);
  }
  public CastExpression getCastExpression() {
    return ((PropertyOne<CastExpression>)getProperty("castExpression")).getValue();
  }
}

package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AdditiveExpressionInt1 extends AdditiveExpressionInt {
  public AdditiveExpressionInt1(AdditiveExpression additiveExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AdditiveExpression>("additiveExpression", additiveExpression)
    }, firstToken, lastToken);
  }
  public AdditiveExpressionInt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AdditiveExpressionInt1(cloneProperties(),firstToken,lastToken);
  }
  public AdditiveExpression getAdditiveExpression() {
    return ((PropertyOne<AdditiveExpression>)getProperty("additiveExpression")).getValue();
  }
}

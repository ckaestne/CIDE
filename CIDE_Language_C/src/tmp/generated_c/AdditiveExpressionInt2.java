package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AdditiveExpressionInt2 extends AdditiveExpressionInt {
  public AdditiveExpressionInt2(AdditiveExpression additiveExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AdditiveExpression>("additiveExpression1", additiveExpression1)
    }, firstToken, lastToken);
  }
  public AdditiveExpressionInt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AdditiveExpressionInt2(cloneProperties(),firstToken,lastToken);
  }
  public AdditiveExpression getAdditiveExpression1() {
    return ((PropertyOne<AdditiveExpression>)getProperty("additiveExpression1")).getValue();
  }
}

package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AdditiveExpressionEnd extends GenASTNode {
  public AdditiveExpressionEnd(AdditiveOperator additiveOperator, MultiplicativeExpression multiplicativeExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AdditiveOperator>("additiveOperator", additiveOperator),
      new PropertyOne<MultiplicativeExpression>("multiplicativeExpression", multiplicativeExpression)
    }, firstToken, lastToken);
  }
  public AdditiveExpressionEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AdditiveExpressionEnd(cloneProperties(),firstToken,lastToken);
  }
  public AdditiveOperator getAdditiveOperator() {
    return ((PropertyOne<AdditiveOperator>)getProperty("additiveOperator")).getValue();
  }
  public MultiplicativeExpression getMultiplicativeExpression() {
    return ((PropertyOne<MultiplicativeExpression>)getProperty("multiplicativeExpression")).getValue();
  }
}

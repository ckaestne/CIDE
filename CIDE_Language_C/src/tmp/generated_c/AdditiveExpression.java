package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AdditiveExpression extends GenASTNode {
  public AdditiveExpression(MultiplicativeExpression multiplicativeExpression, AdditiveExpressionInt additiveExpressionInt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MultiplicativeExpression>("multiplicativeExpression", multiplicativeExpression),
      new PropertyZeroOrOne<AdditiveExpressionInt>("additiveExpressionInt", additiveExpressionInt)
    }, firstToken, lastToken);
  }
  public AdditiveExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AdditiveExpression(cloneProperties(),firstToken,lastToken);
  }
  public MultiplicativeExpression getMultiplicativeExpression() {
    return ((PropertyOne<MultiplicativeExpression>)getProperty("multiplicativeExpression")).getValue();
  }
  public AdditiveExpressionInt getAdditiveExpressionInt() {
    return ((PropertyZeroOrOne<AdditiveExpressionInt>)getProperty("additiveExpressionInt")).getValue();
  }
}

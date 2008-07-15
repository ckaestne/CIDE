package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EqualityExpression extends GenASTNode {
  public EqualityExpression(RelationalExpression relationalExpression, EqualityExpressionInt equalityExpressionInt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<RelationalExpression>("relationalExpression", relationalExpression),
      new PropertyZeroOrOne<EqualityExpressionInt>("equalityExpressionInt", equalityExpressionInt)
    }, firstToken, lastToken);
  }
  public EqualityExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EqualityExpression(cloneProperties(),firstToken,lastToken);
  }
  public RelationalExpression getRelationalExpression() {
    return ((PropertyOne<RelationalExpression>)getProperty("relationalExpression")).getValue();
  }
  public EqualityExpressionInt getEqualityExpressionInt() {
    return ((PropertyZeroOrOne<EqualityExpressionInt>)getProperty("equalityExpressionInt")).getValue();
  }
}

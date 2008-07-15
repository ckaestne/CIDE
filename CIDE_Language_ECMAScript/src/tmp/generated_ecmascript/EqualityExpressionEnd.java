package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EqualityExpressionEnd extends GenASTNode {
  public EqualityExpressionEnd(EqualityOperator equalityOperator, RelationalExpression relationalExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EqualityOperator>("equalityOperator", equalityOperator),
      new PropertyOne<RelationalExpression>("relationalExpression", relationalExpression)
    }, firstToken, lastToken);
  }
  public EqualityExpressionEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EqualityExpressionEnd(cloneProperties(),firstToken,lastToken);
  }
  public EqualityOperator getEqualityOperator() {
    return ((PropertyOne<EqualityOperator>)getProperty("equalityOperator")).getValue();
  }
  public RelationalExpression getRelationalExpression() {
    return ((PropertyOne<RelationalExpression>)getProperty("relationalExpression")).getValue();
  }
}

package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EqualityExpression extends GenASTNode {
  public EqualityExpression(RelationalExpression relationalExpression, ArrayList<EqualityExpressionEnd> equalityExpressionEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<RelationalExpression>("relationalExpression", relationalExpression),
      new PropertyZeroOrMore<EqualityExpressionEnd>("equalityExpressionEnd", equalityExpressionEnd)
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
  public ArrayList<EqualityExpressionEnd> getEqualityExpressionEnd() {
    return ((PropertyZeroOrMore<EqualityExpressionEnd>)getProperty("equalityExpressionEnd")).getValue();
  }
}

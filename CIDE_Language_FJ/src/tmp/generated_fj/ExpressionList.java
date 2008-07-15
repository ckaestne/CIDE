package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ExpressionList extends GenASTNode {
  public ExpressionList(Expression expression, ArrayList<ExpressionRest> expressionRest, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression", expression),
      new PropertyZeroOrMore<ExpressionRest>("expressionRest", expressionRest)
    }, firstToken, lastToken);
  }
  public ExpressionList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ExpressionList(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public ArrayList<ExpressionRest> getExpressionRest() {
    return ((PropertyZeroOrMore<ExpressionRest>)getProperty("expressionRest")).getValue();
  }
}

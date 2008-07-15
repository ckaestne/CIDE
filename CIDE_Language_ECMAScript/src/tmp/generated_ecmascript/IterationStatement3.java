package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IterationStatement3 extends IterationStatement {
  public IterationStatement3(ExpressionNoIn expressionNoIn, Expression expression2, Expression expression3, Statement statement2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ExpressionNoIn>("expressionNoIn", expressionNoIn),
      new PropertyZeroOrOne<Expression>("expression2", expression2),
      new PropertyZeroOrOne<Expression>("expression3", expression3),
      new PropertyOne<Statement>("statement2", statement2)
    }, firstToken, lastToken);
  }
  public IterationStatement3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IterationStatement3(cloneProperties(),firstToken,lastToken);
  }
  public ExpressionNoIn getExpressionNoIn() {
    return ((PropertyZeroOrOne<ExpressionNoIn>)getProperty("expressionNoIn")).getValue();
  }
  public Expression getExpression2() {
    return ((PropertyZeroOrOne<Expression>)getProperty("expression2")).getValue();
  }
  public Expression getExpression3() {
    return ((PropertyZeroOrOne<Expression>)getProperty("expression3")).getValue();
  }
  public Statement getStatement2() {
    return ((PropertyOne<Statement>)getProperty("statement2")).getValue();
  }
}

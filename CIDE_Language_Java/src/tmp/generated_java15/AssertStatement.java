package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssertStatement extends GenASTNode {
  public AssertStatement(Expression expression, Expression expression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression", expression),
      new PropertyZeroOrOne<Expression>("expression1", expression1)
    }, firstToken, lastToken);
  }
  public AssertStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssertStatement(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public Expression getExpression1() {
    return ((PropertyZeroOrOne<Expression>)getProperty("expression1")).getValue();
  }
}

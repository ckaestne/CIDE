package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IfStatement extends GenASTNode {
  public IfStatement(Expression expression, Statement statement, Statement statement1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression", expression),
      new PropertyOne<Statement>("statement", statement),
      new PropertyZeroOrOne<Statement>("statement1", statement1)
    }, firstToken, lastToken);
  }
  public IfStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IfStatement(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public Statement getStatement() {
    return ((PropertyOne<Statement>)getProperty("statement")).getValue();
  }
  public Statement getStatement1() {
    return ((PropertyZeroOrOne<Statement>)getProperty("statement1")).getValue();
  }
}

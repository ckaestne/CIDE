package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DoStatement extends GenASTNode {
  public DoStatement(Statement statement, Expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Statement>("statement", statement),
      new PropertyOne<Expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public DoStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DoStatement(cloneProperties(),firstToken,lastToken);
  }
  public Statement getStatement() {
    return ((PropertyOne<Statement>)getProperty("statement")).getValue();
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
}

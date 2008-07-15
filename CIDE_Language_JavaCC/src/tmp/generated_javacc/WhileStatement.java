package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class WhileStatement extends GenASTNode {
  public WhileStatement(Expression expression, Statement statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression", expression),
      new PropertyOne<Statement>("statement", statement)
    }, firstToken, lastToken);
  }
  public WhileStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new WhileStatement(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public Statement getStatement() {
    return ((PropertyOne<Statement>)getProperty("statement")).getValue();
  }
}

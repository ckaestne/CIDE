package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IterationStatement1 extends IterationStatement {
  public IterationStatement1(ASTStringNode while_kw, Expression expression, Statement statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("while_kw", while_kw),
      new PropertyOne<Expression>("expression", expression),
      new PropertyOne<Statement>("statement", statement)
    }, firstToken, lastToken);
  }
  public IterationStatement1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IterationStatement1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getWhile_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("while_kw")).getValue();
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public Statement getStatement() {
    return ((PropertyOne<Statement>)getProperty("statement")).getValue();
  }
}

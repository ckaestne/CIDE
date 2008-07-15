package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IterationStatement1 extends IterationStatement {
  public IterationStatement1(Statement statement, Expression expression, ASTTextNode text332, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Statement>("statement", statement),
      new PropertyOne<Expression>("expression", expression),
      new PropertyZeroOrOne<ASTTextNode>("text332", text332)
    }, firstToken, lastToken);
  }
  public IterationStatement1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IterationStatement1(cloneProperties(),firstToken,lastToken);
  }
  public Statement getStatement() {
    return ((PropertyOne<Statement>)getProperty("statement")).getValue();
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public ASTTextNode getText332() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text332")).getValue();
  }
}

package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IterationStatement2 extends IterationStatement {
  public IterationStatement2(Expression expression1, Statement statement1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression1", expression1),
      new PropertyOne<Statement>("statement1", statement1)
    }, firstToken, lastToken);
  }
  public IterationStatement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IterationStatement2(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression1() {
    return ((PropertyOne<Expression>)getProperty("expression1")).getValue();
  }
  public Statement getStatement1() {
    return ((PropertyOne<Statement>)getProperty("statement1")).getValue();
  }
}

package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SelectionStatement1 extends SelectionStatement {
  public SelectionStatement1(ASTStringNode if_kw, Expression expression, Statement statement, Statement statement1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("if_kw", if_kw),
      new PropertyOne<Expression>("expression", expression),
      new PropertyOne<Statement>("statement", statement),
      new PropertyZeroOrOne<Statement>("statement1", statement1)
    }, firstToken, lastToken);
  }
  public SelectionStatement1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SelectionStatement1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIf_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("if_kw")).getValue();
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

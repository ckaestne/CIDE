package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SelectionStatement2 extends SelectionStatement {
  public SelectionStatement2(ASTStringNode switch_kw, Expression expression1, Statement statement2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("switch_kw", switch_kw),
      new PropertyOne<Expression>("expression1", expression1),
      new PropertyOne<Statement>("statement2", statement2)
    }, firstToken, lastToken);
  }
  public SelectionStatement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SelectionStatement2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getSwitch_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("switch_kw")).getValue();
  }
  public Expression getExpression1() {
    return ((PropertyOne<Expression>)getProperty("expression1")).getValue();
  }
  public Statement getStatement2() {
    return ((PropertyOne<Statement>)getProperty("statement2")).getValue();
  }
}

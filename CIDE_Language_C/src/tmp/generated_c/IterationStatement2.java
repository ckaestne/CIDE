package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IterationStatement2 extends IterationStatement {
  public IterationStatement2(ASTStringNode do_kw, Statement statement1, ASTStringNode while_kw1, Expression expression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("do_kw", do_kw),
      new PropertyOne<Statement>("statement1", statement1),
      new PropertyOne<ASTStringNode>("while_kw1", while_kw1),
      new PropertyOne<Expression>("expression1", expression1)
    }, firstToken, lastToken);
  }
  public IterationStatement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IterationStatement2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getDo_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("do_kw")).getValue();
  }
  public Statement getStatement1() {
    return ((PropertyOne<Statement>)getProperty("statement1")).getValue();
  }
  public ASTStringNode getWhile_kw1() {
    return ((PropertyOne<ASTStringNode>)getProperty("while_kw1")).getValue();
  }
  public Expression getExpression1() {
    return ((PropertyOne<Expression>)getProperty("expression1")).getValue();
  }
}

package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class JumpStatement4 extends JumpStatement {
  public JumpStatement4(ASTStringNode return_kw, Expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("return_kw", return_kw),
      new PropertyZeroOrOne<Expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public JumpStatement4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new JumpStatement4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getReturn_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("return_kw")).getValue();
  }
  public Expression getExpression() {
    return ((PropertyZeroOrOne<Expression>)getProperty("expression")).getValue();
  }
}

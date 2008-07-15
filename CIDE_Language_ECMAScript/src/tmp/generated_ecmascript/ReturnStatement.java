package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ReturnStatement extends GenASTNode {
  public ReturnStatement(Expression expression, ASTTextNode text335, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Expression>("expression", expression),
      new PropertyZeroOrOne<ASTTextNode>("text335", text335)
    }, firstToken, lastToken);
  }
  public ReturnStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ReturnStatement(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyZeroOrOne<Expression>)getProperty("expression")).getValue();
  }
  public ASTTextNode getText335() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text335")).getValue();
  }
}

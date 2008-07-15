package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Enumerator extends GenASTNode {
  public Enumerator(ASTStringNode identifier, ConstantExpression constantExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrOne<ConstantExpression>("constantExpression", constantExpression)
    }, firstToken, lastToken);
  }
  public Enumerator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Enumerator(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public ConstantExpression getConstantExpression() {
    return ((PropertyZeroOrOne<ConstantExpression>)getProperty("constantExpression")).getValue();
  }
}

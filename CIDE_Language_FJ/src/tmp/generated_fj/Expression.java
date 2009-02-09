package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Expression extends GenASTNode {
  public Expression(PrimaryExpression primaryExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PrimaryExpression>("primaryExpression", primaryExpression)
    }, firstToken, lastToken);
  }
  public Expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Expression(cloneProperties(),firstToken,lastToken);
  }
  public PrimaryExpression getPrimaryExpression() {
    return ((PropertyOne<PrimaryExpression>)getProperty("primaryExpression")).getValue();
  }
}

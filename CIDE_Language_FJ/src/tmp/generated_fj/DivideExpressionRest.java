package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DivideExpressionRest extends GenASTNode {
  public DivideExpressionRest(PrimaryExpression primaryExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PrimaryExpression>("primaryExpression", primaryExpression)
    }, firstToken, lastToken);
  }
  public DivideExpressionRest(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new DivideExpressionRest(cloneProperties(),firstToken,lastToken);
  }
  public PrimaryExpression getPrimaryExpression() {
    return ((PropertyOne<PrimaryExpression>)getProperty("primaryExpression")).getValue();
  }
}

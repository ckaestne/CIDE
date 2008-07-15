package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PreDecrementExpression extends GenASTNode {
  public PreDecrementExpression(PrimaryExpression primaryExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PrimaryExpression>("primaryExpression", primaryExpression)
    }, firstToken, lastToken);
  }
  public PreDecrementExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PreDecrementExpression(cloneProperties(),firstToken,lastToken);
  }
  public PrimaryExpression getPrimaryExpression() {
    return ((PropertyOne<PrimaryExpression>)getProperty("primaryExpression")).getValue();
  }
}

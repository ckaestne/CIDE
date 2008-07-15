package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConstantExpression extends GenASTNode {
  public ConstantExpression(ConditionalExpression conditionalExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ConditionalExpression>("conditionalExpression", conditionalExpression)
    }, firstToken, lastToken);
  }
  public ConstantExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ConstantExpression(cloneProperties(),firstToken,lastToken);
  }
  public ConditionalExpression getConditionalExpression() {
    return ((PropertyOne<ConditionalExpression>)getProperty("conditionalExpression")).getValue();
  }
}

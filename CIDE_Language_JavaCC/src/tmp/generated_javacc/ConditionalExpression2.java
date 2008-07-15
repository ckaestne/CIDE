package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConditionalExpression2 extends ConditionalExpression {
  public ConditionalExpression2(ConditionalOrExpression conditionalOrExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ConditionalOrExpression>("conditionalOrExpression", conditionalOrExpression)
    }, firstToken, lastToken);
  }
  public ConditionalExpression2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ConditionalExpression2(cloneProperties(),firstToken,lastToken);
  }
  public ConditionalOrExpression getConditionalOrExpression() {
    return ((PropertyOne<ConditionalOrExpression>)getProperty("conditionalOrExpression")).getValue();
  }
}

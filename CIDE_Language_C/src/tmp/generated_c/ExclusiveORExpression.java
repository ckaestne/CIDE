package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ExclusiveORExpression extends GenASTNode {
  public ExclusiveORExpression(ANDExpression aNDExpression, ExclusiveORExpression exclusiveORExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ANDExpression>("aNDExpression", aNDExpression),
      new PropertyZeroOrOne<ExclusiveORExpression>("exclusiveORExpression", exclusiveORExpression)
    }, firstToken, lastToken);
  }
  public ExclusiveORExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ExclusiveORExpression(cloneProperties(),firstToken,lastToken);
  }
  public ANDExpression getANDExpression() {
    return ((PropertyOne<ANDExpression>)getProperty("aNDExpression")).getValue();
  }
  public ExclusiveORExpression getExclusiveORExpression() {
    return ((PropertyZeroOrOne<ExclusiveORExpression>)getProperty("exclusiveORExpression")).getValue();
  }
}

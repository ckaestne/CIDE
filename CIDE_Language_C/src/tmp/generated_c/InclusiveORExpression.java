package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class InclusiveORExpression extends GenASTNode {
  public InclusiveORExpression(ExclusiveORExpression exclusiveORExpression, InclusiveORExpression inclusiveORExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ExclusiveORExpression>("exclusiveORExpression", exclusiveORExpression),
      new PropertyZeroOrOne<InclusiveORExpression>("inclusiveORExpression", inclusiveORExpression)
    }, firstToken, lastToken);
  }
  public InclusiveORExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new InclusiveORExpression(cloneProperties(),firstToken,lastToken);
  }
  public ExclusiveORExpression getExclusiveORExpression() {
    return ((PropertyOne<ExclusiveORExpression>)getProperty("exclusiveORExpression")).getValue();
  }
  public InclusiveORExpression getInclusiveORExpression() {
    return ((PropertyZeroOrOne<InclusiveORExpression>)getProperty("inclusiveORExpression")).getValue();
  }
}

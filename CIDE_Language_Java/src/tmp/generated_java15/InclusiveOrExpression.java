package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class InclusiveOrExpression extends GenASTNode {
  public InclusiveOrExpression(ExclusiveOrExpression exclusiveOrExpression, ArrayList<ExclusiveOrExpression> exclusiveOrExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ExclusiveOrExpression>("exclusiveOrExpression", exclusiveOrExpression),
      new PropertyZeroOrMore<ExclusiveOrExpression>("exclusiveOrExpression1", exclusiveOrExpression1)
    }, firstToken, lastToken);
  }
  public InclusiveOrExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new InclusiveOrExpression(cloneProperties(),firstToken,lastToken);
  }
  public ExclusiveOrExpression getExclusiveOrExpression() {
    return ((PropertyOne<ExclusiveOrExpression>)getProperty("exclusiveOrExpression")).getValue();
  }
  public ArrayList<ExclusiveOrExpression> getExclusiveOrExpression1() {
    return ((PropertyZeroOrMore<ExclusiveOrExpression>)getProperty("exclusiveOrExpression1")).getValue();
  }
}

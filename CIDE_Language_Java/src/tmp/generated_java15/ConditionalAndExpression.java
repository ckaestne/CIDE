package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConditionalAndExpression extends GenASTNode {
  public ConditionalAndExpression(InclusiveOrExpression inclusiveOrExpression, ArrayList<InclusiveOrExpression> inclusiveOrExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<InclusiveOrExpression>("inclusiveOrExpression", inclusiveOrExpression),
      new PropertyZeroOrMore<InclusiveOrExpression>("inclusiveOrExpression1", inclusiveOrExpression1)
    }, firstToken, lastToken);
  }
  public ConditionalAndExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ConditionalAndExpression(cloneProperties(),firstToken,lastToken);
  }
  public InclusiveOrExpression getInclusiveOrExpression() {
    return ((PropertyOne<InclusiveOrExpression>)getProperty("inclusiveOrExpression")).getValue();
  }
  public ArrayList<InclusiveOrExpression> getInclusiveOrExpression1() {
    return ((PropertyZeroOrMore<InclusiveOrExpression>)getProperty("inclusiveOrExpression1")).getValue();
  }
}

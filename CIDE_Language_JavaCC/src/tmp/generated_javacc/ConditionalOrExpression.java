package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConditionalOrExpression extends GenASTNode {
  public ConditionalOrExpression(ConditionalAndExpression conditionalAndExpression, ArrayList<ConditionalAndExpression> conditionalAndExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ConditionalAndExpression>("conditionalAndExpression", conditionalAndExpression),
      new PropertyZeroOrMore<ConditionalAndExpression>("conditionalAndExpression1", conditionalAndExpression1)
    }, firstToken, lastToken);
  }
  public ConditionalOrExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ConditionalOrExpression(cloneProperties(),firstToken,lastToken);
  }
  public ConditionalAndExpression getConditionalAndExpression() {
    return ((PropertyOne<ConditionalAndExpression>)getProperty("conditionalAndExpression")).getValue();
  }
  public ArrayList<ConditionalAndExpression> getConditionalAndExpression1() {
    return ((PropertyZeroOrMore<ConditionalAndExpression>)getProperty("conditionalAndExpression1")).getValue();
  }
}

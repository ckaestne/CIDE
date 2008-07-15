package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConditionalExpression1 extends ConditionalExpression {
  public ConditionalExpression1(IASTNode conditionalExpressionFull, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyWrapper<IASTNode,Expression>("conditionalExpressionFull", conditionalExpressionFull, "expression")
    }, firstToken, lastToken);
  }
  public ConditionalExpression1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ConditionalExpression1(cloneProperties(),firstToken,lastToken);
  }
  public IASTNode getConditionalExpressionFull() {
    return ((PropertyWrapper<IASTNode,Expression>)getProperty("conditionalExpressionFull")).getValue();
  }
}

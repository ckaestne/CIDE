package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConditionalExpression1 extends ConditionalExpression {
  public ConditionalExpression1(ASTNode conditionalExpressionFull, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyWrapper<ASTNode,Expression>("conditionalExpressionFull", conditionalExpressionFull, "expression")
    }, firstToken, lastToken);
  }
  public ConditionalExpression1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new ConditionalExpression1(cloneProperties(),firstToken,lastToken);
  }
  public ASTNode getConditionalExpressionFull() {
    return ((PropertyWrapper<ASTNode,Expression>)getProperty("conditionalExpressionFull")).getValue();
  }
}

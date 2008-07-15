package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class additive_expressionInternal extends GenASTNode {
  public additive_expressionInternal(additive_operator additive_operator, additive_expression additive_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<additive_operator>("additive_operator", additive_operator),
      new PropertyOne<additive_expression>("additive_expression", additive_expression)
    }, firstToken, lastToken);
  }
  public additive_expressionInternal(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new additive_expressionInternal(cloneProperties(),firstToken,lastToken);
  }
  public additive_operator getAdditive_operator() {
    return ((PropertyOne<additive_operator>)getProperty("additive_operator")).getValue();
  }
  public additive_expression getAdditive_expression() {
    return ((PropertyOne<additive_expression>)getProperty("additive_expression")).getValue();
  }
}

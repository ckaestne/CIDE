package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class array_creation_postfix_expressionInternal4 extends array_creation_postfix_expressionInternal {
  public array_creation_postfix_expressionInternal4(post_decrement_expression post_decrement_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<post_decrement_expression>("post_decrement_expression", post_decrement_expression)
    }, firstToken, lastToken);
  }
  public array_creation_postfix_expressionInternal4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new array_creation_postfix_expressionInternal4(cloneProperties(),firstToken,lastToken);
  }
  public post_decrement_expression getPost_decrement_expression() {
    return ((PropertyOne<post_decrement_expression>)getProperty("post_decrement_expression")).getValue();
  }
}

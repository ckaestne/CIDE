package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class array_creation_postfix_expressionInternal3 extends array_creation_postfix_expressionInternal {
  public array_creation_postfix_expressionInternal3(post_increment_expression post_increment_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<post_increment_expression>("post_increment_expression", post_increment_expression)
    }, firstToken, lastToken);
  }
  public array_creation_postfix_expressionInternal3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new array_creation_postfix_expressionInternal3(cloneProperties(),firstToken,lastToken);
  }
  public post_increment_expression getPost_increment_expression() {
    return ((PropertyOne<post_increment_expression>)getProperty("post_increment_expression")).getValue();
  }
}

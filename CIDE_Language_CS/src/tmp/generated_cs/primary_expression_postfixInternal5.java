package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_postfixInternal5 extends primary_expression_postfixInternal {
  public primary_expression_postfixInternal5(post_decrement_expression post_decrement_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<post_decrement_expression>("post_decrement_expression", post_decrement_expression)
    }, firstToken, lastToken);
  }
  public primary_expression_postfixInternal5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_postfixInternal5(cloneProperties(),firstToken,lastToken);
  }
  public post_decrement_expression getPost_decrement_expression() {
    return ((PropertyOne<post_decrement_expression>)getProperty("post_decrement_expression")).getValue();
  }
}

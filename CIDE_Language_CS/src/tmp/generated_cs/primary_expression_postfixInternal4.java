package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_postfixInternal4 extends primary_expression_postfixInternal {
  public primary_expression_postfixInternal4(post_increment_expression post_increment_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<post_increment_expression>("post_increment_expression", post_increment_expression)
    }, firstToken, lastToken);
  }
  public primary_expression_postfixInternal4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_postfixInternal4(cloneProperties(),firstToken,lastToken);
  }
  public post_increment_expression getPost_increment_expression() {
    return ((PropertyOne<post_increment_expression>)getProperty("post_increment_expression")).getValue();
  }
}

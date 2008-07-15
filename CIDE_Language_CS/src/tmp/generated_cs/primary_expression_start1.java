package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_start1 extends primary_expression_start {
  public primary_expression_start1(literal literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<literal>("literal", literal)
    }, firstToken, lastToken);
  }
  public primary_expression_start1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_start1(cloneProperties(),firstToken,lastToken);
  }
  public literal getLiteral() {
    return ((PropertyOne<literal>)getProperty("literal")).getValue();
  }
}

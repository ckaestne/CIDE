package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_start10 extends primary_expression_start {
  public primary_expression_start10(unchecked_expression unchecked_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<unchecked_expression>("unchecked_expression", unchecked_expression)
    }, firstToken, lastToken);
  }
  public primary_expression_start10(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_start10(cloneProperties(),firstToken,lastToken);
  }
  public unchecked_expression getUnchecked_expression() {
    return ((PropertyOne<unchecked_expression>)getProperty("unchecked_expression")).getValue();
  }
}

package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class for_initializer2 extends for_initializer {
  public for_initializer2(expression_list expression_list, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expression_list>("expression_list", expression_list)
    }, firstToken, lastToken);
  }
  public for_initializer2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new for_initializer2(cloneProperties(),firstToken,lastToken);
  }
  public expression_list getExpression_list() {
    return ((PropertyOne<expression_list>)getProperty("expression_list")).getValue();
  }
}

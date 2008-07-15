package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression2 extends primary_expression {
  public primary_expression2(creation_expression creation_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<creation_expression>("creation_expression", creation_expression)
    }, firstToken, lastToken);
  }
  public primary_expression2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression2(cloneProperties(),firstToken,lastToken);
  }
  public creation_expression getCreation_expression() {
    return ((PropertyOne<creation_expression>)getProperty("creation_expression")).getValue();
  }
}

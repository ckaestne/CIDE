package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class typeof_expression extends GenASTNode {
  public typeof_expression(type type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type)
    }, firstToken, lastToken);
  }
  public typeof_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new typeof_expression(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
}

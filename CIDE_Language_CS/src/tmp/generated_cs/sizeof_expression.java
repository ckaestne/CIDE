package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class sizeof_expression extends GenASTNode {
  public sizeof_expression(type type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type)
    }, firstToken, lastToken);
  }
  public sizeof_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new sizeof_expression(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
}

package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class stackalloc_initializer extends GenASTNode {
  public stackalloc_initializer(non_array_type non_array_type, expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<non_array_type>("non_array_type", non_array_type),
      new PropertyOne<expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public stackalloc_initializer(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new stackalloc_initializer(cloneProperties(),firstToken,lastToken);
  }
  public non_array_type getNon_array_type() {
    return ((PropertyOne<non_array_type>)getProperty("non_array_type")).getValue();
  }
  public expression getExpression() {
    return ((PropertyOne<expression>)getProperty("expression")).getValue();
  }
}

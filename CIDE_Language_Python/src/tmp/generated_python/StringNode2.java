package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StringNode2 extends StringNode {
  public StringNode2(ASTStringNode single_string2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("single_string2", single_string2)
    }, firstToken, lastToken);
  }
  public StringNode2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StringNode2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getSingle_string2() {
    return ((PropertyOne<ASTStringNode>)getProperty("single_string2")).getValue();
  }
}

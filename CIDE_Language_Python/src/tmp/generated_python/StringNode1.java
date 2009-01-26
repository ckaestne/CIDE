package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StringNode1 extends StringNode {
  public StringNode1(ASTStringNode single_string, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("single_string", single_string)
    }, firstToken, lastToken);
  }
  public StringNode1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StringNode1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getSingle_string() {
    return ((PropertyOne<ASTStringNode>)getProperty("single_string")).getValue();
  }
}

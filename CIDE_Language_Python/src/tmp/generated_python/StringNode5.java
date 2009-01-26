package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StringNode5 extends StringNode {
  public StringNode5(ASTStringNode single_ustring, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("single_ustring", single_ustring)
    }, firstToken, lastToken);
  }
  public StringNode5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StringNode5(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getSingle_ustring() {
    return ((PropertyOne<ASTStringNode>)getProperty("single_ustring")).getValue();
  }
}

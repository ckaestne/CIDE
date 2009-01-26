package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StringNode6 extends StringNode {
  public StringNode6(ASTStringNode single_ustring2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("single_ustring2", single_ustring2)
    }, firstToken, lastToken);
  }
  public StringNode6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StringNode6(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getSingle_ustring2() {
    return ((PropertyOne<ASTStringNode>)getProperty("single_ustring2")).getValue();
  }
}

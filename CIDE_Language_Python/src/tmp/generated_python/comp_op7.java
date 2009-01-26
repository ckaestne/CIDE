package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class comp_op7 extends comp_op {
  public comp_op7(ASTStringNode notequal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("notequal", notequal)
    }, firstToken, lastToken);
  }
  public comp_op7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new comp_op7(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getNotequal() {
    return ((PropertyOne<ASTStringNode>)getProperty("notequal")).getValue();
  }
}

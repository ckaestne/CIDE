package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class comp_op1 extends comp_op {
  public comp_op1(ASTStringNode less, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("less", less)
    }, firstToken, lastToken);
  }
  public comp_op1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new comp_op1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getLess() {
    return ((PropertyOne<ASTStringNode>)getProperty("less")).getValue();
  }
}

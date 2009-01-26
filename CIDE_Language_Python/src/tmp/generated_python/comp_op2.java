package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class comp_op2 extends comp_op {
  public comp_op2(ASTStringNode greater, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("greater", greater)
    }, firstToken, lastToken);
  }
  public comp_op2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new comp_op2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getGreater() {
    return ((PropertyOne<ASTStringNode>)getProperty("greater")).getValue();
  }
}

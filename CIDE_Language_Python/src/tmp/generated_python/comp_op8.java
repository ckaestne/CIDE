package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class comp_op8 extends comp_op {
  public comp_op8(ASTStringNode in, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("in", in)
    }, firstToken, lastToken);
  }
  public comp_op8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new comp_op8(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIn() {
    return ((PropertyOne<ASTStringNode>)getProperty("in")).getValue();
  }
}

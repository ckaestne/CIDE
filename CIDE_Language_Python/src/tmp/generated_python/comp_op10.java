package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class comp_op10 extends comp_op {
  public comp_op10(ASTStringNode is, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("is", is)
    }, firstToken, lastToken);
  }
  public comp_op10(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new comp_op10(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIs() {
    return ((PropertyOne<ASTStringNode>)getProperty("is")).getValue();
  }
}

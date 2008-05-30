package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Primitive1 extends Primitive {
  public Primitive1(ASTNode optionalNode, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyWrapper<ASTNode,Terminal>("optionalNode", optionalNode, "terminal")
    }, firstToken, lastToken);
  }
  public Primitive1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Primitive1(cloneProperties(),firstToken,lastToken);
  }
  public ASTNode getOptionalNode() {
    return ((PropertyWrapper<ASTNode,Terminal>)getProperty("optionalNode")).getValue();
  }
}

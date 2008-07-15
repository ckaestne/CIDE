package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Primitive1 extends Primitive {
  public Primitive1(IASTNode optionalNode, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyWrapper<IASTNode,Terminal>("optionalNode", optionalNode, "terminal")
    }, firstToken, lastToken);
  }
  public Primitive1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Primitive1(cloneProperties(),firstToken,lastToken);
  }
  public IASTNode getOptionalNode() {
    return ((PropertyWrapper<IASTNode,Terminal>)getProperty("optionalNode")).getValue();
  }
}

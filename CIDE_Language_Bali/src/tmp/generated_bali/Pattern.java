package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Pattern extends GenASTNode {
  public Pattern(ArrayList<Primitive> primitive, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<Primitive>("primitive", primitive)
    }, firstToken, lastToken);
  }
  public Pattern(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Pattern(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Primitive> getPrimitive() {
    return ((PropertyOneOrMore<Primitive>)getProperty("primitive")).getValue();
  }
}

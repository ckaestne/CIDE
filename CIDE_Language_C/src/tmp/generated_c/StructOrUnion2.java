package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StructOrUnion2 extends StructOrUnion {
  public StructOrUnion2(ASTStringNode union, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("union", union)
    }, firstToken, lastToken);
  }
  public StructOrUnion2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StructOrUnion2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getUnion() {
    return ((PropertyOne<ASTStringNode>)getProperty("union")).getValue();
  }
}

package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StringNode8 extends StringNode {
  public StringNode8(ASTStringNode triple_ustring2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("triple_ustring2", triple_ustring2)
    }, firstToken, lastToken);
  }
  public StringNode8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StringNode8(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getTriple_ustring2() {
    return ((PropertyOne<ASTStringNode>)getProperty("triple_ustring2")).getValue();
  }
}

package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StringNode4 extends StringNode {
  public StringNode4(ASTStringNode triple_string2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("triple_string2", triple_string2)
    }, firstToken, lastToken);
  }
  public StringNode4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StringNode4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getTriple_string2() {
    return ((PropertyOne<ASTStringNode>)getProperty("triple_string2")).getValue();
  }
}

package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StringNode3 extends StringNode {
  public StringNode3(ASTStringNode triple_string, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("triple_string", triple_string)
    }, firstToken, lastToken);
  }
  public StringNode3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StringNode3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getTriple_string() {
    return ((PropertyOne<ASTStringNode>)getProperty("triple_string")).getValue();
  }
}

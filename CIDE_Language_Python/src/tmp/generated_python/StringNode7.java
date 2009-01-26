package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StringNode7 extends StringNode {
  public StringNode7(ASTStringNode triple_ustring, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("triple_ustring", triple_ustring)
    }, firstToken, lastToken);
  }
  public StringNode7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StringNode7(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getTriple_ustring() {
    return ((PropertyOne<ASTStringNode>)getProperty("triple_ustring")).getValue();
  }
}

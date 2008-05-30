package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class naamMain1 extends naamMain {
  public naamMain1(ASTStringNode constructor_id, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("constructor_id", constructor_id)
    }, firstToken, lastToken);
  }
  public naamMain1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new naamMain1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getConstructor_id() {
    return ((PropertyOne<ASTStringNode>)getProperty("constructor_id")).getValue();
  }
}

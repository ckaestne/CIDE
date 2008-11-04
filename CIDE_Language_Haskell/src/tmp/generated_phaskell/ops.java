package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ops extends GenASTNode {
  public ops(ArrayList<op> op, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<op>("op", op)
    }, firstToken, lastToken);
  }
  public ops(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ops(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<op> getOp() {
    return ((PropertyList<op>)getProperty("op")).getValue();
  }
}

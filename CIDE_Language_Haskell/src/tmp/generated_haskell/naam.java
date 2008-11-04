package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class naam extends GenASTNode {
  public naam(qcon qcon, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<qcon>("qcon", qcon)
    }, firstToken, lastToken);
  }
  public naam(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new naam(cloneProperties(),firstToken,lastToken);
  }
  public qcon getQcon() {
    return ((PropertyOne<qcon>)getProperty("qcon")).getValue();
  }
}

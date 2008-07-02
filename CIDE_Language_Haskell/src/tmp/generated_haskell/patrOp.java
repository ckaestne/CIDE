package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class patrOp extends GenASTNode {
  public patrOp(qconop qconop, patr patr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<qconop>("qconop", qconop),
      new PropertyOne<patr>("patr", patr)
    }, firstToken, lastToken);
  }
  public patrOp(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new patrOp(cloneProperties(),firstToken,lastToken);
  }
  public qconop getQconop() {
    return ((PropertyOne<qconop>)getProperty("qconop")).getValue();
  }
  public patr getPatr() {
    return ((PropertyOne<patr>)getProperty("patr")).getValue();
  }
}

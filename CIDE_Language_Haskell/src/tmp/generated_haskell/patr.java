package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class patr extends GenASTNode {
  public patr(patrMain patrMain, ArrayList<patrOp> patrOp, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<patrMain>("patrMain", patrMain),
      new PropertyZeroOrMore<patrOp>("patrOp", patrOp)
    }, firstToken, lastToken);
  }
  public patr(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new patr(cloneProperties(),firstToken,lastToken);
  }
  public patrMain getPatrMain() {
    return ((PropertyOne<patrMain>)getProperty("patrMain")).getValue();
  }
  public ArrayList<patrOp> getPatrOp() {
    return ((PropertyZeroOrMore<patrOp>)getProperty("patrOp")).getValue();
  }
}

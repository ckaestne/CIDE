package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrMore;
import cide.gparser.Token;

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

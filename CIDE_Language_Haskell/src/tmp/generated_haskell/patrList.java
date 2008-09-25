package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

public class patrList extends GenASTNode {
  public patrList(ArrayList<patr> patr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<patr>("patr", patr)
    }, firstToken, lastToken);
  }
  public patrList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new patrList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<patr> getPatr() {
    return ((PropertyList<patr>)getProperty("patr")).getValue();
  }
}

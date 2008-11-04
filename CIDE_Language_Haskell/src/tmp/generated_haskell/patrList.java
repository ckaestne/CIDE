package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

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

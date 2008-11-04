package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class patroon extends GenASTNode {
  public patroon(ArrayList<patroonPrefix> patroonPrefix, patroonMain patroonMain, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<patroonPrefix>("patroonPrefix", patroonPrefix),
      new PropertyOne<patroonMain>("patroonMain", patroonMain)
    }, firstToken, lastToken);
  }
  public patroon(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new patroon(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<patroonPrefix> getPatroonPrefix() {
    return ((PropertyZeroOrMore<patroonPrefix>)getProperty("patroonPrefix")).getValue();
  }
  public patroonMain getPatroonMain() {
    return ((PropertyOne<patroonMain>)getProperty("patroonMain")).getValue();
  }
}

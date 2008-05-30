package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ModuleNaamPrefix extends GenASTNode {
  public ModuleNaamPrefix(naamMain naamMain, ArrayList<naamMain> naamMain1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<naamMain>("naamMain", naamMain),
      new PropertyZeroOrMore<naamMain>("naamMain1", naamMain1)
    }, firstToken, lastToken);
  }
  public ModuleNaamPrefix(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new ModuleNaamPrefix(cloneProperties(),firstToken,lastToken);
  }
  public naamMain getNaamMain() {
    return ((PropertyOne<naamMain>)getProperty("naamMain")).getValue();
  }
  public ArrayList<naamMain> getNaamMain1() {
    return ((PropertyZeroOrMore<naamMain>)getProperty("naamMain1")).getValue();
  }
}

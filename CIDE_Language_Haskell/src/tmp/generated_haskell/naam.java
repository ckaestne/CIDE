package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class naam extends GenASTNode {
  public naam(ModuleNaamPrefix moduleNaamPrefix, naamMain naamMain, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ModuleNaamPrefix>("moduleNaamPrefix", moduleNaamPrefix),
      new PropertyOne<naamMain>("naamMain", naamMain)
    }, firstToken, lastToken);
  }
  public naam(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new naam(cloneProperties(),firstToken,lastToken);
  }
  public ModuleNaamPrefix getModuleNaamPrefix() {
    return ((PropertyZeroOrOne<ModuleNaamPrefix>)getProperty("moduleNaamPrefix")).getValue();
  }
  public naamMain getNaamMain() {
    return ((PropertyOne<naamMain>)getProperty("naamMain")).getValue();
  }
}

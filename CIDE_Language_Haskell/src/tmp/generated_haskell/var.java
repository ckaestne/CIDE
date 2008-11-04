package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class var extends GenASTNode {
  public var(ModuleNaamPrefix moduleNaamPrefix, varMain varMain, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ModuleNaamPrefix>("moduleNaamPrefix", moduleNaamPrefix),
      new PropertyOne<varMain>("varMain", varMain)
    }, firstToken, lastToken);
  }
  public var(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new var(cloneProperties(),firstToken,lastToken);
  }
  public ModuleNaamPrefix getModuleNaamPrefix() {
    return ((PropertyZeroOrOne<ModuleNaamPrefix>)getProperty("moduleNaamPrefix")).getValue();
  }
  public varMain getVarMain() {
    return ((PropertyOne<varMain>)getProperty("varMain")).getValue();
  }
}

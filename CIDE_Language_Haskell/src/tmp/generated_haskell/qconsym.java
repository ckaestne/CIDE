package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class qconsym extends GenASTNode {
  public qconsym(ModuleNaamPrefix moduleNaamPrefix, ASTStringNode consym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ModuleNaamPrefix>("moduleNaamPrefix", moduleNaamPrefix),
      new PropertyOne<ASTStringNode>("consym", consym)
    }, firstToken, lastToken);
  }
  public qconsym(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new qconsym(cloneProperties(),firstToken,lastToken);
  }
  public ModuleNaamPrefix getModuleNaamPrefix() {
    return ((PropertyZeroOrOne<ModuleNaamPrefix>)getProperty("moduleNaamPrefix")).getValue();
  }
  public ASTStringNode getConsym() {
    return ((PropertyOne<ASTStringNode>)getProperty("consym")).getValue();
  }
}

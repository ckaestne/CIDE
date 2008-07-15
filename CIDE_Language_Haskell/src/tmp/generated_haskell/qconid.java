package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class qconid extends GenASTNode {
  public qconid(ModuleNaamPrefix moduleNaamPrefix, ASTStringNode constructor_id, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ModuleNaamPrefix>("moduleNaamPrefix", moduleNaamPrefix),
      new PropertyOne<ASTStringNode>("constructor_id", constructor_id)
    }, firstToken, lastToken);
  }
  public qconid(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new qconid(cloneProperties(),firstToken,lastToken);
  }
  public ModuleNaamPrefix getModuleNaamPrefix() {
    return ((PropertyZeroOrOne<ModuleNaamPrefix>)getProperty("moduleNaamPrefix")).getValue();
  }
  public ASTStringNode getConstructor_id() {
    return ((PropertyOne<ASTStringNode>)getProperty("constructor_id")).getValue();
  }
}

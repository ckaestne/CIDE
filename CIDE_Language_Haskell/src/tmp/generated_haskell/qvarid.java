package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class qvarid extends GenASTNode {
  public qvarid(ModuleNaamPrefix moduleNaamPrefix, ASTStringNode variable_id, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ModuleNaamPrefix>("moduleNaamPrefix", moduleNaamPrefix),
      new PropertyOne<ASTStringNode>("variable_id", variable_id)
    }, firstToken, lastToken);
  }
  public qvarid(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new qvarid(cloneProperties(),firstToken,lastToken);
  }
  public ModuleNaamPrefix getModuleNaamPrefix() {
    return ((PropertyZeroOrOne<ModuleNaamPrefix>)getProperty("moduleNaamPrefix")).getValue();
  }
  public ASTStringNode getVariable_id() {
    return ((PropertyOne<ASTStringNode>)getProperty("variable_id")).getValue();
  }
}

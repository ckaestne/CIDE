package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class varOperator extends GenASTNode {
  public varOperator(ModuleNaamPrefix moduleNaamPrefix, varOperatorMain varOperatorMain, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ModuleNaamPrefix>("moduleNaamPrefix", moduleNaamPrefix),
      new PropertyOne<varOperatorMain>("varOperatorMain", varOperatorMain)
    }, firstToken, lastToken);
  }
  public varOperator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new varOperator(cloneProperties(),firstToken,lastToken);
  }
  public ModuleNaamPrefix getModuleNaamPrefix() {
    return ((PropertyZeroOrOne<ModuleNaamPrefix>)getProperty("moduleNaamPrefix")).getValue();
  }
  public varOperatorMain getVarOperatorMain() {
    return ((PropertyOne<varOperatorMain>)getProperty("varOperatorMain")).getValue();
  }
}
